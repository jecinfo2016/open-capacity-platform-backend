package com.open.capacity.message.dao;


import com.open.capacity.message.model.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 消息中心model
 */
@Mapper
public interface MessageDao {

    @Insert("insert into `sys_message` (title, content, type, send_time, send_user, app_Id, mobile) "
            + "values (#{title}, #{content}, #{type} , #{sendTime}, #{sendUser}, #{appId}, #{mobile})")
    int save(Message message);

    @Update("update sys_message set title = #{title}, content = #{content}, type = #{type} ,send_time = #{sendTime} where id = #{id}")
    int update(Message message);

    @Delete("delete from `sys_message` where id = #{id}")
    int delete(Long id);

    @Select("<script>" +
            "SELECT uu.nickname,m.*,us.client_name as appName FROM `sys_message` AS m " +
            "LEFT JOIN `oauth-center`.`oauth_client_details` AS us ON  m.id = us.id " +
            "LEFT JOIN `user-center`.`sys_user` AS uu ON m.send_user = uu.id " +
            "where 1 =1 " +
            "<if test = 'type != null'>and type = #{type}</if>" +
            "<if test = 'appId != null'>and m.id = #{appId}</if>" +
            "<if test = 'sendUser != null'>and send_user = #{sendUser}</if>" +
            "ORDER BY app_Id" +
            "</script>")
    List<Message> findAll(Message message);

    @Select("<script>" +
            "SELECT COUNT(1) FROM `sys_message` WHERE send_user = #{sendUser} " +
            "<if test = 'type != null'>and type = #{type}</if>" +
            "</script>")
    int countByType(Message message);

    @Select("SELECT us.name AS appName,m.* FROM `sys_message` AS m LEFT JOIN `user-center`.`sys_menu` AS us ON  m.app_Id = us.id GROUP BY app_Id")
    List<Message> findAppName();
}
