package com.open.capacity.controller;

import com.open.capacity.common.rest.RestTemplateConfig;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.capacity.entity.GisPipelineStyle;
import com.open.capacity.service.GisPipelineStyleService;
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
@RequestMapping("gispipelinestyle")
@Api(tags = "管线显示样式")
public class GisPipelineStyleController {

    @Autowired
    private GisPipelineStyleService gisPipelineStyleService;

    @Autowired
    private RestTemplateConfig restTemplateConfig;

    /**
     * 管线显示样式列表
     */

    @ApiOperation(value = "获取管线显示样式")
    @PostMapping("/getPipeLineStyle")
    @PreAuthorize("hasAnyAuthority('gispipelinestyle:get/PipeLineStyle')")
    public PageResult getPipeLineStyle(@RequestParam Map<String, Object> params){
        PageResult pageResult = gisPipelineStyleService.getPipeLineStyle(params);
        return pageResult;
    }

    /**
     * 保存管线样式
     */
    @ApiOperation(value = "保存管线样式")
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('gispipelinestyle:post/gispipelinestyles')")
    public Result save(@RequestBody GisPipelineStyle gisPipelineStyle){
			gisPipelineStyleService.save(gisPipelineStyle);

        return Result.succeed("保存成功");
    }

    /**
     * 修改管线样式
     */
    @ApiOperation(value = "修改管线样式")
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('gispipelinestyle:put/gispipelinestyles')")
    public Result update(@RequestBody GisPipelineStyle gisPipelineStyle){
			gisPipelineStyleService.update(gisPipelineStyle);
        return Result.succeed("修改成功");
    }

    /**
     * 根据id删除管线样式
     */
    @ApiOperation(value = "根据id删除管线样式")
    @PostMapping("/deleteGisPipelineStyleById/{id}")
    @PreAuthorize("hasAnyAuthority('gispipelinestyle:delete/gispipelinestyle/{id}')")
    public Result deleteGisPipelineStyleById(@ApiParam(value = "根据id删除", required = true) @PathVariable Long  id){
			gisPipelineStyleService.delete(id);
        return Result.succeed("删除成功");
    }



}
