package com.open.capacity.developer.device.controller;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.capacity.developer.device.entity.DevDeviceRelation;
import com.open.capacity.developer.device.entity.DeviceInfo;
import com.open.capacity.developer.device.entity.DeviceType;
import com.open.capacity.developer.device.service.IDevDeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author DUJIN
 */
@RestController
@RequestMapping("/dev-device")
@Api("开发者平台设备管理")
@Slf4j
public class DevDeviceController {

    @Autowired
    IDevDeviceService devDeviceService;


    /**
     * 新增开发者平台-设备管理
     *
     * @param deviceRelation
     * @return
     * @throws Exception
     */
    @PostMapping("/addDevDeviceInfo")
    @ApiOperation(value = "新增开发者平台-设备关联信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "clientId", value = "应用ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "appId", value = "应用标识", required = true, dataType = "String"),
            @ApiImplicitParam(name = "dmaId", value = "DMA分区ID", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "deviceId", value = "设备ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "deviceSn", value = "设备编码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "installAddress", value = "安装地点", required = true, dataType = "String")
    })
    public Result addDevDeviceInfo(@RequestBody @Valid DevDeviceRelation deviceRelation) {
        if (devDeviceService.queryDeviceInfoById(deviceRelation.getClientId(), deviceRelation.getDeviceId()) != null) {
            return Result.failed("该应用已关联此设备,无需重复操作。");
        }
        int i = devDeviceService.saveDevDeviceInfo(deviceRelation);
        if (i > 0) {
            log.info("新增设备关联信息成功");
            return Result.succeed("新增设备关联信息成功");
        } else {
            return Result.failed("新增设备关联信息失败");
        }
    }


    /**
     * clientId,deviceTypeId,dmaId,deviceSn,deviceName
     *
     * @param params
     * @return
     */
    @PostMapping("/queryDevDeviceInfo")
    @ApiOperation(value = "查询开发者平台设备列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页起始位置", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "limit", value = "分页结束位置", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "clientId", value = "应用ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "deviceTypeId", value = "设备类型ID", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "dmaId", value = "DMA分区ID", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "deviceSn", value = "设备编码", required = false, dataType = "String"),
            @ApiImplicitParam(name = "deviceName", value = "设备名称", required = false, dataType = "String")
    })
    public Result queryDeviceInfoByParams(@RequestParam Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return Result.failed("参数异常");
        }
        if (params.get("clientId") == null || "".equals(params.get("clientId"))) {
            return Result.failed("参数【clientId】必传");
        }
        PageResult pageResult = devDeviceService.queryDeviceInfoByParams(params);
        return Result.succeed(pageResult, "查询成功");
    }

    /**
     * 查询所有的设备类型信息
     *
     * @return
     */
    @PostMapping("/queryDeviceTypeInfo")
    @ApiOperation(value = "查询平台所有的设备类型信息")
    public Result queryDeviceTypeInfoAll() {
        List<DeviceType> deviceTypes = devDeviceService.queryDeviceTypeAll();
        return Result.succeed(deviceTypes, "查询成功");
    }

    @PostMapping("/queryDeviceInfoList")
    @ApiOperation(value = "查询平台所有的设备信息")
    public Result queryDeviceInfoList(@RequestParam Map<String, Object> map) {
        List<DeviceInfo> list = devDeviceService.queryDeviceInfoList(map);
        return Result.succeed(list, "查询成功");
    }

    /**
     * 根据ID，删除设备关联信息
     *
     * @param relationId
     * @return
     */
    @PostMapping("/deleteDeviceRelationInfo")
    @ApiOperation(value = "删除设备关联信息")
    @ApiImplicitParams(
            {@ApiImplicitParam(name = "relationId", value = "设备关联ID", required = true, dataType = "Integer")
            })
    public Result deleteDeviceRelation(@RequestParam("relationId") Integer relationId) {
        if (relationId == null) {
            return Result.failed("relationId参数不能为空");
        }
        int i = devDeviceService.deleteDeviceRelationInfo(relationId);
        if (i > 0) {
            log.info("删除设备关联信息成功，id=【{}】", relationId);
            return Result.succeed("删除设备关联信息成功");
        } else {
            log.info("删除设备关联信息失败~");
            return Result.failed("未找到此记录，id=" + relationId);
        }
    }


    /**
     * 修改设备关联信息
     *
     * @param deviceRelation
     * @return
     */
    @PostMapping("/updateDeviceRelationInfo")
    @ApiOperation(value = "修改设备关联信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "设备关联ID", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "installAddress", value = "安装地点", required = false, dataType = "String"),
            @ApiImplicitParam(name = "dmaId", value = "设备关联ID", required = false, dataType = "Integer")
    })
    public Result updateDeviceRelation(DevDeviceRelation deviceRelation) {
        if (deviceRelation == null || deviceRelation.getId() == null) {
            return Result.failed("传输参数有误");
        }
        int row = devDeviceService.updateDevDeviceRelation(deviceRelation);
        if (row > 0) {
            log.info("更新设备关联信息成功，id=【{}】", deviceRelation.getId());
            return Result.succeed("更新设备关联信息成功");
        } else {
            log.info("更新设备关联信息失败~");
            return Result.failed("未找到此记录，id=" + deviceRelation.getId());
        }
    }
}
