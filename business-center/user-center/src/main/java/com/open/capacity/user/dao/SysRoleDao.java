package com.open.capacity.user.dao;

import com.open.capacity.common.model.SysRole;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2017年11月12日 上午22:57:51
 * 角色
 */
@Mapper
public interface SysRoleDao {

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into sys_role(code, name, `desc`, createTime, updateTime) values(#{code}, #{name}, #{desc}, #{createTime}, #{createTime})")
	int save(SysRole sysRole);

	@Update("update sys_role t set t.name = #{name} ,t.updateTime = #{updateTime} ,t.desc = #{desc} where t.id = #{id}")
	int updateByOps(SysRole sysRole);

	@Select("select * from sys_role t where t.id = #{id}")
	SysRole findById(Long id);

	@Select("select * from sys_role t where t.code = #{code}")
	SysRole findByCode(String code);

	@Delete("delete from sys_role where id = #{id}")
	int delete(Long id);

	int count(Map<String, Object> params);

	List<SysRole> findList(Map<String, Object> params);

	/**
	 * @Author levlin
	 * @Date 14:05 2019/7/28
	 * @Param
	 * @Description 角色菜单
	 **/
	List<HashMap<String, String>> findMenusByRoleId(Integer roleId);

	/**
	 * @Author levlin
	 * @Date 23:30 2019/7/28
	 * @Param
	 * @Description 角色权限
	 **/
	List<HashMap<String, String>> findPermissionByRoleId(Integer roleId);

	/**
	 * @Author: levlin
	 * @Date: 2019/9/1
	 * @param:
	 * @Description: 删除角色
	 */
	void deleteRole(Long roleId);

}
