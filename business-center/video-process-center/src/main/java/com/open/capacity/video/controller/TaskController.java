package com.open.capacity.video.controller;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.capacity.video.entity.RecognizeResult;
import com.open.capacity.video.entity.Task;
import com.open.capacity.video.service.TaskService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 任务进度查询, 结果查询
 * 采用异步模式, 用户上传视频后生成taskId, 之后根据taskId进行查询与轮询
 *
 * @author donglh
 * @date 2020/7/15
 */
@Slf4j
@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @ApiOperation("查询task")
    @GetMapping("/query/{taskId}")
    public Result<Task> query(@PathVariable("taskId") String taskId) {
        Task task = taskService.findByTaskId(taskId);
        return Result.succeed(task, "success");
    }


    @ApiOperation("查询识别结果明细")
    @GetMapping("/query/result/{taskId}")
    public PageResult<RecognizeResult> queryRecognizeResult(@PathVariable("taskId") String taskId,
                                                            @RequestParam Map<String, Object> params) {
        return taskService.findRecognizeResult(taskId, params);
    }
}
