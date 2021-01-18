package com.open.capacity.developer.water.dao;

import com.open.capacity.developer.water.entity.EpnaetPipes;
import com.open.capacity.developer.water.entity.EpnaetPoint;
import com.open.capacity.developer.water.entity.WaterBasicModel;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author DUJIN
 */
public interface IWaterDao {

    /**
     * 插入点位信息数据
     *
     * @param entity
     * @return
     */
    @Insert("INSERT INTO epnaet_point(node_id, node_x,node_y,longitude,latitude,create_time,model_id) VALUES(#{nodeId}, #{nodeX},#{nodeY},#{longitude},#{latitude},#{createTime},#{modelId})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertEpanetPoint(EpnaetPoint entity);


    /**
     * 查询点位信息数据
     *
     * @param nodeId
     * @param modelId
     * @return
     */
    @Select({"<script> SELECT * FROM epnaet_point e WHERE 1=1 " +
            "<if test= \"nodeId != null\">  and e.node_id=#{nodeId} </if> " +
            "<if test= \"modelId!= null\">  and e.model_id=#{modelId} </if> " +
            "</script>"})
    List<EpnaetPoint> queryEpnaetPoint(@Param("nodeId") Integer nodeId, @Param("modelId") Integer modelId);


    /**
     * 插入管线信息数据
     *
     * @param entity
     * @return
     */
    @Insert("INSERT INTO epnaet_pipes(pipes_id,node1_id,node2_id,length,diameter,roughness,minorLoss,model_id,create_time) VALUES(#{pipesId},#{node1Id},#{node2Id},#{length},#{diameter},#{roughness},#{minorLoss},#{modelId},#{createTime,jdbcType =TIMESTAMP})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertEpnaetPipes(EpnaetPipes entity);


    /**
     * 查询管线信息数据
     *
     * @param pipesId
     * @param modelId
     * @return
     */
    @Select({"<script> SELECT * FROM epnaet_pipes e WHERE 1=1 " +
            "<if test= \"pipesId!= null\">  and e.pipes_id=#{pipesId} </if> " +
            "<if test= \"modelId!= null\">  and e.model_id=#{modelId} </if> " +
            "</script>"})
    List<EpnaetPipes> queryEpnaetPipes(@Param("pipesId") Integer pipesId, @Param("modelId") Integer modelId);

    /**
     * 保存模型基础信息
     *
     * @param waterBasicModel
     * @return
     */
    @Insert("INSERT INTO water_basic_model(client_id,dma_id,epnaet_address,node1,node2,gaude1,gaude2) VALUES(#{clientId},#{dmaId},#{epnaetAddress},#{node1},#{node2},#{gaude1},#{gaude2})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertBasicModel(WaterBasicModel waterBasicModel);

    /**
     * 查询水利模型基础信息
     *
     * @param clientId
     * @return
     */
    @Select("SELECT w.*,n.name dma_name FROM water_basic_model w ,n_dma_info n where w.dma_id=n.id AND w.client_id=#{clientId}")
    List<Map<String, Object>> quertWaterBasicInfo(@Param("clientId") Integer clientId);

    /**
     * 查询地图上的设备点位信息
     * @param modelId
     * @return
     */
    @Select("SELECT p.longitude,p.latitude,t.name type_name,i.name device_name,d.device_sn " +
            "FROM  epnaet_point_device d,epnaet_point p ,device_info i,device_type t " +
            "WHERE d.node_id=p.id AND  d.device_sn=i.device_sn AND i.devce_type_id=t.type AND d.model_id=#{modelId}")
    List<Map<String,Object>> queryDeviceMark(@Param("modelId") Integer modelId);


    /**
     * 删除水利模型基础信息,同时关联删除:[管线表]、[点位表]、[点位-设备管理表]
     * @param modelId
     * @return
     */
    @Delete("DELETE w,t,s,d" +
            " FROM  water_basic_model AS w" +
            " LEFT JOIN epnaet_point AS t ON w.id=t.model_id" +
            " LEFT JOIN epnaet_pipes AS s ON w.id=s.model_id" +
            " LEFT JOIN epnaet_point_device AS d ON w.id=d.model_id" +
            " WHERE w.id=#{modelId}")
    int deleteWaterBasciInfo(Integer modelId);


    /**
     * 更新水利模型基础信息
     * @param waterBasicModel
     * @return
     */
    @Update("UPDATE water_basic_model SET node1=#{node1},node2=#{node2},gaude1=#{gaude1},gaude2=#{gaude2} WHERE id=#{id}")
    int updateWaterInfo(WaterBasicModel waterBasicModel);

    /**
     * 更新点位坐标信息
     * @param id
     * @param longitude
     * @param latitude
     * @return
     */
    @Update("UPDATE epnaet_point SET longitude=#{longitude},latitude=#{latitude} WHERE id=#{id}")
    int updatePointInfo(@Param("id")Integer id,@Param("longitude") double longitude,@Param("latitude") double latitude);
}
