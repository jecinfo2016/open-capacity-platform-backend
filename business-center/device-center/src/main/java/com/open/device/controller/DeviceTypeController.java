package com.open.device.controller;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.device.model.DeviceType;
import com.open.device.service.DeviceTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 设备类型
 *
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */
@RestController
@RequestMapping("devicetype")
@Api(tags = "设备类型")
public class DeviceTypeController {

    @Autowired
    private DeviceTypeService deviceTypeService;

    /**
     * 设备类型列表
     */
    @ApiOperation(value = "设备类型列表")
    @PostMapping("/deviceTypeList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @PreAuthorize("hasAuthority('devicetype:get/deviceTypes')")
    public PageResult deviceTypeList(@RequestParam Map<String, Object> params){
        return deviceTypeService.findAll(params);
    }


    /**
     * 保存
     */
    @ApiOperation(value = "保存设备类型")
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('devicetype:post/deviceTypes')")
    public Result save(DeviceType deviceType){
			deviceTypeService.save(deviceType);

        return Result.succeed("保存成功");
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改设备类型")
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('devicetype:put/deviceTypes')")
    public Result update(DeviceType deviceType){
			deviceTypeService.update(deviceType);

        return Result.succeed("修改成功");
    }

    /**
     * 根据id删除设备类型
     */
    @ApiOperation(value = "根据id删除设备类型")
    @PostMapping("/deleteDeviceTypeById{id}")
    @PreAuthorize("hasAnyAuthority('devicetype:delete/deviceTypes/{id}')")
    public Result deleteDeviceTypeById(Long  id){
			deviceTypeService.delete(id);
        return Result.succeed("删除成功");
    }

}
