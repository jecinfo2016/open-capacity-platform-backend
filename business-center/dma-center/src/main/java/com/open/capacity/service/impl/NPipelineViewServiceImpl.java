package com.open.capacity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.dao.NPipelineViewDao;
import com.open.capacity.entity.NPipelineView;
import com.open.capacity.service.NPipelineViewService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class NPipelineViewServiceImpl  implements NPipelineViewService {

    @Autowired
    private NPipelineViewDao nPipelineViewDao;

    /**
     * 添加
     * @param nPipelineView
     */
    public int save(NPipelineView nPipelineView){
        return nPipelineViewDao.save(nPipelineView);
    }

    /**
     * 修改
     * @param nPipelineView
     */
    public int update(NPipelineView nPipelineView){
        return nPipelineViewDao.update(nPipelineView);
    }


    /**
     * 删除
     * @param id
     */
    public int delete(Long id){
        return nPipelineViewDao.delete(id);
    }


    /**
     * 列表
     * @param params
     * @return
     */
    public PageResult<NPipelineView> findAll(Map<String, Object> params){
        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
        if (MapUtils.getInteger(params, "page")!=null && MapUtils.getInteger(params, "limit")!=null)
            PageHelper.startPage(MapUtils.getInteger(params, "page"),MapUtils.getInteger(params, "limit"),true);

        List<NPipelineView> list  =  nPipelineViewDao.findAll(params);
        PageInfo<NPipelineView> pageInfo = new PageInfo(list);

        return PageResult.<NPipelineView>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }

    /**
     * 根据pipeId查询管线信息
     * @param pipeId
     * @return
     */
    @Override
    public List<NPipelineView> findPipelineBypipeId(Integer pipeId) {
        return nPipelineViewDao.findPipelineBypipeId(pipeId);
    }

    @Override
    public int PipelineCount() {

        return nPipelineViewDao.PipelineCount();
    }

}
