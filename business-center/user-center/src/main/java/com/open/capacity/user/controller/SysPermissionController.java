package com.open.capacity.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.open.capacity.common.exception.controller.ControllerException;
import com.open.capacity.common.exception.service.ServiceException;
import com.open.capacity.common.model.SysMenu;
import com.open.capacity.common.model.SysPermission;
import com.open.capacity.common.web.CodeEnum;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.capacity.log.annotation.LogAnnotation;
import com.open.capacity.user.service.SysMenuService;
import com.open.capacity.user.service.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


/**
* @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2017年11月12日 上午22:57:51
* 权限管理
 */
@RestController
@Api(tags = "PERMISSION API")
@RequestMapping("/permissions")
public class SysPermissionController {

	@Autowired
	private SysPermissionService sysPermissionService;

	@Autowired
	private SysMenuService menuService;

	private ObjectMapper objectMapper = new ObjectMapper();
	/**
	 * 删除权限标识
	 * 参考 /permissions/1
	 * @param id
	 * @throws ControllerException 
	 */
	@PreAuthorize("hasAuthority('permission:delete/permissions/{id}')")
	@ApiOperation(value = "后台管理删除权限标识")
	@DeleteMapping("delete/{id}")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result delete(@PathVariable Long id) throws ControllerException {

		try {
			sysPermissionService.delete(id);
			return  Result.succeed("操作成功");
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}

	}


	/**
	 * 查询所有的权限标识
	 * 参考 ?start=0&length=10
	 * @return
	 * @throws ControllerException 
	 */
	@PreAuthorize("hasAuthority('permission:get/permissions')")
	@ApiOperation(value = "后台管理查询所有的权限标识")
	@ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
        @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
	@LogAnnotation(module="user-center",recordRequestParam=false)
	@GetMapping("/findPermissions")
	public PageResult<SysPermission> findPermissions(@RequestParam Map<String, Object> params) throws ControllerException {
		
		try {
			return sysPermissionService.findPermissions(params);
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
	}

	/**
	 * 权限新增或者更新
	 * @param sysPermission
	 * @return
	 * @throws ControllerException 
	 */
	@PreAuthorize("hasAnyAuthority('permission:put/permissions','permission:post/permissions')")
	@PostMapping("/saveOrUpdate")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public Result saveOrUpdate(@RequestBody SysPermission sysPermission) throws ControllerException {
		try{
			if (sysPermission.getId()!=null){
				sysPermissionService.update(sysPermission);
			}else {
				sysPermissionService.save(sysPermission);
			}
			return Result.succeed("操作成功");
		}catch (ServiceException e){
			throw new ControllerException(e);
		}
	}

	@PreAuthorize("hasAuthority('permission:get/permissions/{roleId}/permissions')")
	@ApiOperation(value = "根据roleId获取对应的权限")
	@GetMapping("/{roleId}/permissions")
	@LogAnnotation(module="user-center",recordRequestParam=false)
	public List<Map<String, Object>> findAuthByRoleId(@PathVariable Long roleId) throws ControllerException {
		
		try {
			List<Map<String, Object>> authTrees = new ArrayList<>();
			Set<Long> roleIds = new HashSet<Long>();
			//初始化角色
			roleIds.add(roleId);
			Set<SysPermission> roleAuths = sysPermissionService.findByRoleIds(roleIds);//根据roleId获取对应的权限
			PageResult<SysPermission> allAuths = sysPermissionService.findPermissions(null);//根据roleId获取对应的权限


			Map<Long,SysPermission> roleAuthsMap = roleAuths.stream().collect(Collectors.toMap(SysPermission::getId,SysPermission->SysPermission));

			for (SysPermission sysPermission : allAuths.getData() ){
				Map<String, Object> authTree = new HashMap<>();
				authTree.put("id",sysPermission.getId() + "");
				authTree.put("name",sysPermission.getName());
				authTree.put("open",true);
				authTree.put("checked", false);
				if (roleAuthsMap.get(sysPermission.getId())!=null){
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
	 * @Author levlin
	 * @Date 15:42 2019/7/31
	 * @Param
	 * @Description 权限菜单
	 **/
	@PreAuthorize("hasAuthority('permission:get/menus/preMenus')")
	@GetMapping("/preMenus")
	@ApiOperation(value = "查询所有菜单")
	@LogAnnotation(module = "user-center", recordRequestParam = false)
	public Result<List<SysMenu>> preMenus(){
		Result<List<SysMenu>> sysMenuResult = new Result<>();
		sysMenuResult.setDatas(menuService.preMenus());
		sysMenuResult.setResp_code(CodeEnum.SUCCESS.getCode());
		return sysMenuResult;
	}

}
