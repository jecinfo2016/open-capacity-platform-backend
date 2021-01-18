package com.open.capacity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.dao.PositionTypeDao;
import com.open.capacity.entity.PositionType;
import com.open.capacity.service.PositionTypeService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class PositionTypeServiceImpl  implements PositionTypeService {

    @Autowired
    private PositionTypeDao positionTypeDao;

    /**
     * 添加
     * @param positionType
     */
    public int save(PositionType positionType){
        return positionTypeDao.save(positionType);
    }

    /**
     * 修改
     * @param positionType
     */
    public int update(PositionType positionType){
        return positionTypeDao.update(positionType);
    }


    /**
     * 删除
     * @param id
     */
    public int delete(Long id){
        return positionTypeDao.delete(id);
    }


    /**
     * 列表
     * @param params
     * @return
     */
    public PageResult<PositionType> findAll(Map<String, Object> params){
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        if (MapUtils.getInteger(params, "page")!=null && MapUtils.getInteger(params, "limit")!=null)
            PageHelper.startPage(MapUtils.getInteger(params, "page"),MapUtils.getInteger(params, "limit"),true);

        List<PositionType> list  =  positionTypeDao.findAll(params);
        PageInfo<PositionType> pageInfo = new PageInfo(list);

        return PageResult.<PositionType>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }

}
