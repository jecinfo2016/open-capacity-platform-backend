package com.open.capacity.client.controller;

import com.open.capacity.client.entity.BlackList;
import com.open.capacity.client.service.BlackListService;
import com.open.capacity.common.web.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/black")
public class BlackListController {

    @Autowired
    private BlackListService BlackListService;

    //增加白名单
    @PostMapping("/add")
    public Result add(@RequestBody BlackList BlackList) {
        BlackListService.insert(BlackList);
        return Result.succeed("保存成功");
    }

    //更新白名单
    @PostMapping("/update")
    public Result update(@RequestBody BlackList BlackList) {
        BlackListService.updateByPrimaryKey(BlackList);
        return Result.succeed("修改成功");
    }

    //删除路由
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        BlackListService.deleteByPrimaryKey(id);
        return Result.succeed("删除成功");
    }

    //获取全部数据
    @GetMapping("/findAll")
    public List<BlackList> findAll(){
        return BlackListService.findAll();
    }

}
