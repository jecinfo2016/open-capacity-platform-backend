package com.open.capacity.service;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.entity.PositionData;

import java.util.Map;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2020-05-12 10:52:01
 */
public interface PositionDataService {
    /**
     * 添加
     * @param positionData
     */
    int save(PositionData positionData);

    /**
     * 修改
     * @param positionData
     */
    int update(PositionData positionData);

    /**
     * 删除
     * @param id
     */
    int delete(Long id);


    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<PositionData> findAll(Map<String, Object> params);

    /**
     * 管线位置和类型
     * @param params
     * @return
     */
    PageResult<PositionData> getPipeLineData(Map<String, Object> params);

}

