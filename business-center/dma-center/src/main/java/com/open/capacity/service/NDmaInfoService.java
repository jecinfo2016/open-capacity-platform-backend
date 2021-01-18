package com.open.capacity.service;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.entity.NDmaInfo;


import java.util.Map;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2020-05-15 16:38:16
 */
public interface NDmaInfoService {
    /**
     * 添加
     * @param nDmaInfo
     */
    int save(NDmaInfo nDmaInfo);

    /**
     * 修改
     * @param nDmaInfo
     */
    int update(NDmaInfo nDmaInfo);

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
    PageResult<NDmaInfo> findAll(Map<String, Object> params);

}

