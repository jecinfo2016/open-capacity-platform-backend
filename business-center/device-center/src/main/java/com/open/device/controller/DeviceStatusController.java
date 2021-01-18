package com.open.device.controller;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.device.model.DeviceStatus;
import com.open.device.service.DeviceStatusService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 设备状态
 *
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */
@RestController
@RequestMapping("devicestatus")
@Api(tags = "设备状态")
public class DeviceStatusController {

    @Autowired
    private DeviceStatusService deviceStatusService;

    /**
     * 查询设备状态列表
     */
    @ApiOperation(value = "查询设备状态列表")
    @PostMapping("/deviceStatusList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @PreAuthorize("hasAuthority('devicestatus:get/deviceStatus')")
    public PageResult deviceStatusList(@RequestParam Map<String, Object> params){
        return deviceStatusService.findAll(params);
    }


    /**
     * 保存设备状态
     */
    @ApiOperation(value = "保存设备状态")
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('devicestatus:post/deviceStatus')")
    public Result save(DeviceStatus deviceStatus){
			deviceStatusService.save(deviceStatus);

        return Result.succeed("保存成功");
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改设备状态")
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('devicestatus:put/deviceStatus')")
    public Result update(DeviceStatus deviceStatus){
			deviceStatusService.update(deviceStatus);

        return Result.succeed("修改成功");
    }

    /**
     * 根据id删除设备状态
     */
    @ApiOperation(value = "根据id删除设备状态")
    @PostMapping("/deleteDeviceStatusById{id}")
    @PreAuthorize("hasAnyAuthority('devicestatus:delete/deviceStatus/{id}')")
    public Result deleteDeviceStatusById(@ApiParam(value = "根据id删除", required = true) Long  id){
			deviceStatusService.delete(id);
        return Result.succeed("删除成功");
    }

}
