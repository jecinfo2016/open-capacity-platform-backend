package com.open.device.controller;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.device.model.DeviceInstallInfo;
import com.open.device.service.DeviceAppService;
import com.open.device.service.DeviceInstallInfoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 设备安装信息
 *
 * @author 
 * @email 
 * @date 2020-05-08 10:42:41
 */
@RestController
@RequestMapping("deviceinstallinfo")
@Api(tags = "设备安装信息")
public class DeviceInstallInfoController {

    @Autowired
    private DeviceInstallInfoService deviceInstallInfoService;

    @Autowired
    private DeviceAppService deviceAppService;

    /**
     * 设备安装信息列表
     */
    @ApiOperation(value = "设备安装信息列表")
    @PostMapping("/DeviceInstallInfoList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "limit",value = "分页结束位置", required = true, dataType = "Integer")
    })
    @PreAuthorize("hasAuthority('deviceinstallinfo:get/deviceInstallInfo')")
    public PageResult deviceInstallInfoList(@RequestParam Map<String, Object> params){
        return deviceInstallInfoService.findAll(params);
    }


    /**
     * 保存设备安装信息
     */
    @ApiOperation(value = "保存设备安装信息")
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('deviceinstallinfo:post/deviceInstallInfo')")
    public Result save(DeviceInstallInfo deviceInstallInfo){
			deviceInstallInfoService.save(deviceInstallInfo);
        return Result.succeed("保存成功");
    }

    /**
     * 修改设备安装信息
     */
    @ApiOperation(value = "修改设备安装信息")
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('deviceinstallinfo:put/deviceInstallInfo')")
    public Result update(DeviceInstallInfo deviceInstallInfo){
			deviceInstallInfoService.update(deviceInstallInfo);

        return Result.succeed("修改成功");
    }

    /**
     * 根据id删除设备安装信息
     */
    @ApiOperation(value = "根据id删除设备安装信息")
    @PostMapping("/deleteDeviceInstallInfoById{id}")
    @PreAuthorize("hasAnyAuthority('deviceinstallinfo:delete/deviceInstallInfo/{id}')")
    public Result deleteDeviceInstallInfoById(@ApiParam(value = "根据id删除", required = true) Long  id){
			deviceInstallInfoService.delete(id);
        return Result.succeed("删除成功");
    }

    /**
     * 更新设备安装信息
     * @param deviceInstallInfo
     * @return
     */
    @ApiOperation(value = "更新或修改设备安装信息")
    @PostMapping("/saveOrUpdate")
    @PreAuthorize("hasAnyAuthority('deviceinstallinfo:post/deviceInstallInfo','deviceinstallinfo:put/deviceInstallInfo')")
    public Result saveOrUpdate(DeviceInstallInfo deviceInstallInfo) {
            deviceInstallInfoService.saveOrUpdate(deviceInstallInfo);
            return Result.succeed("操作成功");
    }
}
