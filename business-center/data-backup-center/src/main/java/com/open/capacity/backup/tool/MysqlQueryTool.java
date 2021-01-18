package com.open.capacity.backup.tool;

import com.open.capacity.backup.entity.JobDatasource;

import java.sql.SQLException;

/**
 * mysql数据库使用的查询工具
 * @author Jk
 */
public class MysqlQueryTool extends BaseQueryTool {

    /**
     * 构造方法
     *
     * @param jobDatasource
     */
    public MysqlQueryTool(JobDatasource jobDatasource) throws SQLException {
        super(jobDatasource);
    }
}
