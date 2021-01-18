package com.open.capacity.monitor.schedule;

import com.open.capacity.monitor.entity.ServiceHistory;
import com.open.capacity.monitor.entity.ServiceMonitor;
import com.open.capacity.monitor.service.IHistoryService;
import com.open.capacity.monitor.service.IMonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;


@Slf4j
@Component
public class SbScheduleTask {

    @Autowired
    IHistoryService historyService;

    @Autowired
    IMonitorService monitorService;

    @Scheduled(cron = "0 */5 * * * ?")
    public void executeMonitorTask() {
        log.info("开始执行定时任务...");
        List<ServiceMonitor> list = monitorService.findMonitorInfoAll();
        for (ServiceMonitor monitor : list) {
            //请求地址
            String address = monitor.getServiceAddr();
            //请求方式
            String methodName = monitor.getServiceMethod();
            //请求参数
            String serviceParams = monitor.getServiceParams();
            //请求地址封装
            String requestUrl = address + "?" + serviceParams;
            log.info("请求方式：{}", methodName);
            log.info("请求的地址：{}", requestUrl);
            log.info("请求的参数：{}", serviceParams);
            ServiceHistory history = new ServiceHistory();
            history.setServiceId(monitor.getServiceId());
            history.setStartTime(new Date());
            ResponseEntity<String> res;
            RestTemplate restTemplate=new RestTemplate();
            try {
                if ("POST".equals(methodName)) {
                    res = restTemplate.postForEntity(requestUrl, "", String.class);
                    log.info("POST请求结果：{}", res);
                    history.setState(res.getStatusCodeValue());
                    history.setEndTime(new Date());
                    history.setHistoryResult(res.getBody().toString());
                } else if ("GET".equals(methodName)) {
                    res = restTemplate.getForEntity(requestUrl, String.class);
                    log.info("GET请求结果：{}", res);
                    history.setState(res.getStatusCodeValue());
                    history.setEndTime(new Date());
                    history.setHistoryResult(res.getBody());
                }
            } catch (Exception ex) {
                log.error("服务调用异常...");
                //使用捕获异常来处理返回的非200状态的不同响应
                log.error(ex.getMessage());
                //获取接口返回状态码
                history.setState(0);
            }
            log.info("要保存的实体数据：{}", history);
            if (historyService.saveHistoryInfo(history) > 0) {
                log.info("保存历史记录成功！");
                if (history.getState().equals(monitor.getState())) {
                    continue;
                }
                monitor.setServiceAddr(null);
                monitor.setServiceMethod(null);
                monitor.setServiceParams(null);
                monitor.setServiceName(null);
                if (history.getState() == 200) {
                    monitor.setState(200);
                } else {
                    monitor.setState(0);
                }
                if (monitorService.updateMonitorInfo(monitor)>0) {
                    log.info("更新【最新状态】成功~");
                }
            }
        }
    }
}
