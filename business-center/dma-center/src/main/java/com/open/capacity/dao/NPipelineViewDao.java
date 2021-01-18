package com.open.capacity.dao;

import com.open.capacity.entity.NPipelineView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
/**
 * 管线显示
 * 
 * @author 
 * @email 
 * @date 2020-05-15 16:38:16
 */
@Mapper
public interface NPipelineViewDao  {

    int save(NPipelineView nPipelineView);

    int update(NPipelineView nPipelineView);

    int delete(Long id);

    List<NPipelineView> findAll(Map<String, Object> params);

    int PipelineCount();

    List<NPipelineView> findPipelineBypipeId(Integer pipeid);

}
