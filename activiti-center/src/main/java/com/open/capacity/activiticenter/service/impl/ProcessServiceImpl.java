package com.open.capacity.activiticenter.service.impl;

import com.open.capacity.activiticenter.constant.ResponseConstantManager;
import com.open.capacity.activiticenter.service.ProcessService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.NativeProcessDefinitionQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

@Service
public class ProcessServiceImpl implements ProcessService {

    private static final Logger logger = LoggerFactory.getLogger(ProcessServiceImpl.class);

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    RepositoryService repositoryService;

    @Override
    public HashMap<String, Object> startProcessInstance(String key) {
        HashMap<String, Object> result = new HashMap<>();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key);
        result.put("status", ResponseConstantManager.STATUS_SUCCESS);
        result.put("processId", processInstance.getId());
        logger.info("当前实例信息： deployId:" + processInstance.getDeploymentId() + ",processInstanceId:" + processInstance.getId() + ",流程定义id:" + processInstance.getProcessDefinitionId());
        return result;
    }

    @Override
    public HashMap<String, Object> processList() {
        HashMap<String, Object> result = new HashMap<>();
        try {
            NativeProcessDefinitionQuery nativeProcessDefinitionQuery = repositoryService.createNativeProcessDefinitionQuery();
            List<ProcessDefinition> list = nativeProcessDefinitionQuery.list();
            result.put("status", ResponseConstantManager.STATUS_SUCCESS);
            result.put("processId", list);
        } catch (Exception e) {
            result.put("status", ResponseConstantManager.STATUS_FAIL);
            result.put("message", e.toString());
        }
        return result;
    }

    @Override
    public HashMap<String, Object> viewProcess(String Id) {
        HashMap<String, Object> result = new HashMap<>();
        try {
            List<String> list = repositoryService.getDeploymentResourceNames(Id);
            String resourceName = "";
            if (null != list && list.size() > 0) {
                for (String s : list) {
                    if (s.indexOf(".png") >= 0) {
                        resourceName = s;
                    }
                }
            }
            //获取图片输入流
            InputStream in = repositoryService.getResourceAsStream(Id, resourceName);
            File file = new File("D://" + resourceName);
            FileUtils.copyInputStreamToFile(in, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
