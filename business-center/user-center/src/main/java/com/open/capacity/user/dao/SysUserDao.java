package com.open.capacity.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.open.capacity.common.model.SysUser;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
* @author 作者 owen E-mail: 624191343@qq.com
* @version 创建时间：2017年11月12日 上午22:57:51
 * 用户管理
 */
@Mapper
public interface SysUserDao  extends BaseMapper<SysUser> {

	int save(SysUser sysUser);

	int updateByOps(SysUser sysUser);

	int resetPassword(SysUser SysUser);

	@Select("select * from sys_user t where t.username = #{username}")
	SysUser findByUsername(String username);

	SysUser findById(Long id);

	int count(Map<String, Object> params);

	List<SysUser> findList(Map<String, Object> params);


	@Select("select u.* from sys_user u   where u.username = #{username}")
	SysUser findUserByUsername(String username);


	@Select("select u.* from sys_user u   where u.phone = #{mobile}")
	SysUser findUserByMobile(String mobile);

	//@Select("select * from sys_user where deptId = #{deptId}")
	List<SysUser> selectByDeptId(Long id);
}
