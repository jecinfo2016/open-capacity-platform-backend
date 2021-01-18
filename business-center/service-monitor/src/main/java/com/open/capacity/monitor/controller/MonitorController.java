package com.open.capacity.monitor.controller;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.capacity.monitor.entity.ServiceMonitor;
import com.open.capacity.monitor.service.IHistoryService;
import com.open.capacity.monitor.service.IMonitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.commons.lang3.StringUtils.*;

@RestController
@RequestMapping("monitor")
@Api(tags = "服务监控平台相关接口")
public class MonitorController {

    @Autowired
    IMonitorService monitorService;

    @Autowired
    IHistoryService historyService;

    /**
     * 保存服务监控信息
     *
     * @param monitor
     * @return
     */
    @PostMapping("/saveMonitorInfo")
    @ApiOperation("添加服务监控信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serviceName", value = "服务名称", required = true),
            @ApiImplicitParam(name = "serviceAddr", value = "服务地址", required = true),
            @ApiImplicitParam(name = "serviceMethod", value = "调用方式", required = true),
            @ApiImplicitParam(name = "serviceParams", value = "请求参数", required = false)
    })
    public Result saveMonitorInfo(ServiceMonitor monitor) {
        if (monitor == null || monitor.getServiceName() == null || monitor.getServiceAddr() == null || monitor.getServiceMethod() == null) {
            return Result.failed("缺少必要参数");
        }
        if (isEmpty(monitor.getServiceName()) || isEmpty(monitor.getServiceAddr()) || isEmpty(monitor.getServiceMethod())) {
            return Result.failed("参数不能为空");
        }
        ServiceMonitor monitorVo = new ServiceMonitor();
        monitorVo.setServiceName(monitor.getServiceName());
        PageResult<ServiceMonitor> pageResult = monitorService.queryMonitorInfo(monitorVo, 1, 1);
        if (pageResult != null && !pageResult.getData().isEmpty()) {
            return Result.failed("服务名称已经存在");
        }
        int i = monitorService.saveMonitorInfo(monitor);
        if (i > 0) {
            return Result.succeed("保存服务监控信息成功");
        } else {
            return Result.failed("保存服务监控信息失败~,请重新尝试");
        }
    }


    /**
     * 查询服务监控信息
     *
     * @param monitor
     * @return
     */
    @PostMapping("/queryMonitorInfo")
    @ApiOperation("查询服务监控信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serviceId", value = "服务ID", required = false),
            @ApiImplicitParam(name = "serviceName", value = "服务名称", required = false),
            @ApiImplicitParam(name = "serviceAddr", value = "服务地址", required = false),
            @ApiImplicitParam(name = "serviceMethod", value = "调用方式", required = false),
            @ApiImplicitParam(name = "state", value = "状态", required = false)
    })
    public PageResult<ServiceMonitor> queryMonitorInfo(ServiceMonitor monitor, @RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "20") int size) {
        return monitorService.queryMonitorInfo(monitor, page, size);
    }


    /**
     * 删除服务监控信息
     *
     * @param serviceId
     * @return
     */
    @PostMapping("/deleteMonitorInfo")
    @ApiOperation("删除服务监控信息的接口")
    @ApiImplicitParams({@ApiImplicitParam(name = "serviceId", value = "服务ID", required = true)})
    public Result deleteMonitorInfoById(Integer serviceId) {
        int i = monitorService.deleteMonitorInfo(serviceId);
        if (i > 0) {
            return Result.succeed("删除成功");
        } else {
            return Result.failed("删除失败，请重新尝试");
        }
    }

    /**
     * 更新服务监控信息
     *
     * @param monitor
     * @return
     */
    @PostMapping("/updateMonitorInfo")
    @ApiOperation("更新服务监控信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serviceId", value = "服务ID", required = true),
            @ApiImplicitParam(name = "serviceName", value = "服务名称", required = false),
            @ApiImplicitParam(name = "serviceAddr", value = "服务地址", required = false),
            @ApiImplicitParam(name = "serviceMethod", value = "调用方式", required = false),
            @ApiImplicitParam(name = "serviceParams", value = "请求参数", required = false),
    })
    public Result updteMonitorInfo(ServiceMonitor monitor) {
        if (monitor == null || monitor.getServiceId() == null) {
            return Result.failed("缺少必要参数,服务ID不能为空");
        }
        //服务名称
        String monitorServiceName = monitor.getServiceName();
        if (!"".equals(monitorServiceName)) {
            //构造查询对象
            ServiceMonitor monitorVo = new ServiceMonitor();
            monitorVo.setServiceId(monitor.getServiceId());
            monitorVo.setServiceName(monitorServiceName);
            if (monitorService.findMonitorInfoByIdAndName(monitorVo) != null) {
                return Result.failed("服务名称已经存在");
            }
        }
        int i = monitorService.updateMonitorInfo(monitor);
        if (i > 0) {
            return Result.succeed("更新服务监控信息成功");
        } else {
            return Result.failed("更新服务监控信息失败,请重新尝试");
        }
    }

}
