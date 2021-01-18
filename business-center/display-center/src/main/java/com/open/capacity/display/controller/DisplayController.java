package com.open.capacity.display.controller;

import com.open.capacity.common.web.Result;
import com.open.capacity.display.entity.DisplayBaseInfo;
import com.open.capacity.display.service.IDisplayService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author DUJIN
 * @Classname DisplayController
 * @Date 2020/9/11 10:42
 */
@RestController
@RequestMapping("/display")
public class DisplayController {

    @Autowired
    IDisplayService displayService;

    /**
     * 查询连接下,所有的数据库
     *
     * @return
     */
    @GetMapping("/queryDatabases")
    public Result queryDatabases() {
        return Result.succeed(displayService.queryDatabases(), "查询成功");
    }

    /**
     * 根据数据库名称查询所有的表
     *
     * @return
     */
    @GetMapping("/queryTablesByDbname")
    public Result queryTablesByDbname(@RequestParam String dbName) {
        return Result.succeed(displayService.queryTablesByDbname(dbName), "查询成功");
    }

    /**
     * 根据数据库名称和表名称查询所有的字段
     *
     * @param dbName
     * @param tableName
     * @return
     */
    @GetMapping("/queryColumnsByTableName")
    public Result queryColumnsByTableName(@RequestParam String dbName, @RequestParam String tableName) {
        return Result.succeed(displayService.queryColumnsByTableName(dbName, tableName), "查询成功");
    }

    /**
     * 更修或修改
     *
     * @param displayBaseInfo
     * @return
     */
    @PostMapping("/saveOrUpdateDisplayInfo")
    public Result saveOrUpdateDisplayInfo(@Valid @RequestBody DisplayBaseInfo displayBaseInfo) {
        if (displayService.saveOrUpdateDisplayInfo(displayBaseInfo) > 0) {
            return Result.succeed("操作成功");
        }
        return Result.failed("操作失败");
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @GetMapping("/deleteDisplayInfo/{id}")
    public Result deleteDisplayInfo(@PathVariable("id") Integer id) {
        if (displayService.deleteDisplayInfo(id) > 0) {
            return Result.succeed("操作成功");
        }
        return Result.failed("操作失败");
    }

    /**
     * 查询展示列表
     *
     * @param displayBaseInfo
     * @return
     */
    @PostMapping("/queryDisplayInfo")
    public Result queryDisplayInfo(DisplayBaseInfo displayBaseInfo) {
        return Result.succeed(displayService.queryDisplayInfo(displayBaseInfo), "查询成功");
    }

    @PostMapping("/queryDataInfo")
    public Result queryData(@RequestParam("databaseName") String databaseName,
                            @RequestParam("tableName") String tableName,
                            @RequestParam("columnName") String columnName,
                            @RequestParam(required = false) String mode) {
        if (StringUtils.isEmpty(databaseName) || StringUtils.isEmpty(tableName) || StringUtils.isEmpty(columnName)){
            return Result.failed("缺少必传参数");
        }
        return Result.succeed(displayService.queryDataList(databaseName,tableName,columnName,mode),"查询成功");
    }
}
