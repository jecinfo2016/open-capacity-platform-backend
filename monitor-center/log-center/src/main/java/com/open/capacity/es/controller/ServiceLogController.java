package com.open.capacity.es.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.common.model.SysLog;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.log.annotation.LogAnnotation;
import com.open.capacity.log.dao.LogDao;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author zlt
 */
@Slf4j
@RestController
public class ServiceLogController {


    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String ES_PARAM_MESSAGE = "message";
    private static final String ES_PARAM_LOG_LEVEL = "logLevel";
    private static final String ES_PARAM_APP_NAME = "appName";
    private static final String ES_PARAM_CLASSNAME = "classname";
    private static final String ES_PARAM_CONTEXT_TRACE_ID = "contextTraceId";
    private static final String ES_PARAM_CURRENT_TRACE_ID = "currentTraceId";

    @Autowired
    LogDao logService;

    @ApiOperation(value = "系统日志查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/sysLog")
    @LogAnnotation(module = "device-center",recordRequestParam = true)
    public PageResult<SysLog> getPage(@RequestParam Map<String, Object> params) throws JsonProcessingException {
        PageHelper.startPage(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"), true);
        List<SysLog> list = logService.findAll();
        PageInfo<SysLog> pageInfo = new PageInfo(list);
        return PageResult.<SysLog>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }
}
