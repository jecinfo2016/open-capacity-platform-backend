package com.open.capacity.backup.dao;

import com.open.capacity.backup.entity.JobBackupHistoryInfo;
import com.open.capacity.backup.entity.JobDatasource;

import java.util.List;
import java.util.Map;

/**
 * @author DUJIN
 */
public interface BackupDao {

    /**
     * 新增数据备份记录
     * @param datasource
     * @return
     */
    int insertBackupInfo(JobDatasource datasource);

    /**
     * 保存数据备份记录
     * @param jdbcDatasource
     * @return
     */
    int updateBackUpInfo(JobDatasource jdbcDatasource);

    /**
     * 查询数据备份列表
     * @param params
     * @return
     */
    List<JobDatasource> queryJobDatasourceList(Map<String,Object> params);

    /**
     * 删除数据备份信息
     * @param id
     * @return
     */
    int deleteBackupInfo(Long id);

    /**
     * 保存数据备份历史
     * @param historyInfo
     * @return
     */
    int insertBackupHistroy(JobBackupHistoryInfo historyInfo);

    /**
     * 查询数据备份历史
     * @param params
     * @return
     */
    List<JobBackupHistoryInfo> queryHistoryList(Map<String, Object> params);
}
