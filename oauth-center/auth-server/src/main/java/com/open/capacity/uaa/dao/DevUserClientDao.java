package com.open.capacity.uaa.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DevUserClientDao {
    @Insert("insert into dev_group_client(groupId,clientId) values(#{groupId},#{clientId})")
    int insert(@Param("groupId") Integer groupId, @Param("clientId") Integer clientId);
    @Delete("delete from dev_group_client where clientId = #{clientId}")
    int deleteByClientId(Integer clientId);
    @Select("select clientId from dev_group_client where groupId = #{groupId}")
    List<Long> getClientIds(Integer groupId);
//    @Delete("delete from dev_group_client where groupId = #{groupId")
//    int deleteByGroupId(Integer groupId);
}
