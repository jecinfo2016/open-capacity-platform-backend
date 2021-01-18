package com.open.capacity.workflow.service;

import com.github.pagehelper.Page;
import com.open.capacity.workflow.constant.PageDomain;
import com.open.capacity.workflow.domain.dto.ActTaskDTO;
import com.open.capacity.workflow.domain.dto.ActWorkflowFormDataDTO;

import java.text.ParseException;
import java.util.List;

public interface IActTaskService {
    public Page<ActTaskDTO> selectProcessDefinitionList(PageDomain pageDomain);
    public List<String>formDataShow(String taskID);
    public int formDataSave(String taskID, List<ActWorkflowFormDataDTO> awfs) throws ParseException;
}
