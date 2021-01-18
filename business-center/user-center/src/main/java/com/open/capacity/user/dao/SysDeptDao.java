package com.open.capacity.user.dao;

import com.open.capacity.common.model.SysDept;
import com.open.capacity.common.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SysDeptDao {

    List<SysDept> getAllDept();

    List<SysDept> getDeptByParentId(Long id);

    @Select("select * from sys_dept where id = 0")
    List<SysDept> getDeptById();

    int save(@Param("sysDept") SysDept sysDept);

    int update(@Param("sysDept")SysDept sysDept);

    int delete(long id);

}
