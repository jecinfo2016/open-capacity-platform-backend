package com.open.capacity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.dao.PositionDataDao;
import com.open.capacity.entity.PositionData;
import com.open.capacity.service.PositionDataService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class PositionDataServiceImpl  implements PositionDataService {

    @Autowired
    private PositionDataDao positionDataDao;

    /**
     * 添加
     * @param positionData
     */
    public int save(PositionData positionData){
        return positionDataDao.save(positionData);
    }

    /**
     * 修改
     * @param positionData
     */
    public int update(PositionData positionData){
        return positionDataDao.update(positionData);
    }


    /**
     * 删除
     * @param id
     */
    public int delete(Long id){
        return positionDataDao.delete(id);
    }


    /**
     * 列表
     * @param params
     * @return
     */
    public PageResult<PositionData> findAll(Map<String, Object> params){
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        if (MapUtils.getInteger(params, "page")!=null && MapUtils.getInteger(params, "limit")!=null)
            PageHelper.startPage(MapUtils.getInteger(params, "page"),MapUtils.getInteger(params, "limit"),true);

        List<PositionData> list  =  positionDataDao.findAll(params);
        PageInfo<PositionData> pageInfo = new PageInfo(list);

        return PageResult.<PositionData>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }

    /**
     * 管线位置和类型
     * @param params
     * @return
     */
    @Override
    public PageResult<PositionData> getPipeLineData(Map<String, Object> params) {
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        if (MapUtils.getInteger(params, "page")!=null && MapUtils.getInteger(params, "limit")!=null)
            PageHelper.startPage(MapUtils.getInteger(params, "page"),MapUtils.getInteger(params, "limit"),true);

        List<PositionData> list  =  positionDataDao.findByDataAndType(params);
        PageInfo<PositionData> pageInfo = new PageInfo(list);

        return PageResult.<PositionData>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }

}
