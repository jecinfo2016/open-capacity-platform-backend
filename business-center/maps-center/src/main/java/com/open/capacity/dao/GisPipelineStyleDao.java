package com.open.capacity.dao;

import com.open.capacity.entity.GisPipelineStyle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
/**
 * 
 * 
 * @author 
 * @email 
 * @date 2020-05-12 10:52:01
 */
@Mapper
public interface GisPipelineStyleDao  {

    int save(GisPipelineStyle gisPipelineStyle);

    int update(GisPipelineStyle gisPipelineStyle);

    int delete(Long id);

    List<GisPipelineStyle> findAll(Map<String, Object> params);


}
