package com.open.capacity.video.controller;

import com.alibaba.fastjson.JSONObject;
import com.open.capacity.common.web.Result;
import com.open.capacity.video.service.CollectorService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.rmi.ServerException;

/**
 * @author donglh
 * @date 2020/7/13
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/video")
@Api(value = "视频上传", tags = "视频上传")
public class VideoUploadController {

    private final CollectorService collectorService;

    @ResponseBody
    @PostMapping(value = "/upload")
    public Result<Object> uploadFile(@RequestParam("file") MultipartFile file) throws ServerException {
        String taskId = collectorService.videoCollector(file);
        JSONObject task = new JSONObject();
        task.put("taskId", taskId);
        return Result.succeed(task, "success");
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
