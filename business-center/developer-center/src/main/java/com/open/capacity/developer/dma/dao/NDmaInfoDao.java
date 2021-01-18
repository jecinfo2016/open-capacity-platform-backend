package com.open.capacity.developer.dma.dao;

import com.open.capacity.developer.dma.entity.NDmaInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author 
 * @email 
 * @date 2020-05-15 16:38:16
 */
@Mapper
public interface NDmaInfoDao  {

    int save(NDmaInfo nDmaInfo);

    int update(NDmaInfo nDmaInfo);

    int delete(Long id);

    List<NDmaInfo> findAll(Map<String, Object> params);

    List<NDmaInfo> queryTreeList(Integer parentId);

    int moveDmaById(NDmaInfo nDmaInfo);

    NDmaInfo queryByParentIdLast(Integer parentId);
}
