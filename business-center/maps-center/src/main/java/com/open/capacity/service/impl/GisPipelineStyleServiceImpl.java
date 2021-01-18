package com.open.capacity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.dao.GisPipelineStyleDao;
import com.open.capacity.entity.GisPipelineStyle;
import com.open.capacity.service.GisPipelineStyleService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class GisPipelineStyleServiceImpl  implements GisPipelineStyleService {

    @Autowired
    private GisPipelineStyleDao gisPipelineStyleDao;



    /**
     * 添加
     * @param gisPipelineStyle
     */
    public int save(GisPipelineStyle gisPipelineStyle){
        return gisPipelineStyleDao.save(gisPipelineStyle);
    }

    /**
     * 修改
     * @param gisPipelineStyle
     */
    public int update(GisPipelineStyle gisPipelineStyle){
        return gisPipelineStyleDao.update(gisPipelineStyle);
    }


    /**
     * 删除
     * @param id
     */
    public int delete(Long id){
        return gisPipelineStyleDao.delete(id);
    }


    /**
     * 获取管线显示样式
     * @param params
     * @return
     */
    public PageResult<GisPipelineStyle> getPipeLineStyle(Map<String, Object> params){
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        if (MapUtils.getInteger(params, "page")!=null && MapUtils.getInteger(params, "limit")!=null)
            PageHelper.startPage(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"),true);

        List<GisPipelineStyle> list  =  gisPipelineStyleDao.findAll(params);
        PageInfo<GisPipelineStyle> pageInfo = new PageInfo(list);

        return PageResult.<GisPipelineStyle>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }

}
