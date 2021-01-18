package com.open.capacity.user.service;

import com.open.capacity.common.exception.service.ServiceException;
import com.open.capacity.common.model.SysMenu;

import java.util.List;
import java.util.Set;

public interface SysMenuService {

	/**
	 * 添加菜单
	 * @param menu
	 */
	void save(SysMenu menu)  throws ServiceException;

	/**
	 * 更新菜单
	 * @param menu
	 */
	void update(SysMenu menu)  throws ServiceException;

	/**
	 * 删除菜单
	 * @param id
	 */
	void delete(Long id)  throws ServiceException;

	/**
	 * 角色分配菜单
	 * @param roleId
	 * @param menuIds
	 */
	void setMenuToRole(Long roleId, Set<Long> menuIds)  throws ServiceException;

	/**
	 * 角色菜单列表
	 * @param roleIds
	 * @return
	 */
	List<SysMenu> findByRoles(Set<Long> roleIds)  throws ServiceException;

	/**
	 * @Author: levlin
	 * @Date: 2020/2/5
	 * @param:
	 * @Description: 超级管理员菜单
	 */
	List<SysMenu> findAdminMenus();

	/**
	 * 菜单列表
	 * @return
	 */
	List<SysMenu> findAll();


	/**
	 * ID获取菜单
	 * @param id
	 * @return
	 */
	SysMenu findMenuByMenuId(Long id);

	/**
	 * ID获取菜单
	 * @param id
	 * @return
	 */
	SysMenu findById(Long id);


	/**
	 * 角色ID获取菜单
	 * @param roleId
	 * @return
	 */
	Set<Long> findMenuIdsByRoleId(Long roleId);

	List<SysMenu> findOnes();

	/**
	 * @Author levlin
	 * @Date 18:21 2019/7/28
	 * @Param
	 * @Description 根据角色删除菜单
	 **/
	Integer delRoleMenus(Integer roleId);

	/**
	 * @Author levlin
	 * @Date 18:25 2019/7/28
	 * @Param
	 * @Description 增加角色菜单
	 **/
	Integer saveRoleMenus(Integer roleId,String[] resourceIds);

	/**
	 * @Author levlin
	 * @Date 18:27 2019/7/28
	 * @Param
	 * @Description 删除菜单
	 **/
	Integer delHalfResourceIds(Integer roleId, String[] halfResourceIds);

	/**
	 * @Author levlin
	 * @Date 17:26 2019/7/29
	 * @Param
	 * @Description 角色保存菜单
	 **/
	Integer saveMenusByRoleId(Set<Long> roleIds, Long menuId);

	/**
	 * @Author levlin
	 * @Date 15:49 2019/7/31
	 * @Param
	 * @Description 菜单权限
	 **/
	List<SysMenu> preMenus();



}
