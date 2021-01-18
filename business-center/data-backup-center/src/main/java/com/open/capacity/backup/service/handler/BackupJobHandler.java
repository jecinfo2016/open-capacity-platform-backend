package com.open.capacity.backup.service.handler;

import com.alibaba.fastjson.JSONObject;
import com.open.capacity.backup.dao.BackupDao;
import com.open.capacity.backup.entity.JobBackupHistoryInfo;
import com.open.capacity.backup.entity.JobParam;
import com.open.capacity.backup.util.MySQLDBUtil;
import com.open.capacity.common.util.DateUtils;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * XxlJob开发示例（Bean模式）
 * <p>
 * 开发步骤：
 * 1、在Spring Bean实例中，开发Job方法，方式格式要求为 "public ReturnT<String> execute(String param)"
 * 2、为Job方法添加注解 "@XxlJob(value="自定义jobhandler名称", init = "JobHandler初始化方法", destroy = "JobHandler销毁方法")"，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 * 3、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 *
 * @author xuxueli 2019-12-11 21:52:51
 */
@Component
public class BackupJobHandler {
    private static Logger logger = LoggerFactory.getLogger(BackupJobHandler.class);

    @Autowired
    MySQLDBUtil mySQLDBUtil;

    @Value("${mysql.savePath}")
    private String savePath;

    @Autowired
    BackupDao backupDao;

    JobBackupHistoryInfo jobBackupHistoryInfo=null;

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("backupJobHandler")
    public ReturnT<String> demoJobHandler(String param) throws Exception {
        XxlJobLogger.log("进入数据备份执行器");
        if (StringUtils.isNotEmpty(param)){
            JobParam jobParam=JSONObject.parseObject(param,JobParam.class);
            String username = jobParam.getJdbcUsername();
            String password=jobParam.getJdbcPassword();
            String jdbcUrl=jobParam.getJdbcUrl();
            logger.info("jdbcUrl:{}",jdbcUrl);
            String[] array = jdbcUrl.split("//");
            String hostAndPortDatabase= array[1];
            String[] split = hostAndPortDatabase.split("/");
            String database=split[1];
            String[] hostAndPort = split[0].split(":");
            String host=hostAndPort[0];
            String port=hostAndPort[1];
            String fileName =database+"-"+DateUtils.dateTimeNow() +".sql";
            //备份状态
            boolean backupFlag = mySQLDBUtil.backup(host, port, username, password, savePath, fileName, database);
            jobBackupHistoryInfo=new JobBackupHistoryInfo();
            if (backupFlag){
                jobBackupHistoryInfo.setStatus(0);
            }else {
                jobBackupHistoryInfo.setStatus(1);
            }
            //外键
            jobBackupHistoryInfo.setJjdId(jobParam.getJjdId());
            //文件名称
            jobBackupHistoryInfo.setFileName(fileName);
            backupDao.insertBackupHistroy(jobBackupHistoryInfo);
        }
        return ReturnT.SUCCESS;
    }

    public void init() {
        logger.info("init");
    }

    public void destroy() {
        logger.info("destory");
    }
}
