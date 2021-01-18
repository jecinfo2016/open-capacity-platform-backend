package com.open.device.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.common.web.PageResult;
import com.open.device.dao.TaskOutputDao;
import com.open.device.model.TaskOutput;
import com.open.device.service.TaskOutputService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaskOutputServiceImpl implements TaskOutputService {

    @Autowired
    private TaskOutputDao taskOutputDao;

    @Override
    public int save(TaskOutput taskOutput) {
        return taskOutputDao.save(taskOutput);
    }

    @Override
    public int delete(Long id) {
        return taskOutputDao.delete(id);
    }

    /**
     * 分页查询任务数据
     * @param
     * @param params
     * @return
     */
    @Override
    public PageResult<TaskOutput> selectByStack(Map<String, Object> params) {
        if (MapUtils.getInteger(params, "page") != null && MapUtils.getInteger(params, "limit") != null)
            PageHelper.startPage(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"), true);
        List<TaskOutput> list = taskOutputDao.selectByStack(params);
        PageInfo<TaskOutput> pageInfo = new PageInfo(list);
        return PageResult.<TaskOutput>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }
}
