package com.open.capacity.controller;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.capacity.entity.PositionData;
import com.open.capacity.service.PositionDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * 
 *
 * @author 
 * @email 
 * @date 2020-05-12 10:52:01
 */
@RestController
@RequestMapping("positiondata")
@Api(tags = "管道经纬度信息")
public class PositionDataController {

    @Autowired
    private PositionDataService positionDataService;


    /**
     * 获取管道经纬度列表
     */
    @ApiOperation(value = "获取管道经纬度列表")
    @PostMapping("/positiondataList")
    @PreAuthorize("hasAnyAuthority('positiondata:get/positiondatas')")
    public PageResult list(@RequestParam Map<String, Object> params){
        PageResult pageResult = positionDataService.findAll(params);
        return pageResult;
    }

    /**
     * 获取管道经纬度和类型信息
     */
    @ApiOperation(value = "获取管道经纬度和类型信息")
    @PostMapping("/PipeLineData")
    @PreAuthorize("hasAnyAuthority('positiondata:get/PipeLineDatas')")
    public PageResult<PositionData> getPipeLineData(Map<String, Object> params) {

         PageResult pageResult =  positionDataService.getPipeLineData(params);

        return pageResult;
    }

    /**
     * 保存管道信息
     */
    @ApiOperation(value = "保存管道信息")
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('positiondata:post/PipeLineDatas')")
    public Result save(@RequestBody PositionData positionData){
			positionDataService.save(positionData);

        return Result.succeed("保存成功");
    }

    /**
     * 修改管道信息
     */
    @ApiOperation(value = "修改管道信息")
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('positiondata:put/PipeLineDatas')")
    public Result update(@RequestBody PositionData positionData){
			positionDataService.update(positionData);
        return Result.succeed("修改成功");
    }

    /**
     * 根据id删除管道信息
     */
    @ApiOperation(value = "根据id删除管道信息")
    @PostMapping("/deleteById/{id}")
    @PreAuthorize("hasAnyAuthority('positiondata:delete/PipeLineDatas/{id}')")
    public Result deleteById(@ApiParam(value = "根据id删除", required = true) @PathVariable Long  id){
			positionDataService.delete(id);
        return Result.succeed("删除成功");
    }

}
