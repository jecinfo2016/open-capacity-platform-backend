package com.open.capacity.workflow.service.impl;

import com.open.capacity.workflow.constant.DateUtils;
import com.open.capacity.workflow.domain.ActWorkflowFormData;
import com.open.capacity.workflow.mapper.ActWorkflowFormDataMapper;
import com.open.capacity.workflow.service.IActWorkflowFormDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 动态单Service业务层处理
 * 
 * @author danny
 * @date 2020-11-02
 */
@Service
public class ActWorkflowFormDataServiceImpl implements IActWorkflowFormDataService
{
    @Autowired
    private ActWorkflowFormDataMapper actWorkflowFormDataMapper;

    /**
     * 查询动态单
     * 
     * @param id 动态单ID
     * @return 动态单
     */
    @Override
    public ActWorkflowFormData selectActWorkflowFormDataById(Long id)
    {
        return actWorkflowFormDataMapper.selectActWorkflowFormDataById(id);
    }

    @Override
    public List<ActWorkflowFormData> selectActWorkflowFormDataByBusinessKey(String businessKey){
        return actWorkflowFormDataMapper.selectActWorkflowFormDataByBusinessKey(businessKey);
    }

    /**
     * 查询动态单列表
     * 
     * @param ActWorkflowFormData 动态单
     * @return 动态单
     */
    @Override
    public List<ActWorkflowFormData> selectActWorkflowFormDataList(ActWorkflowFormData ActWorkflowFormData)
    {
        return actWorkflowFormDataMapper.selectActWorkflowFormDataList(ActWorkflowFormData);
    }

    /**
     * 新增动态单
     * 
     * @param ActWorkflowFormData 动态单
     * @return 结果
     */
    @Override
    public int insertActWorkflowFormData(ActWorkflowFormData ActWorkflowFormData)
    {
        ActWorkflowFormData.setCreateTime(DateUtils.getNowDate());
        return actWorkflowFormDataMapper.insertActWorkflowFormData(ActWorkflowFormData);
    }

    @Override
    public int insertActWorkflowFormDatas(List<ActWorkflowFormData> ActWorkflowFormDatas) {
        return actWorkflowFormDataMapper.insertActWorkflowFormDatas("", ActWorkflowFormDatas, new Date(),"");
    }


    /**
     * 修改动态单
     * 
     * @param ActWorkflowFormData 动态单
     * @return 结果
     */
    @Override
    public int updateActWorkflowFormData(ActWorkflowFormData ActWorkflowFormData)
    {
        return actWorkflowFormDataMapper.updateActWorkflowFormData(ActWorkflowFormData);
    }

    /**
     * 批量删除动态单
     * 
     * @param ids 需要删除的动态单ID
     * @return 结果
     */
    @Override
    public int deleteActWorkflowFormDataByIds(Long[] ids)
    {
        return actWorkflowFormDataMapper.deleteActWorkflowFormDataByIds(ids);
    }

    /**
     * 删除动态单信息
     * 
     * @param id 动态单ID
     * @return 结果
     */
    @Override
    public int deleteActWorkflowFormDataById(Long id)
    {
        return actWorkflowFormDataMapper.deleteActWorkflowFormDataById(id);
    }
}
