package com.open.capacity.display.dao;

import com.open.capacity.display.entity.DisplayBaseInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @author DUJIN
 * @Classname IDisplayDao
 * @Date 2020/9/11 11:57
 */
public interface IDisplayDao {

    /**
     * 查询所有的数据库
     * @return
     */
    @Select("SELECT t.SCHEMA_NAME databaseName FROM information_schema.SCHEMATA t")
    List<String> queryDatabases();

    /**
     * 根据数据库名称查询所有的表
     * @param dbName
     * @return
     */
    @Select("SELECT TABLE_NAME tableName,TABLE_COMMENT tableComment FROM information_schema.TABLES  WHERE TABLE_SCHEMA=#{dbName}")
    List<Map> queryTablesByDbname(String dbName);

    /**
     * 根据表名称查询字段集合
     * @param dbName ：数据库名
     * @param tableName ：表名
     * @return
     */
    @Select("SELECT column_name columnName,DATA_TYPE dataType,column_comment columnComment" +
            " FROM information_schema.COLUMNS" +
            " WHERE TABLE_SCHEMA = #{dbName} AND TABLE_NAME = #{tableName}")
    List<Map> queryColumnsByTableName(@Param("dbName") String dbName, @Param("tableName") String tableName);

    /**
     * 保存统计信息
     * @param displayBaseInfo
     * @return
     */
    @Insert("INSERT INTO display_base_info(database_name,table_name,table_desc,column_name,display_desc,mode) " +
            " VALUES(#{databaseName},#{tableName},#{tableDesc},#{columnName},#{displayDesc},#{mode})")
    int saveDisplayInfo(DisplayBaseInfo displayBaseInfo);

    /**
     * 修改统计信息
     * @param displayBaseInfo
     * @return
     */
    @Update("UPDATE display_base_info SET database_name=#{databaseName}," +
            "table_name=#{tableName}," +
            "table_desc=#{tableDesc}," +
            "column_name=#{columnName}," +
            "display_desc=#{displayDesc}," +
            "mode=#{mode} " +
            "WHERE id=#{id}")
    int updateDisplayInfo(DisplayBaseInfo displayBaseInfo);

    /**
     * 删除统计信息数据
     * @param id
     * @return
     */
    @Update("DELETE FROM display_base_info WHERE id=#{id}")
    int deleteDisplayInfo(Integer id);

    /**
     * 查询展示列表
     * @param displayBaseInfo
     * @return
     */
    @Select({"<script> SELECT * FROM display_base_info WHERE 1=1 " +
            "<if test= \"tableName!= null and tableName != ''\"> AND table_name like concat('%', #{tableName},'%')</if>" +
            "<if test= \"databaseName!= null and databaseName != ''\"> AND database_name like concat('%', #{databaseName},'%')</if>" +
            "</script>"})
    List<DisplayBaseInfo> queryDisplayInfo(DisplayBaseInfo displayBaseInfo);

    /**
     * 查询数据列表
     * @param databaseName
     * @param tableName
     * @param columnName
     * @return
     */
    @Select("SELECT ${columnName} from ${databaseName}.${tableName}")
    List<Map<String, Object>> queryDataList(@Param("databaseName") String databaseName,
                                            @Param("tableName") String tableName,
                                            @Param("columnName") String columnName);

    /**
     * 分组函数
     * @param databaseName
     * @param tableName
     * @param columnName
     * @param mode
     * @return
     */
    @Select("SELECT ${mode}(${columnName}) from ${databaseName}.${tableName} GROUP BY ${columnName}")
    List<Map<String, Object>> queryDataListByGroup(@Param("databaseName") String databaseName,
                                            @Param("tableName") String tableName,
                                            @Param("columnName") String columnName,@Param("mode") String mode);
}
