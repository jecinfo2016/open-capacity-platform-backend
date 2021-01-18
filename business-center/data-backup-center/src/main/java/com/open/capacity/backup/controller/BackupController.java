package com.open.capacity.backup.controller;

import com.alibaba.fastjson.JSONObject;
import com.open.capacity.backup.entity.JobBackupHistoryInfo;
import com.open.capacity.backup.entity.JobDatasource;
import com.open.capacity.backup.service.IJobDatasourceService;
import com.open.capacity.common.web.PageResult;
import com.open.capacity.common.web.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author Jk
 */
@RestController
@RequestMapping("/backup")
@Slf4j
public class BackupController {

    @Autowired
    IJobDatasourceService jobDatasourceService;

    @Value("${mysql.savePath}")
    private String savePath;

    /**
     * 数据源连接测试
     *
     * @param datasource: jdbc数据源配置实体
     * @return
     */
    @PostMapping("/testJdbcUrl")
    public Result testJdbcUrl(@RequestBody @Valid JobDatasource datasource) throws Exception {
        if (jobDatasourceService.dataSourceTest(datasource)) {
            return Result.succeed("连接成功");
        }
        return Result.failed("连接失败");
    }

    /**
     * 新增-修改数据备份记录
     *
     * @param datasource
     * @return
     */
    @PostMapping("/saveOrUpdateBackupInfo")
    public Result saveBackupInfo(@RequestBody @Valid JobDatasource datasource) {
        if (jobDatasourceService.saveOrUpdateDataSource(datasource)) {
            return Result.succeed("操作成功");
        }
        return Result.failed("操作失败。");
    }

    /**
     * 修改数据备份状态
     *
     * @param datasource
     * @return
     */
    @RequestMapping("/updateStatus")
    public Result updateStatus(@RequestBody JobDatasource datasource) {
        if (datasource.getId() == null || datasource.getJobInfoId() == null || datasource.getStatus() == null) {
            return Result.failed("缺少必传参数");
        }
        if (jobDatasourceService.updateBackupStatus(datasource)) {
            return Result.succeed("操作成功");
        }
        return Result.failed("操作失败。");
    }

    /**
     * 删除数据备份状态
     *
     * @param datasource
     * @return
     */
    @RequestMapping("/deleteBackupInfo")
    public Result deleteBackupInfo(@RequestBody JobDatasource datasource) {
        if (datasource.getId() == null || datasource.getJobInfoId() == null) {
            return Result.failed("缺少必传参数");
        }
        if (jobDatasourceService.deleteBackupInfo(datasource)) {
            return Result.succeed("操作成功");
        }
        return Result.failed("操作失败。");
    }


    /**
     * 获取数据备份列表
     *
     * @return
     */
    @RequestMapping("/queryJobDatasourceList")
    public PageResult<JobDatasource> queryJobDatasourceList(@RequestParam Map<String, Object> params) {
        log.info("请求参数：{}", JSONObject.toJSONString(params));
        PageResult<JobDatasource> jobDatasourcePageResult = jobDatasourceService.queryJobDatasourceList(params);
        return jobDatasourcePageResult;
    }

    /**
     * 查询该任务数据备份历史
     *
     * @param params ：id 主键
     * @return
     */
    @RequestMapping("/queryHistoryList")
    public PageResult<JobBackupHistoryInfo> queryHistoryList(@RequestParam Map<String, Object> params) {
        log.info("请求参数：{}", JSONObject.toJSONString(params));
        return jobDatasourceService.queryHistoryList(params);
    }

    /**
     * 文件下载
     */
    @RequestMapping("/downloadBackupFile")
    public Result downloadBackupFile(@RequestParam String fileName, HttpServletResponse response) {
        log.info("请求参数：fileName={}", fileName);
        //设置要下载的文件路径
        String _fileName = savePath + fileName;
        File file = new File(_fileName);
        if (file.exists()) {
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                //设置强制下载不打开
                response.setContentType("application/force-download");
                // 设置文件名,注意带后缀
                response.setHeader("Content-Disposition", "attachment;fileName="+ URLEncoder.encode(fileName, "utf-8"));
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return Result.succeed("下载成功");
            } catch (Exception e) {
                log.info("下载文件出错");
            }finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return Result.failed("下载失败");
    }
}