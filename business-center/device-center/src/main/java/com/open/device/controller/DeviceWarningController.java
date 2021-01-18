package com.open.device.controller;


import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.device.model.DeviceWarning;
import com.open.device.service.DeviceWarningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("devicewarning")
@Api(tags = "设备告警信息")
public class DeviceWarningController {

    @Autowired
    private DeviceWarningService deviceWarningService;

    /**
     * 列表
     */
    @ApiOperation(value = "设备告警信息列表")
    @PostMapping("/deviceWarningList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @PreAuthorize("hasAnyAuthority('devicewarning:get/deviceWarning')")
    public PageResult deviceWarningList(@RequestParam Map<String, Object> params){
        return deviceWarningService.findAll(params);
    }


    /**
     * 保存
     */
    @ApiOperation(value = "保存设备告警信息")
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('devicewarning:post/deviceWarning')")
    public Result save(DeviceWarning deviceWarning){
			deviceWarningService.save(deviceWarning);

        return Result.succeed("保存成功");
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改设备告警信息")
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('devicewarning:put/deviceWarning')")
    public Result update(DeviceWarning deviceWarning){
			deviceWarningService.update(deviceWarning);

        return Result.succeed("修改成功");
    }

    /**
     * 根据id删除设备告警信息
     */
    @ApiOperation(value = "根据id删除设备告警信息")
    @PostMapping("/deleteDeviceWarningById{id}")
    @PreAuthorize("hasAnyAuthority('devicewarning:delete/deviceWarning/{id}')")
    public Result deleteDeviceWarningById(Long  id){
			deviceWarningService.delete(id);
        return Result.succeed("删除成功");
    }

}
