package com.open.capacity.user.service;

import com.open.capacity.common.exception.service.ServiceException;
import com.open.capacity.common.model.SysMenu;
import com.open.capacity.common.model.SysPermission;
import com.open.capacity.common.model.SysRole;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
* @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2017年11月12日 上午22:57:51
 */
public interface SysRoleService {

	/**
	 * 保存角色
	 * @param sysRole
	 */
	void save(SysRole sysRole)  throws ServiceException;

	/**
	 * 修改角色
	 * @param sysRole
	 */
	void update(SysRole sysRole)  throws ServiceException;

	/**
	 * 删除角色
	 * @param id
	 */
	void deleteRole(Long id)  throws ServiceException;


	/**
	 * ID获取角色
	 * @param id
	 * @return
	 */
	SysRole findById(Long id)  throws ServiceException;

	/**
	 * 角色列表
	 * @param params
	 * @return
	 */
	PageResult<SysRole> findRoles(Map<String, Object> params)  throws ServiceException;

	/**
	 * 角色权限列表
	 * @param roleId
	 * @return
	 */
	Set<SysPermission> findPermissionsByRoleId(Long roleId)  throws ServiceException;

	/**
	 * 更新角色
	 * @param sysRole
	 */
	Result saveOrUpdate(SysRole sysRole)  throws ServiceException;

	/**
	 * @Author levlin
	 * @Date 14:03 2019/7/28
	 * @Param
	 * @Description 获取菜单id
	 **/
	List<HashMap<String, String>> findMenusByRoleId(Integer roleId);

	/**
	 * @Author levlin
	 * @Date 23:29 2019/7/28
	 * @Param
	 * @Description 角色权限id
	 **/
	List<HashMap<String, String>> findPermissionByRoleId(Integer roleId);

	/**
	 * @Author levlin
	 * @Date 18:14 2019/7/28
	 * @Param
	 * @Description 修改角色菜单
	 **/
	Result updateRoleMenus(Integer roleId, String [] resourceIds, String [] halfResourceIds);



	/**
	 * @Author levlin
	 * @Date 18:14 2019/7/28
	 * @Param
	 * @Description 角色权限
	 **/
	Result rolesPermission(Integer roleId, Integer [] resourceIds);

	/**
	 * @Author levlin
	 * @Date 21:37 2019/7/28
	 * @Param
	 * @Description 菜单权限
	 **/
	List<SysMenu> menusPermission();

}
