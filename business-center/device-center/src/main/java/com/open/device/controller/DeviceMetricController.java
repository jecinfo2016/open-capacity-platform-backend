package com.open.device.controller;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.device.model.DeviceMetric;
import com.open.device.service.DeviceMetricService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("devicemetric")
@Api(tags = "设备度量")
public class DeviceMetricController {

    @Autowired
    private DeviceMetricService deviceMetricService;

    /**
     * 查询设备度量列表
     */
    @ApiOperation(value = "查询设备度量列表")
    @PostMapping("/findAll")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    public PageResult findAll(@RequestParam Map<String, Object> params){
        return deviceMetricService.findAll(params);
    }

    /**
     * 保存
     */
    @ApiOperation(value = "保存设备度量")
    @PostMapping("/save")
    public Result save(@RequestBody DeviceMetric deviceMetric){
        deviceMetricService.save(deviceMetric);
        return Result.succeed("保存成功");
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改设备度量")
    @PostMapping("/update")
    public Result update(@RequestBody DeviceMetric deviceMetric){
        deviceMetricService.update(deviceMetric);
        return Result.succeed("修改成功");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "根据id删除设备度量")
    @PostMapping("/delete/{id}")
    public Result deleteById(@ApiParam(value = "根据id删除", required = true) @PathVariable Long  id){
        deviceMetricService.delete(id);
        return Result.succeed("删除成功");
    }
}
