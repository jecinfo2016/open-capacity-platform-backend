package com.open.capacity.user.dao;

import com.open.capacity.common.model.SysMenu;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Set;
/**
* @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2017年11月13日 上午22:57:51
 * 菜单
 */
@Mapper
public interface SysMenuDao {

	@Insert("insert into sys_menu(parentId, name, url, path, css, sort, createTime, updateTime,isMenu,hidden) "
			+ "values (#{parentId}, #{name}, #{url} , #{path} , #{css}, #{sort}, #{createTime}, #{updateTime},#{isMenu},#{hidden})")
	@Options(useGeneratedKeys = true, keyColumn = "id")
	int save(SysMenu menu);

	int updateByOps(SysMenu menu);

	@Select("select * from sys_menu t where t.id = #{id}")
	SysMenu findById(Long id);

	@Delete("delete from sys_menu where id = #{id}")
	int delete(Long id);

	int deleteByParentId(Long menuId);

	@Select("select * from sys_menu t order by t.sort")
	List<SysMenu> findAll();

	@Select("select * from sys_menu t where t.isMenu = 1 order by t.sort")
	List<SysMenu> findOnes();


	/**
	 * @Author levlin
	 * @Date 18:24 2019/7/28
	 * @Param
	 * @Description 删除角色菜单
	 **/
	Integer delRoleMenus(Integer roleId);

	/**
	 * @Author levlin
	 * @Date 18:25 2019/7/28
	 * @Param
	 * @Description 增加角色菜单
	 **/
	Integer saveRoleMenus(@Param("roleId") Integer roleId, @Param("resourceIds") String[] resourceIds);

	/**
	 * @Author levlin
	 * @Date 18:27 2019/7/28
	 * @Param
	 * @Description 删除菜单
	 **/
	Integer delHalfResourceIds(@Param("roleId")Integer roleId, @Param("halfResourceIds")String[] halfResourceIds);


	/**
	 * @Author levlin
	 * @Date 18:24 2019/7/28
	 * @Param
	 * @Description 删除角色权限
	 **/
	Integer delRolePermission(Integer roleId);

	/**
	 * @Author levlin
	 * @Date 18:25 2019/7/28
	 * @Param
	 * @Description 增加角色权限
	 **/
	Integer saveRolePermission(@Param("roleId") Integer roleId, @Param("resourceIds") Integer[] resourceIds);

	/**
	 * @Author levlin
	 * @Date 17:28 2019/7/29
	 * @Param
	 * @Description 角色菜单
	 **/
	Integer saveMenusByRoleId(@Param("roleIds") Set<Long> roleIds, @Param("menuId")Long menuId);


	/**
	 * @Author levlin
	 * @Date 15:52 2019/7/31
	 * @Param
	 * @Description 菜单权限
	 **/
	List<SysMenu> preMenus();

	/**
	 * @Author: levlin
	 * @Date: 2019/8/31
	 * @param:
	 * @Description:
	 */
	SysMenu findMenuByMenuId(Long id);
}
