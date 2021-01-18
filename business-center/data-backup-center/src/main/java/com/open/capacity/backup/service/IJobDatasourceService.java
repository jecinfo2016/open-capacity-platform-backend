package com.open.capacity.backup.service;

import com.open.capacity.backup.entity.JobBackupHistoryInfo;
import com.open.capacity.backup.entity.JobDatasource;
import com.open.capacity.common.web.PageResult;
import java.util.Map;

/**
 * @author Jk
 */
public interface IJobDatasourceService {

    /**
     * 测试数据源是否正确，能否连接
     * @param jdbcDatasource
     * @return
     * @throws Exception
     */
    Boolean dataSourceTest(JobDatasource jdbcDatasource) throws Exception;


    /**
     * 保存数据备份信息
     * @param jdbcDatasource
     * @return
     */
    Boolean saveOrUpdateDataSource(JobDatasource jdbcDatasource);

    /**
     * 更新数据备份状态
     * @param jdbcDatasource
     * @return
     */
    Boolean updateBackupStatus(JobDatasource jdbcDatasource);

    /**
     * 获取数据备份列表
     * @param params
     * @return
     */
    PageResult<JobDatasource> queryJobDatasourceList(Map<String,Object> params);

    /**
     * 删除数据备份信息
     * @param datasource
     * @return
     */
    boolean deleteBackupInfo(JobDatasource datasource);

    /**
     * 查询数据备份历史
     * @param params
     * @return
     */
    PageResult<JobBackupHistoryInfo> queryHistoryList(Map<String, Object> params);
}
