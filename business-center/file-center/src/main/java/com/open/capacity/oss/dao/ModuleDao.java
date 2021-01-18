package com.open.capacity.oss.dao;

import com.open.capacity.oss.model.ModuleInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ModuleDao {

    @Select("<script>" +
            "select * from module_info where 1=1 " +
            "<if test = 'name != null'>and name = #{name}</if>" +
            "</script>")
    List<ModuleInfo> findAll(ModuleInfo module);

    @Select("select * from module_info where id = #{Id}")
    ModuleInfo getById(Integer Id);

    @Update("update module_info set name = #{name}, icon = #{icon}, creater = #{creater} where id = #{id}")
    int update(ModuleInfo module);

    @Update("update module_info set showUrl = #{showUrl} where id = #{id}")
    int updateFile(ModuleInfo module);

    @Insert("insert into module_info (name, icon, creater, documentAddress, showUrl) values" +
            " (#{name}, #{icon}, #{creater}, #{documentAddress}, #{showUrl})")
    int save(ModuleInfo module);

    @Delete("delete from module_info where id = #{Id}")
    int delete(Integer Id);
}
