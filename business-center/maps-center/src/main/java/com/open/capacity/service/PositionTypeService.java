package com.open.capacity.service;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.entity.PositionType;


import java.util.Map;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2020-05-12 10:52:01
 */
public interface PositionTypeService {
    /**
     * 添加
     * @param positionType
     */
    int save(PositionType positionType);

    /**
     * 修改
     * @param positionType
     */
    int update(PositionType positionType);

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
    PageResult<PositionType> findAll(Map<String, Object> params);

}

