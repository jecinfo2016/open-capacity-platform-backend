package com.open.capacity.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.open.capacity.common.auth.details.LoginAppUser;
import com.open.capacity.common.exception.controller.ControllerException;
import com.open.capacity.common.exception.service.ServiceException;
import com.open.capacity.common.model.SysMenu;
import com.open.capacity.common.model.SysRole;
import com.open.capacity.common.util.SysUserUtil;
import com.open.capacity.common.web.CodeEnum;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.capacity.log.annotation.LogAnnotation;
import com.open.capacity.user.service.SysMenuService;
import com.open.capacity.user.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@Api(tags = "MENU API")
@RequestMapping("/menus")
public class SysMenuController {

	@Autowired
	private SysMenuService menuService;

	@Autowired
	private SysRoleService sysRoleService;

	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 删除菜单
	 * 
	 * @param id
	 * @throws ControllerException 
	 */
	@PreAuthorize("hasAuthority('menu:delete/menus/{id}')")
	@ApiOperation(value = "删除菜单")
	@DeleteMapping("delete/{id}")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result delete(@PathVariable Long id) throws ControllerException {
		try {
			menuService.delete(id);
			return Result.succeed("操作成功");
		} catch (Exception e) {
			throw new ControllerException(e);
		}

	}

	@PreAuthorize("hasAuthority('menu:get/menus/{roleId}/menus')")
	@ApiOperation(value = "根据roleId获取对应的菜单")
	@GetMapping("/{roleId}/menus")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public List<Map<String, Object>> findMenusByRoleId(@PathVariable Long roleId) throws ControllerException {

		try {
			Set<Long> roleIds = new HashSet<Long>()  ;
			//初始化角色
			roleIds.add(roleId) ;
			List<SysMenu> roleMenus = menuService.findByRoles(roleIds); // 获取该角色对应的菜单
			List<SysMenu> allMenus = menuService.findAll(); // 全部的菜单列表
			List<Map<String, Object>> authTrees = new ArrayList<>();

			Map<Long, SysMenu> roleMenusMap = roleMenus.stream()
					.collect(Collectors.toMap(SysMenu::getId, SysMenu -> SysMenu));

			for (SysMenu sysMenu : allMenus) {
				Map<String, Object> authTree = new HashMap<>();
				authTree.put("id", sysMenu.getId());
				authTree.put("name", sysMenu.getName());
				authTree.put("pId", sysMenu.getParentId());
				authTree.put("open", true);
				authTree.put("checked", false);
				if (roleMenusMap.get(sysMenu.getId()) != null) {
					authTree.put("checked", true);
				}
				authTrees.add(authTree);
			}
			return authTrees;
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
	}

	/**
	 * 给角色分配菜单
	 * @throws ControllerException 
	 */
	@PreAuthorize("hasAuthority('menu:post/menus/granted')")
	@ApiOperation(value = "角色分配菜单")
	@PostMapping("/granted")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result setMenuToRole(@RequestBody SysMenu sysMenu) throws ControllerException {
		try {
			menuService.setMenuToRole(sysMenu.getRoleId(), sysMenu.getMenuIds());
			return Result.succeed("操作成功");
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
	}

	@PreAuthorize("hasAuthority('menu:get/menus/findAlls')")
	@ApiOperation(value = "查询所有菜单")
	@GetMapping("/findAlls")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public PageResult<SysMenu> findAlls() throws ControllerException {
		try {
			List<SysMenu> list = menuService.findAll();
			return PageResult.<SysMenu>builder().data(list).code(0).count((long) list.size()).build();
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
	}

	@ApiOperation(value = "获取菜单以及顶级菜单")
	@GetMapping("/findOnes")
	@PreAuthorize("hasAuthority('menu:get/menus/findOnes')")
	public PageResult<SysMenu> findOnes() throws ControllerException {
		try {
			List<SysMenu> list = menuService.findOnes();
			return PageResult.<SysMenu>builder().data(list).code(0).count((long) list.size()).build();
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
	}

	/**
	 * 添加菜单 或者 更新
	 * 
	 * @param menu
	 * @return
	 * @throws ControllerException 
	 */
	@PreAuthorize("hasAnyAuthority('menu:post/menus','menu:put/menus')")
	@ApiOperation(value = "新增菜单")
	@PostMapping("saveOrUpdate")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result saveOrUpdate(@RequestBody SysMenu menu) throws ControllerException {
			try {
				if (menu.getId() != null) {
					menuService.update(menu);
				} else {
					menuService.save(menu);
				}
				return Result.succeed("操作成功");
			} catch (ServiceException e) {
				throw new ControllerException(e);
			}
	}

	/**
	 * 当前登录用户的菜单
	 * 
	 * @return
	 * @throws ControllerException 
	 */
	@PreAuthorize("hasAuthority('menu:get/menus/current')")
	@GetMapping("/current")
	@ApiOperation(value = "查询当前用户菜单")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public List<SysMenu> findMyMenu() throws ControllerException {
		try {
			LoginAppUser loginAppUser = SysUserUtil.getLoginAppUser();
			Set<SysRole> roles = loginAppUser.getSysRoles();
			if (CollectionUtils.isEmpty(roles)) {
				return Collections.emptyList();
			}
			Iterator it = roles.iterator();
			boolean flag = false;
			while(it.hasNext()) {
				SysRole s = (SysRole) it.next();
				if (s.getCode().equals("ADMIN")){
					flag = true;
				}
			}
			List<SysMenu> menus = null;
			if (flag){
				menus = menuService.findAdminMenus();
			}else {
				menus = menuService
						.findByRoles(roles.parallelStream().map(SysRole::getId).collect(Collectors.toSet()));
			}
			return menus;
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
	}

	@PreAuthorize("hasAuthority('menu:get/menus/findMenusByRoleId/{roleId}')")
	@GetMapping(value = "/findMenusByRoleId/{roleId}")
	@ApiOperation(value = "查询角色菜单")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "Integer")
	})
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result<List<HashMap<String, String>>> findMenusByRoleId(@PathVariable Integer roleId){
		List<HashMap<String, String>> map = sysRoleService.findMenusByRoleId(roleId);
		if (map.size() != 0) {
			Result<List<HashMap<String, String>>> result = new Result<>();
			result.setDatas(map);
			return result;
		}
		return Result.failed("fail");
	}

	@PreAuthorize("hasAuthority('menu:get/menus/findMenusByMenuId/{menuId}')")
	@GetMapping(value = "/findMenusByMenuId/{menuId}")
	@ApiOperation(value = "查询菜单")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "menuId", value = "菜单id", required = true, dataType = "Integer")
	})
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result<SysMenu> findMenusByMenuId(@PathVariable Integer menuId){
		Result<SysMenu> menuResult = new Result<>();
		if (menuId != 0L) {
			menuResult.setResp_code(CodeEnum.SUCCESS.getCode());
			menuResult.setDatas(menuService.findMenuByMenuId(Long.valueOf(menuId)));
			return menuResult;
		}
		return Result.failed("操作错误");
	}
}
