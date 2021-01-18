package com.open.capacity.developer.dma.controller;


import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.capacity.developer.dma.entity.NDmaInfo;
import com.open.capacity.developer.dma.service.NDmaInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author
 * @email
 * @date 2020-05-15 16:38:16
 */
@RestController
@RequestMapping("ndmainfo")
@Api(tags = "dam分区")
public class NDmaInfoController {

    @Autowired
    private NDmaInfoService nDmaInfoService;

    /**
     * 查询dam分区列表
     */
    @ApiOperation(value = "查询dam分区列表")
    @PostMapping("/nDmaInfoList")
    public PageResult nDmaInfoList(@RequestParam Map<String, Object> params) {
        return nDmaInfoService.findAll(params);
    }

    /**
     * 查询dma-列表（树形）
     */
    @ApiOperation(value = "查询dam分区树形图")
    @PostMapping("/getDmaTree")
    public Result getDmaTree() {
        return Result.succeed(nDmaInfoService.queryTreeList(), "");
    }

    /**
     * 保存dam分区
     */
    @ApiOperation(value = "保存dam分区")
    @PostMapping("/save")
    public Result save(@RequestBody NDmaInfo nDmaInfo) {
        nDmaInfoService.save(nDmaInfo);

        return Result.succeed("保存成功");
    }

    /**
     * 修改dam分区
     */
    @ApiOperation(value = "修改dam分区")
    @PostMapping("/update")
    public Result update(@RequestBody NDmaInfo nDmaInfo) {
        nDmaInfoService.update(nDmaInfo);

        return Result.succeed("修改成功");
    }

    /**
     * 根据id删除dam分区
     */
    @ApiOperation(value = "根据id删除dam分区")
    @PostMapping("/deleteDmaInfoById/{id}")
    public Result deleteDmaInfoById(@ApiParam(value = "根据id删除dam分区", required = true) @PathVariable Long id) {
        nDmaInfoService.delete(id);
        return Result.succeed("删除成功");
    }

    /**
     * 上移下移
     * 0上  1下
     */
    @ApiOperation(value = "上移下移")
    @PostMapping("/move")
    public Result move(@RequestBody NDmaInfo nDmaInfo) {
        nDmaInfoService.moveDma(nDmaInfo);
        return Result.succeed("删除成功");
    }


}
