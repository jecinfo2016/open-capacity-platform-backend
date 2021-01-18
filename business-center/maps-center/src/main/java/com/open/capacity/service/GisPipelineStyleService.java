package com.open.capacity.service;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.entity.GisPipelineStyle;

import java.util.Map;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2020-05-12 10:52:01
 */
public interface GisPipelineStyleService {
    /**
     * 添加
     * @param gisPipelineStyle
     */
    int save(GisPipelineStyle gisPipelineStyle);

    /**
     * 修改
     * @param gisPipelineStyle
     */
    int update(GisPipelineStyle gisPipelineStyle);

    /**
     * 删除
     * @param id
     */
    int delete(Long id);


    /**
     * 获取管线显示样式
     * @param params
     * @return
     */
    PageResult<GisPipelineStyle> getPipeLineStyle(Map<String, Object> params);


}

