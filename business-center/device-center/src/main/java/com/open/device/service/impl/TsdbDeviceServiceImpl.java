package com.open.device.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.open.device.config.XxlJobConfig;
import com.open.device.dao.TsdbDeviceDao;
import com.open.device.model.*;
import com.open.device.service.TsdbDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author DUJIN
 */
@Service
@Slf4j
public class TsdbDeviceServiceImpl implements TsdbDeviceService {

  @Autowired TsdbDeviceDao tsdbDeviceDao;

  @Autowired
  @Qualifier("restTemplateNew")
  private RestTemplate restTemplate;

  @Autowired
  XxlJobConfig xxlJobConfig;


  public static final String JOB_MESSAGE="调度中心返回结果:{}";

  public static final String TOKEN_NAME="XXL-JOB-ACCESS-TOKEN";

  @Override
  public List<String> queryAggregators() {
    return tsdbDeviceDao.queryAggregators();
  }

  @Override
  public List<DeviceType> queryEquipmentType() {
    return tsdbDeviceDao.queryEquipmentType();
  }

  @Override
  public List<EquipmentTsdbTag> queryEquipmentTsdbTag(Integer deviceType) {
    return tsdbDeviceDao.queryEquipmentTsdbTag(deviceType);
  }

  @Override
  public List<DeviceInfo> queryDeviceListByType(Integer deviceType) {
    return tsdbDeviceDao.queryDeviceListByType(deviceType);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public int insertDeviceTask(EquipmentTsdbTask task) {
    int row = 0;
    if (tsdbDeviceDao.insertDeviceTask(task) > 0) {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      headers.add(TOKEN_NAME, xxlJobConfig.getAccessToken());
      MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
      map.add("jobGroup", xxlJobConfig.getAppid());
      map.add("jobCron", task.getCronStr());
      map.add("jobDesc", task.getTaskDesc());
      map.add("author", "admin");
      map.add("executorRouteStrategy", "FIRST");
      map.add("executorHandler", xxlJobConfig.getHandlerName());
      // 任务参数
      // aggregator、start_time、device_type、equipment_tag_id、end_time、equipment_sns、downsample_unit、downsample_time
      RequestModel requestModel = new RequestModel();
      requestModel.setStart(task.getStartTime());
      if (StringUtils.isNotEmpty(task.getEndTime())) {
        requestModel.setEnd(Long.parseLong(task.getEndTime()));
      }
      Querie querie = new Querie();
      querie.setAggregator(task.getAggregator());
      String metric = tsdbDeviceDao.queryMetricByType(task.getDeviceType());
      log.info("查询出来的上报主题为：{}", metric);
      querie.setMetric(metric);
      // 采样方式：采样时间-采样方式-补值策略【这里默认是zero，还可以是nan，null】
      if (StringUtils.isNotEmpty(task.getDownsampleTime()) && StringUtils.isNotEmpty(task.getDownsampleUnit())){
        String downsample =task.getDownsampleTime()
                + task.getDownsampleUnit()
                + "-"
                + task.getAggregator()
                + "-zero";
        querie.setDownsample(downsample);
      }
      // 采集标志
      Tags tags = new Tags();
      // 统计字段-必有
      tags.setIndex(task.getEquipmentTag());
      // 统计设备-不一定有
      if(StringUtils.isNotEmpty(task.getEquipmentSns())){
        tags.setHost(task.getEquipmentSns());
      }
      querie.setTags(tags);
      requestModel.setQueries(Arrays.asList(querie));
      //任务执行参数
      JobParam jobParam=new JobParam();
      jobParam.setRequestModel(requestModel);
      jobParam.setTaskId(task.getId());
      jobParam.setEquipmentTag(task.getEquipmentTag());
      map.add("executorParam", JSON.toJSONString(jobParam));
      map.add("executorBlockStrategy", "SERIAL_EXECUTION");
      map.add("glueType", "BEAN");
      HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
      String mapRes =
          restTemplate.postForObject(xxlJobConfig.getAdminAddresses() + "/jobinfo/add", request, String.class);
      log.info(JOB_MESSAGE, mapRes);
      JSONObject mapOject = JSONObject.parseObject(mapRes);
      if ("200".equals(mapOject.getString("code"))) {
        // 回写主键
        String jobInfoId = mapOject.getString("content");
        int i = tsdbDeviceDao.updateJobInfoId(task.getId(), Integer.parseInt(jobInfoId));
        if (i > 0) {
          log.info("回调更新任务主键成功，jobInfoId=【{}】", jobInfoId);
          row++;
        }
      }
    }
    return row;
  }

  @Override
  public List<Map<String, String>> queryTaskList(Map<String, String> params) {
    return tsdbDeviceDao.queryTaskList(params);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public int deleteTaskInfo(Integer id, Integer jobInfoId) {
    int result = 0;
    int row = tsdbDeviceDao.deleteTaskInfo(id);
    if (row > 0) {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      headers.add(TOKEN_NAME, xxlJobConfig.getAccessToken());
      MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
      map.add("id", jobInfoId);
      HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
      String mapRes =
          restTemplate.postForObject(xxlJobConfig.getAdminAddresses() + "/jobinfo/remove", request, String.class);
      log.info("调度中心返回结果如下：{}", mapRes);
      JSONObject mapOject = JSONObject.parseObject(mapRes);
      if ("200".equals(mapOject.getString("code"))) {
        log.info("删除任务调度记录成功，id=【{}】", id);
        result++;
      }
    }
    return result;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public int updateTaskInfo(EquipmentTsdbTask task) {
    int result = 0;
    int row = tsdbDeviceDao.updateTaskInfo(task);
    if (row > 0) {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      headers.add(TOKEN_NAME, xxlJobConfig.getAccessToken());
      MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
      // 必传id
      map.add("id", task.getJobInfoId());
      map.add("jobGroup", xxlJobConfig.getAppid());
      map.add("jobCron", task.getCronStr());
      map.add("jobDesc", task.getTaskDesc());
      map.add("author", "admin");
      map.add("executorRouteStrategy", "FIRST");
      map.add("executorHandler", xxlJobConfig.getHandlerName());
      map.add("updateTime", new Date());
      // 执行参数
      RequestModel model = new RequestModel();
      model.setStart(task.getStartTime());
      if (StringUtils.isNotEmpty(task.getEndTime())) {
        model.setEnd(Long.parseLong(task.getEndTime()));
      }
      Querie querie = new Querie();
      // 聚合方式
      querie.setAggregator(task.getAggregator());
      // 设备类型-metric
      String metric = tsdbDeviceDao.queryMetricByType(task.getDeviceType());
      log.info("查询出来的上报主题为：{}", metric);
      querie.setMetric(metric);
      // 采样方式：采样时间-采样方式-补值策略【这里默认是zero，还可以是nan，null】
      if (StringUtils.isNotEmpty(task.getDownsampleTime()) && StringUtils.isNotEmpty(task.getDownsampleUnit())){
        String downsample =task.getDownsampleTime()
                + task.getDownsampleUnit()
                + "-"
                + task.getAggregator()
                + "-zero";
        querie.setDownsample(downsample);
      }
      // 采集标志
      Tags tags = new Tags();
      // 统计字段
      tags.setIndex(task.getEquipmentTag());
      // 统计设备-不一定有
      if(StringUtils.isNotEmpty(task.getEquipmentSns())){
        tags.setHost(task.getEquipmentSns());
      }
      querie.setTags(tags);
      model.setQueries(Arrays.asList(querie));
      //任务执行参数
      JobParam jobParam=new JobParam();
      jobParam.setRequestModel(model);
      jobParam.setTaskId(task.getId());
      map.add("executorParam", JSON.toJSONString(jobParam));
      map.add("executorBlockStrategy", "SERIAL_EXECUTION");
      map.add("glueType", "BEAN");
      HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
      String mapRes =
          restTemplate.postForObject(xxlJobConfig.getAdminAddresses() + "/jobinfo/update", request, String.class);
      log.info(JOB_MESSAGE, mapRes);
      JSONObject mapOject = JSONObject.parseObject(mapRes);
      if (mapOject != null && "200".equals(mapOject.getString("code"))) {
        result++;
        log.info("更新任务调度记录成功~");
      }
    }
    return result;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public int updateTaskFlag(Integer taskId,String flag) {
    int result = 0;
    //首先去校验是否已经是开启状态
    EquipmentTsdbTask equipmentTsdbTask = tsdbDeviceDao.queryTaskInfoById(taskId);
    if (equipmentTsdbTask==null || equipmentTsdbTask.getJobInfoId()==null){
      log.info("该条调度任务不存在,id:{}",taskId);
      return result;
    }
    int row = tsdbDeviceDao.updateTaskFlag(taskId,flag);
    // 开启任务调度
    if (row > 0) {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
      headers.add(TOKEN_NAME, xxlJobConfig.getAccessToken());
      MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
      map.add("id", equipmentTsdbTask.getJobInfoId());
      HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(map, headers);
      String mapRes = "";
      JSONObject mapOject = null;
      // 开启调度
      if ("1".equals(flag)) {
        mapRes =
            restTemplate.postForObject(xxlJobConfig.getAdminAddresses() + "/jobinfo/start", request, String.class);
        log.info(JOB_MESSAGE, mapRes);
        mapOject = JSONObject.parseObject(mapRes);
        if (mapOject != null && "200".equals(mapOject.getString("code"))) {
          result++;
          log.info("开启调度任务成功,id:{},jobInfoId:{}", taskId, equipmentTsdbTask.getJobInfoId());
        }
      } else if ("0".equals(flag)) {
        mapRes =
            restTemplate.postForObject(xxlJobConfig.getAdminAddresses() + "/jobinfo/stop", request, String.class);
        log.info(JOB_MESSAGE, mapRes);
        mapOject = JSONObject.parseObject(mapRes);
        if (mapOject != null && "200".equals(mapOject.getString("code"))) {
          result++;
          log.info("终止调度任务成功,id:{},jobInfoId:{}", taskId, equipmentTsdbTask.getJobInfoId());
        }
      }
    }
    return result;
  }
}
