package com.open.device.dao;

import com.open.device.model.InficomboDevtype;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface InficomboDevtypeDao {

    @Insert("insert into `inficombo_devtype` (devtype, devtypedesc, vendor, appclass, devtypeflag) "
            + "values (#{devtype}, #{devtypedesc}, #{vendor} , #{appclass} , #{devtypeflag})")
    int save(InficomboDevtype inficomboDevtype);

    @Select("select * from `inficombo_devtype` t where t.devtype = #{devtype}")
    InficomboDevtype getByType(String devtype);
}
