package com.open.capacity.backup.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.open.capacity.backup.config.XxlJobConfig;
import com.open.capacity.backup.dao.BackupDao;
import com.open.capacity.backup.entity.JobBackupHistoryInfo;
import com.open.capacity.backup.entity.JobDatasource;
import com.open.capacity.backup.entity.JobParam;
import com.open.capacity.backup.exception.ApiException;
import com.open.capacity.backup.service.IJobDatasourceService;
import com.open.capacity.backup.tool.BaseQueryTool;
import com.open.capacity.backup.tool.MysqlQueryTool;
import com.open.capacity.common.web.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;


/**
 * @author Jk
 */
@Service
@Slf4j
public class JobDatasourceServiceImpl implements IJobDatasourceService {

    @Autowired
    BackupDao backupDao;

    @Autowired
    XxlJobConfig jobConfig;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public Boolean dataSourceTest(JobDatasource jdbcDatasource) {
        BaseQueryTool queryTool = null;
        try {
            queryTool = new MysqlQueryTool(jdbcDatasource);
        } catch (Exception e) {
            log.error("JDBC连接参数配置错误，获取数据源失败....");
            throw new RuntimeException("JDBC连接参数配置错误，获取数据源失败....");
        }
        return queryTool.dataSourceTest();
    }

    @Override
    public Boolean saveOrUpdateDataSource(JobDatasource jdbcDatasource) {
        Boolean backupFlag = false;
        JSONObject jobResult = null;
        //新增
        if (jdbcDatasource.getId() == null) {
            if (backupDao.insertBackupInfo(jdbcDatasource) > 0) {
                jobResult = dispatchInsertJobCenter(jdbcDatasource);
                if ("200".equals(jobResult.getString("code"))) {
                    // 回写主键
                    jdbcDatasource.setJobInfoId(Long.parseLong(jobResult.getString("content")));
                    if (backupDao.updateBackUpInfo(jdbcDatasource) > 0) {
                        log.info("回调更新主键成功，jobInfoId：{}", jdbcDatasource.getJobInfoId());
                        backupFlag = true;
                    }
                }
            }
        } else {
            if (backupDao.updateBackUpInfo(jdbcDatasource) > 0) {
                log.info("阶段1-更新任务调度表信息成功");
                jobResult = dispatchUpdateJobCenter(jdbcDatasource);
                if ("200".equals(jobResult.getString("code"))) {
                    log.info("更新任务调度记录成功,jobInfoId:{}", jdbcDatasource.getJobInfoId());
                }
            }
        }
        return backupFlag;
    }

