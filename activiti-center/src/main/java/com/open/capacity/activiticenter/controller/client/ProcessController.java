package com.open.capacity.activiticenter.controller.client;


import com.open.capacity.activiticenter.service.ModelService;
import com.open.capacity.activiticenter.service.ProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Api(description = "流程实例相关", tags = {"ProcessController"})
@RestController
@RequestMapping("models")
@CrossOrigin(origins = "*")
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @ApiOperation(value = "启动流程")
    @PostMapping(value = "startProcess")
    public ResponseEntity<?> startProcessInstance(@RequestParam(value = "key") String key) {

        HashMap<String, Object> responseBody = processService.startProcessInstance(key);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @ApiOperation(value = "查询定义的流程列表")
    @PostMapping(value = "processList")
    public ResponseEntity<?> processList() {

        HashMap<String, Object> responseBody = processService.processList();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @ApiOperation(value = "查看流程图")
    @PostMapping(value = "viewProcess")
    public ResponseEntity<?> viewProcess(@RequestParam(value = "Id") String Id) {

        HashMap<String, Object> responseBody = processService.viewProcess(Id);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
