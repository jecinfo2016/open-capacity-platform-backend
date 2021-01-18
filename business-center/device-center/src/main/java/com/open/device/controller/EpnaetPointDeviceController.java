package com.open.device.controller;

import com.open.capacity.common.web.Result;
import com.open.device.model.EpnaetPointDevice;
import com.open.device.service.EpnaetPointDeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/point-device")
@Api("开发者平台节点设备配置")
@Slf4j
public class EpnaetPointDeviceController {

    @Autowired
    private EpnaetPointDeviceService service;

    /**
     * 保存设备信息
     */
    @ApiOperation(value = "保存设节点设备配置")
    @PostMapping("/save")
    public Result save(EpnaetPointDevice epnaetPointDevice) {
        int i = service.configurationDevice(epnaetPointDevice);
        if (i==0){
            return Result.failed("保存失败");
        }
        return Result.succeed("保存成功");
    }

    /**
     * 查询水力模型设备列表
     */
    @ApiOperation(value = "根据ModelId查询水力模型设备列表")
    @PostMapping("/listByModelId")
    public Result list(@ApiParam(value = "根据modelId查询", required = true)Integer modelId) {
        List<EpnaetPointDevice> list = service.modelDeviceList(modelId);
        return Result.succeed(list,"请求成功");
    }


    /**
     * 删除
     */
    @ApiOperation(value = "删除设备信息")
    @PostMapping("/deleteById")
    public Result deleteInfoById(@ApiParam(value = "根据id删除", required = true)  Integer id) {
        service.deleteById(id);
        return Result.succeed("删除成功");
    }
}
