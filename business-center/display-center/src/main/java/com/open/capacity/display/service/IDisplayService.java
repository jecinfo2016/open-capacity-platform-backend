package com.open.capacity.display.service;

import com.open.capacity.display.entity.DisplayBaseInfo;

import java.util.List;
import java.util.Map;

/**
 * @author DUJIN
 * @Classname IDisplayService
 * @Date 2020/9/11 11:54
 */
public interface IDisplayService {

    /**
     * 查询所有的数据库
     * @return
     */
    List<String> queryDatabases();

    /**
     * 根据数据库名称查询所有的表
     * @param dbName :数据库名称
     * @return
     */
    List<Map> queryTablesByDbname(String dbName);

    /**
     * 根据表名称查询所有的字段
     * @param tableName:表名称
     * @param dbName:数据库名称
     * @return
     */
    List<Map> queryColumnsByTableName(String dbName,String tableName);

    /**
     * 保存统计信息
     * @param displayBaseInfo
     * @return
     */
    int saveOrUpdateDisplayInfo(DisplayBaseInfo displayBaseInfo);

    /**
     * 删除统计信息
     * @param id
     * @return
     */
    int deleteDisplayInfo(Integer id);

    /**
     * 查询展示列表
     * @param displayBaseInfo
     * @return
     */
    List<DisplayBaseInfo> queryDisplayInfo(DisplayBaseInfo displayBaseInfo);

    /**
     * 查询数据
     * @param databaseName
     * @param tableName
     * @param columnName
     * @param mode
     * @return
     */
    List<Map<String,Object>> queryDataList(String databaseName, String tableName, String columnName, String mode);
}
