package com.open.device.controller;


import com.open.capacity.common.auth.details.LoginAppUser;
import com.open.capacity.common.util.SysUserUtil;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.device.model.DeviceManufacturer;
import com.open.device.service.DeviceManufacturerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 设备厂家信息
 *
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */
@RestController
@RequestMapping("devicemanufacturer")
@Api(tags = "设备厂家信息")
public class DeviceManufacturerController {

    @Autowired
    private DeviceManufacturerService deviceManufacturerService;

    /**
     * 厂家信息列表
     */
    @ApiOperation(value = "厂家信息列表")
    @PostMapping("/deviceManufacturerList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @PreAuthorize("hasAuthority('deviceManufacturer:get/DeviceManufacturer')")
    public PageResult deviceManufacturerList(@RequestParam Map<String, Object> params){
        return deviceManufacturerService.findAll(params);
    }


    /**
     * 保存
     */
    @ApiOperation(value = "保存厂家信息")
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('deviceManufacturer:post/deviceManufacturer')")
    public Result save(DeviceManufacturer deviceManufacturer){
        LoginAppUser user = SysUserUtil.getLoginAppUser();
        String username = user.getUsername();
        deviceManufacturer.setCreateBy(username);
			deviceManufacturerService.save(deviceManufacturer);
        return Result.succeed("保存成功");
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改厂家信息")
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('deviceManufacturer:put/deviceManufacturer')")
    public Result update(DeviceManufacturer deviceManufacturer){
        LoginAppUser user = SysUserUtil.getLoginAppUser();
        String username = user.getUsername();
        deviceManufacturer.setUpdateBy(username);
			deviceManufacturerService.update(deviceManufacturer);
        return Result.succeed("修改成功");
    }

    /**
     * 根据ID删除设备厂家信息
     */
    @ApiOperation(value = "根据ID删除设备厂家信息")
    @PostMapping("/deleteDeviceManufacturerById{id}")
    @PreAuthorize("hasAnyAuthority('deviceManufacturer:delete/deviceManufacturer/{id}')")
    public Result deleteDeviceManufacturerById(@ApiParam(value = "根据id删除", required = true) Long  id){
			deviceManufacturerService.delete(id);
        return Result.succeed("删除成功");
    }

}
