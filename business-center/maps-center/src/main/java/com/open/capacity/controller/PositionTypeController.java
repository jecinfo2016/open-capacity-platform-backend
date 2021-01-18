package com.open.capacity.controller;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.capacity.entity.PositionType;
import com.open.capacity.service.PositionTypeService;
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
@RequestMapping("positiontype")
@Api(tags = "管道类型")
public class PositionTypeController {

    @Autowired
    private PositionTypeService positionTypeService;

    /**
     * 获取类型列表
     */
    @ApiOperation(value = "获取类型列表")
    @PostMapping("/positiontypeList")
    @PreAuthorize("hasAnyAuthority('positiontype:get/positiontypes')")
    public PageResult list(@RequestParam Map<String, Object> params){
        PageResult pageResult = positionTypeService.findAll(params);
        return pageResult;
    }


    /**
     * 保存管道类型
     */
    @ApiOperation(value = "保存管道类型")
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('positiontype:post/positiontypes')")
    public Result save(@RequestBody PositionType positionType){
			positionTypeService.save(positionType);
        return Result.succeed("保存成功");
    }

    /**
     * 修改管道类型
     */
    @ApiOperation(value = "修改管道类型")
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('positiontype:put/positiontypes')")
    public Result update(@RequestBody PositionType positionType){
			positionTypeService.update(positionType);

        return Result.succeed("修改成功");
    }

    /**
     * 根据id删除管道类型
     */
    @ApiOperation(value = "根据id删除管道类型")
    @PostMapping("/deleteById/{id}")
    @PreAuthorize("hasAnyAuthority('positiontype:delete/positiontypes/{id}')")
    public Result deleteById(@ApiParam(value = "根据id删除", required = true) @PathVariable Long  id){
			positionTypeService.delete(id);
        return Result.succeed("删除成功");
    }

}
