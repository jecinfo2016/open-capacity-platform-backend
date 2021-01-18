package com.open.capacity.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.open.capacity.common.exception.controller.ControllerException;
import com.open.capacity.common.exception.service.ServiceException;
import com.open.capacity.common.model.SysMenu;
import com.open.capacity.common.model.SysRole;
import com.open.capacity.common.web.CodeEnum;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.capacity.log.annotation.LogAnnotation;
import com.open.capacity.user.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2017年11月12日 上午22:57:51
* 角色管理
 */

@Slf4j
@RestController
@Api(tags = "ROLE API")
@RequestMapping("/roles")
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;
	private ObjectMapper objectMapper = new ObjectMapper();


//	<!-- -->
	/**
	 * 后台管理查询角色
	 * @param params
	 * @return
	 * @throws JsonProcessingException 
	 */
	@PreAuthorize("hasAuthority('role:get/roles')")
	@ApiOperation(value = "后台管理查询角色")
	@GetMapping("/findRoles")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public PageResult<SysRole> findRoles(@RequestParam Map<String, Object> params) throws ControllerException {
		try {
			return sysRoleService.findRoles(params);
		} catch (ServiceException e) {
			 throw new ControllerException(e);
		}
	}

	/**
	 * 角色新增或者更新
	 * @param sysRole
	 * @return
	 * @throws ControllerException 
	 */
	@PreAuthorize("hasAnyAuthority('role:post/roles','role:put/roles')")
	@PostMapping("/saveOrUpdate")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result saveOrUpdate(@RequestBody SysRole sysRole) throws ControllerException {
		try {
			return sysRoleService.saveOrUpdate(sysRole);
		} catch (ServiceException e) {
			 throw new ControllerException(e);
		}
	}

	/**
	 * 后台管理删除角色
	 * delete /role/1
	 * @param id
	 * @throws ControllerException 
	 */
	@PreAuthorize("hasAuthority('role:delete/roles/{id}')")
	@ApiOperation(value = "后台管理删除角色")
	@DeleteMapping("/deleteRole/{id}")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result deleteRole(@PathVariable Long id) throws ControllerException {
		try {
			if (id == 1L){
				return Result.failed("管理员不可以删除");
			}
			sysRoleService.deleteRole(id);
			return Result.succeed("操作成功");
		}catch (Exception e){
			 throw new ControllerException(e);
		}
	}

	/**
	 * @Author levlin
	 * @Date 17:15 2019/7/28
	 * @Param
	 * @Description 角色菜单修改
	 **/
	@PreAuthorize("hasAnyAuthority('role:put/rolesMenus')")
	@ApiOperation(value = "角色菜单修改")
	@PostMapping("/rolesMenus")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result rolesMenus(@RequestParam Integer roleId, @RequestParam String [] resourceIds, @RequestParam
			String [] halfResourceIds) {
		if (roleId == 0 || roleId == null){
			return Result.failed("角色错误");
		}
		return sysRoleService.updateRoleMenus(roleId, resourceIds, halfResourceIds);
	}

	/**
	 * @Author levlin
	 * @Date 11:31 2019/7/29
	 * @Param
	 * @Description 角色权限
	 **/
	@PreAuthorize("hasAnyAuthority('role:put/rolesPermission')")
	@PostMapping("/rolesPermission")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result rolesPermission(@RequestParam Integer roleId, @RequestParam Integer [] resourceIds) {
		if (roleId == 0 || roleId == null){
			return Result.failed("角色错误");
		}
		return sysRoleService.rolesPermission(roleId, resourceIds);
	}

	/**
	 * @Author levlin
	 * @Date 14:33 2019/7/29
	 * @Param
	 * @Description 查询角色
	 **/
	@PreAuthorize("hasAuthority('role:findOne/roles/{id}')")
	@ApiOperation(value = "后台管理查询角色")
	@GetMapping("/findRoleById/{id}")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result<SysRole> findRoleById(@PathVariable Long id) {
		Result<SysRole> result = new Result<>();
		result.setResp_code(CodeEnum.SUCCESS.getCode());
		result.setDatas(sysRoleService.findById(id));
		return result;
	}

	@PreAuthorize("hasAuthority('role:get/menusPermission/current')")
	@GetMapping("/menusPermission")
	@ApiOperation(value = "查询角色权限")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public List<SysMenu> menusPermission() {
		List<SysMenu> menus = sysRoleService.menusPermission();
		return menus;
	}

	@PreAuthorize("hasAuthority('role:get/menus/findPermissionByRoleId/{roleId}')")
	@GetMapping(value = "/findPermissionByRoleId/{roleId}")
	@ApiOperation(value = "查询当前角色权限")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "roleId", value = "角色id", required = true, dataType = "Integer")
	})
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result<List<HashMap<String, String>>> findPermissionByRoleId(@PathVariable Integer roleId){
		List<HashMap<String, String>> map = sysRoleService.findPermissionByRoleId(roleId);
		if (map.size() != 0) {
			Result<List<HashMap<String, String>>> result = new Result<>();
			result.setDatas(map);
			return result;
		}
		return Result.failed("error");
	}

}
