package com.open.device.controller;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.device.model.TaskOutput;
import com.open.device.service.TaskOutputService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("TaskOutput")
@Api(tags = "设备任务输出")
@Slf4j
public class TaskOutputController {

    @Autowired
    private TaskOutputService taskOutputService;

    /**
     * 保存设备任务数据
     */
    @ApiOperation(value = "保存设备任务数据")
    @PostMapping("/saveTaskOutput")
    @PreAuthorize("hasAnyAuthority('taskoutput:post/TaskOutputs')")
    public Result save(TaskOutput taskOutput) {
        int save = taskOutputService.save(taskOutput);
        if (save==0){
            return Result.failed("保存失败");
        }
        return Result.succeed("保存成功");
    }


    /**
     * 删除
     */
    @ApiOperation(value = "删除设备信息")
    @PostMapping("/deleteTaskOutput{id}")
    @PreAuthorize("hasAnyAuthority('taskoutput:delete/TaskOutputs/{id}')")
    public Result deleteTaskOutput(@ApiParam(value = "根据id删除", required = true)  Long id) {
        int delete = taskOutputService.delete(id);
        if (delete==0){
            return Result.failed("删除失败");
        }
        return Result.succeed("删除成功");
    }



    /**
     * 查询设备信息列表
     */
    @ApiOperation(value = "查询设备任务输出列表")
    @PostMapping("/deviceTaskList")
    public Result deviceTaskList(@RequestParam Map<String, Object> params) {
        PageResult<TaskOutput> list=null;
        if (params!=null && !params.isEmpty()){
            list= taskOutputService.selectByStack(params);
        }else{
            log.info("缺少必传参数:taskId");
            return Result.failed("缺少必传参数:taskId");
        }
        return Result.succeed(list,"请求成功");
    }
}
