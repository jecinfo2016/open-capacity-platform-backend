package com.open.capacity.uaa.dao;


import com.open.capacity.uaa.model.DevGroup;
import com.open.capacity.uaa.model.DevUser;
import com.open.capacity.uaa.model.SysClient;
import com.open.capacity.uaa.model.SysService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DevUserDao {
    int insert(DevUser devUser);
    DevUser selectByUsername(String username);
    DevUser findById(Long id);
    int update(DevUser devUser);
    int updatePassword(DevUser devUser);
    @Select("select * from sys_service where id > 91 and parentId > 90 order by sort")//parentId")
    List<SysService> findServiceAll();
    List<SysClient> findClientList(@Param("params") Map<String, Object> params );
    int insertGroup(DevGroup devGroup);
    DevGroup selectByName(DevGroup devGroup);
    int updateGroup(DevGroup devGroup);
    int deleteGroup(Integer id);
    List<DevGroup> findGroup(@Param("params") Map<String, Object> params);
}
