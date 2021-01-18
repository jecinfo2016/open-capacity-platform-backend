package com.open.device.controller;


import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.device.model.DevicePower;
import com.open.device.service.DevicePowerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 
 *
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */
@RestController
@RequestMapping("devicepower")
@Api(tags = "设备用电信息")
public class DevicePowerController {

    @Autowired
    private DevicePowerService devicePowerService;

    /**
     * 设备用电信息列表
     */
    @ApiOperation(value = "设备用电信息列表")
    @PostMapping("/devicePowerList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @PreAuthorize("hasAuthority('Devicepower:get/devicePowers')")
    public PageResult devicePowerList(@RequestParam Map<String, Object> params){
        return devicePowerService.findAll(params);
    }


    /**
     * 保存设备用电信息
     */
    @ApiOperation(value = "保存设备用电信息")
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('devicepower:post/devicePowers')")
    public Result save(DevicePower devicePower){
			devicePowerService.save(devicePower);

        return Result.succeed("保存成功");
    }

    /**
     * 修改设备用电信息
     */
    @ApiOperation(value = "修改设备用电信息")
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('devicepower:put/devicePowers')")
    public Result update(DevicePower devicePower){
			devicePowerService.update(devicePower);

        return Result.succeed("修改成功");
    }

    /**
     * 根据id删除设备用电信息
     */
    @ApiOperation(value = "根据id删除设备用电信息")
    @PostMapping("/deleteDevicePowerById{id}")
    @PreAuthorize("hasAnyAuthority('devicepower:delete/devicePowers/{id}')")
    public Result deleteDevicePowerById(@ApiParam(value = "根据id删除", required = true) Long  id){
			devicePowerService.delete(id);
        return Result.succeed("删除成功");
    }

}
