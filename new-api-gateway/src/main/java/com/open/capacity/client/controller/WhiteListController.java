package com.open.capacity.client.controller;

import com.open.capacity.client.entity.WhiteList;
import com.open.capacity.client.service.WhiteListService;
import com.open.capacity.common.web.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/white")
public class WhiteListController {

    @Autowired
    private WhiteListService whiteListService;

    //增加白名单
    @PostMapping("/add")
    public Result add(@RequestBody WhiteList whiteList) {
        whiteListService.insert(whiteList);
        return Result.succeed("保存成功");
    }

    //更新白名单
    @PostMapping("/update")
    public Result update(@RequestBody WhiteList whiteList) {
        whiteListService.updateByPrimaryKey(whiteList);
        return Result.succeed("修改成功");
    }

    //删除路由
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        whiteListService.deleteByPrimaryKey(id);
        return Result.succeed("删除成功");
    }

    //获取全部数据
    @GetMapping("/findAll")
    public List<WhiteList> findAll(){
        return whiteListService.findAll();
    }

    //同步redis数据 从mysql中同步过去
    @GetMapping("/synchronization")
    public Result  synchronization() {
        whiteListService.synchronization();
        return Result.succeed("redis同步成功");
    }
}
