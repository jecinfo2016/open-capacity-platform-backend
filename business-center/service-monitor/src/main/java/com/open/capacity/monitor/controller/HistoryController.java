package com.open.capacity.monitor.controller;

import com.open.capacity.common.web.Result;
import com.open.capacity.monitor.entity.ServiceHistory;
import com.open.capacity.monitor.service.IHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("history")
@RestController
@Slf4j
public class HistoryController {

    @Autowired
    IHistoryService historyService;

    @PostMapping("/queryHistorys")
    public Result getHistorys(ServiceHistory history, int page, int size) {
        List<ServiceHistory> list = historyService.queryHistoryInfo(history, page, size);
        if (list != null && list.isEmpty()) {
            return Result.succeed(list, "查询成功");
        } else {
            return Result.failed("查询失败");
        }
    }
}
