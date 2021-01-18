package com.open.capacity.display.entity;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author DUJIN
 */
public class DisplayBaseInfo {
    private Integer id;

    @NotBlank(message = "表名称不能为空")
    private String tableName;

    @NotBlank(message = "表描述不能为空")
    private String tableDesc;

    @NotBlank(message = "字段不能为空")
    private String columnName;

    @NotBlank(message = "统计描述不能为空")
    private String displayDesc;

    private String mode;

    @NotBlank(message = "数据库名称不能为空")
    private String databaseName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableDesc() {
        return tableDesc;
    }

    public void setTableDesc(String tableDesc) {
        this.tableDesc = tableDesc;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDisplayDesc() {
        return displayDesc;
    }

    public void setDisplayDesc(String displayDesc) {
        this.displayDesc = displayDesc;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}
