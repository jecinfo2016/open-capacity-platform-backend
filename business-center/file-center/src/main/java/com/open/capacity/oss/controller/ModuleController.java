package com.open.capacity.oss.controller;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.capacity.oss.model.Catalog;
import com.open.capacity.oss.model.ModuleInfo;
import com.open.capacity.oss.service.CatalogService;
import com.open.capacity.oss.service.ModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("module")
@Api(tags = "MODULE API")
public class ModuleController {

    @Autowired
    ModuleService moduleService;

    @Autowired
    private CatalogService catalogService;
    /**
     * 查询列表
     */
    @ApiOperation(value = "获取列表", notes = "根据分页等查询条件查询列表")
    @PostMapping("/list")
    public PageResult getModuleList(@RequestBody ModuleInfo moduleInfo) {
        PageResult<ModuleInfo> pageResult = moduleService.findAll(moduleInfo);
        return pageResult;
    }

    /**
     * 查询单个
     */
    @ApiOperation(value = "根据id查询模块详情")
    @PostMapping("/get")
    public Result getById(@RequestParam Integer Id){
        ModuleInfo moduleInfo = moduleService.getById(Id);
        return Result.succeed(moduleInfo,"");
    }

    /**
     * 添加
     */
    @ApiOperation(value = "新增模块")
    @PostMapping("/save")
    public Result save(@RequestBody ModuleInfo moduleInfo){

        moduleService.save(moduleInfo);
        return Result.succeed("保存成功");
    }

    /**
     * 修改
     */
    @ApiOperation(value = "修改模块")
    @PostMapping("/update")
    public Result update(@RequestBody ModuleInfo moduleInfo){

        moduleService.update(moduleInfo);
        return Result.succeed("修改成功");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除模块")
    @PostMapping("/delete")
    public Result delete(@RequestParam Integer Id){

        moduleService.delete(Id);
        return Result.succeed("删除成功");
    }


    /**
     * 获取文档目录
     */
    @ApiOperation(value = "获取文档目录")
    @PostMapping("/getCatalog")
    public Result getCatalog(@RequestParam String id){
        Catalog catalog = catalogService.findCatalog(id);
        return Result.succeed(catalog,"");
    }

}
