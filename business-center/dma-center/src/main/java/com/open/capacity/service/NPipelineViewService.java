package com.open.capacity.service;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.entity.NPipelineView;

import java.util.List;
import java.util.Map;

/**
 * 管线显示
 *
 * @author 
 * @email 
 * @date 2020-05-15 16:38:16
 */
public interface NPipelineViewService {
    /**
     * 添加
     * @param nPipelineView
     */
    int save(NPipelineView nPipelineView);

    /**
     * 修改
     * @param nPipelineView
     */
    int update(NPipelineView nPipelineView);

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
    PageResult<NPipelineView> findAll(Map<String, Object> params);

    /**
     * 根据pipeId查询管线信息
     * @param pipeId
     * @return
     */
    List<NPipelineView> findPipelineBypipeId(Integer pipeId);

    /**
     * 根据管线记录数
     * @return
     */
    int PipelineCount();
}

