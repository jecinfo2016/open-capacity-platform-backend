package com.open.capacity.message.controller;

import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import com.open.capacity.message.model.Message;
import com.open.capacity.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 查询消息信息列表
     */
    @PostMapping("/list")
    public PageResult DeviceInfoList(@RequestBody Message message) {
        PageResult<Message> pageResult = messageService.findAll(message);
        return pageResult;
    }

    /**
     * 查询应用名称
     */
    @PostMapping("/appnames")
    public Result findAppNames(){
        List<Message> appNameList = messageService.findAppName();
        return Result.succeed(appNameList,"查询应用名称");
    }

    /**
     * 添加消息
     */
    @PostMapping("/save")
    public Result save(@RequestBody Message message){

        messageService.save(message);
        return Result.succeed("保存成功");
    }

    /**
     * 修改消息
     */
    @PostMapping("/update")
    public Result update(@RequestBody Message message){

        messageService.update(message);
        return Result.succeed("修改成功");
    }


    /**
     * 删除消息
     */
    @PostMapping("/delete")
    public Result delete(@RequestParam Long id){

        messageService.delete(id);
        return Result.succeed("删除成功");
    }

    /**
     * 统计消息
     */
    @PostMapping("/count")
    public Result countByType(@RequestParam(value = "type", required = false) Integer type){

        int result = messageService.countByType(type);
        return Result.succeed(String.valueOf(result));
    }
}