    /**
     * 更新数据备份记录的状态
     *
     * @return
     */
    @Override
    public Boolean updateBackupStatus(JobDatasource jdbcDatasource) {
        Boolean flag = false;
        //更新状态
        log.info("id:{}", jdbcDatasource.getId());
        log.info("jobInfoId:{}", jdbcDatasource.getJobInfoId());
        String address = "";
        if (backupDao.updateBackUpInfo(jdbcDatasource) > 0) {
            switch (jdbcDatasource.getStatus()) {
                //0停止
                case 0:
                    address = "/jobinfo/stop";
                    break;
                //1运行
                case 1:
                    address = "/jobinfo/start";
                    break;
                default:
                    log.info("状态不匹配");
                    break;
            }
            JSONObject mapOject = dispatchStartOrStopStatus(jdbcDatasource, address);
            if (mapOject != null && "200".equals(mapOject.getString("code"))) {
                log.info("开启调度任务成功,id:{},jobInfoId:{}", jdbcDatasource.getId(), jdbcDatasource.getJobInfoId());
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public PageResult<JobDatasource> queryJobDatasourceList(Map<String, Object> params) {
        if (MapUtils.getInteger(params, "page") != null && MapUtils.getInteger(params, "limit") != null) {
            PageHelper.startPage(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"), true);
        }
        List<JobDatasource> jobDatasources = backupDao.queryJobDatasourceList(params);
        PageInfo<JobDatasource> pageInfo = new PageInfo(jobDatasources);
        return PageResult.<JobDatasource>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }

    @Override
    public PageResult<JobBackupHistoryInfo> queryHistoryList(Map<String, Object> params) {
        if (MapUtils.getInteger(params, "page") != null && MapUtils.getInteger(params, "limit") != null) {
            PageHelper.startPage(MapUtils.getInteger(params, "page"), MapUtils.getInteger(params, "limit"), true);
        }
        List<JobBackupHistoryInfo> historyList = backupDao.queryHistoryList(params);
        PageInfo<JobBackupHistoryInfo> pageInfo = new PageInfo(historyList);
        return PageResult.<JobBackupHistoryInfo>builder().data(pageInfo.getList()).code(0).count(pageInfo.getTotal()).build();
    }


    @Override
    public boolean deleteBackupInfo(JobDatasource datasource) {
        boolean flag = false;
        JSONObject jobResult = new JSONObject();
        if (backupDao.deleteBackupInfo(datasource.getId()) > 0) {
            jobResult = dispatchDeleteJobCenter(datasource);
            if ("200".equals(jobResult.getString("code"))) {
                flag = true;
            }
        }
        return flag;
    }


    /**
     * 请求调度中心-执行添加
     *
     * @param jdbcDatasource
     * @return
     */
    public JSONObject dispatchInsertJobCenter(JobDatasource jdbcDatasource) {
        JSONObject result = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        //任务执行参数
        JobParam jobParam = new JobParam();
        jobParam.setJdbcUrl(jdbcDatasource.getJdbcUrl());
        jobParam.setJdbcUsername(jdbcDatasource.getJdbcUsername());
        jobParam.setJdbcPassword(jdbcDatasource.getJdbcPassword());
        //任务主键
        jobParam.setJjdId(jdbcDatasource.getId());
        map.add("jobGroup", jobConfig.getAppid().toString());
        map.add("jobCron", jdbcDatasource.getCronStr());
        map.add("jobDesc", jdbcDatasource.getComments());
        map.add("author", "admin");
        map.add("executorRouteStrategy", "FIRST");
        map.add("executorHandler", jobConfig.getHandlerName());
        map.add("executorParam", JSONObject.toJSONString(jobParam));
        map.add("executorBlockStrategy", "SERIAL_EXECUTION");
        map.add("glueType", "BEAN");
        HttpEntity<MultiValueMap<String, String>> request;
        request = new HttpEntity<>(map, headers);
        String mapRes = null;
        try {
            mapRes = restTemplate.postForObject(jobConfig.getAdminAddresses() + "/jobinfo/add", request, String.class);
            log.info("调度中心返回结果：{}", mapRes);
            result = JSONObject.parseObject(mapRes);
        } catch (RestClientException e) {
            log.info("调用任务调度中心-新增失败...");
            throw new ApiException("调用任务调度中心-新增失败");
        }
        return result;
    }

    /**
     * 请求调度中心-执行修改
     *
     * @param jdbcDatasource
     * @return
     */
    public JSONObject dispatchUpdateJobCenter(JobDatasource jdbcDatasource) {
        JSONObject result = new JSONObject();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        //任务执行参数
        JobParam jobParam = new JobParam();
        jobParam.setJdbcUrl(jdbcDatasource.getJdbcUrl());
        jobParam.setJdbcUsername(jdbcDatasource.getJdbcUsername());
        jobParam.setJdbcPassword(jdbcDatasource.getJdbcPassword());
        jobParam.setJjdId(jdbcDatasource.getId());
        map.add("id", jdbcDatasource.getJobInfoId().toString());
        map.add("jobGroup", jobConfig.getAppid().toString());
        map.add("jobCron", jdbcDatasource.getCronStr());
        map.add("jobDesc", jdbcDatasource.getComments());
        map.add("author", "admin");
        map.add("executorRouteStrategy", "FIRST");
        map.add("executorHandler", jobConfig.getHandlerName());
        map.add("executorParam", JSONObject.toJSONString(jobParam));
        map.add("executorBlockStrategy", "SERIAL_EXECUTION");
        map.add("glueType", "BEAN");
        HttpEntity<MultiValueMap<String, String>> request;
        request = new HttpEntity<>(map, headers);
        String mapRes = null;
        try {
            mapRes = restTemplate.postForObject(jobConfig.getAdminAddresses() + "/jobinfo/update", request, String.class);
            log.info("调度中心返回结果：{}", mapRes);
            result = JSONObject.parseObject(mapRes);
        } catch (RestClientException e) {
            log.info("调用任务调度中心失败...");
            throw new ApiException("调用任务调度中心-修改失败");
        }
        return result;
    }

    /**
     * 请求调度中心-删除调度纪录
     *
     * @param jdbcDatasource
     * @return
     */
    public JSONObject dispatchDeleteJobCenter(JobDatasource jdbcDatasource) {
        JSONObject result = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id", jdbcDatasource.getJobInfoId().toString());
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        String mapRes = null;
        try {
            mapRes = restTemplate.postForObject(jobConfig.getAdminAddresses() + "/jobinfo/remove", request, String.class);
            log.info("调度中心返回结果：{}", mapRes);
            result = JSONObject.parseObject(mapRes);
        } catch (RestClientException e) {
            log.info("调用任务调度中心-执行修改失败...");
            throw new ApiException("调用任务调度中心删除失败");
        }
        return result;
    }


    /**
     * 请求调度中心-调度开关
     *
     * @param jdbcDatasource
     * @return
     */
    public JSONObject dispatchStartOrStopStatus(JobDatasource jdbcDatasource, String address) {
        JSONObject result = null;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id", jdbcDatasource.getJobInfoId().toString());
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        String mapRes = null;
        try {
            mapRes = restTemplate.postForObject(jobConfig.getAdminAddresses() + address, request, String.class);
            log.info("调度中心返回结果：{}", mapRes);
            result = JSONObject.parseObject(mapRes);
        } catch (RestClientException e) {
            log.info("调用任务调度中心失败...");
            throw new ApiException("调用任务调度中心失败");
        }
        return result;
    }
}