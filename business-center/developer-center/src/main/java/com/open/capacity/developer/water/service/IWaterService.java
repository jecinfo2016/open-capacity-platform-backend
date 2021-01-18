package com.open.capacity.developer.water.service;

import com.alibaba.fastjson.JSONObject;
import com.open.capacity.developer.exception.MyException;
import com.open.capacity.developer.water.entity.WaterBasicModel;

import java.util.List;
import java.util.Map;

/**
 * @author DUJIN
 */
public interface IWaterService {

    /**
     * 保存水利模型信息
     * @param basicModel
     * @return
     * @throws MyException：自定义异常
     */
    int insertWaterModel(WaterBasicModel basicModel) throws MyException;

    /**
     * 查询水利模型基础信息
     * @param clientId
     * @return
     */
    List<Map<String,Object>> quertWaterBasicInfo(Integer clientId);

    /**
     * 查询高德地图
     * @param modelId
     * @return
     */
    JSONObject queryWaterGuides(Integer modelId);

    /**
     * 删除水利模型信息
     * @param modelId
     * @param fileAddress
     * @return
     */
    int deleteWaterBasciInfo(Integer modelId,String fileAddress);

    boolean updateWaterBasicInfo(WaterBasicModel waterBasicModel);
}
