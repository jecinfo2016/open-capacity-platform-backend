package com.open.capacity.backup.entity;

import lombok.Data;

import java.util.Date;

/**
 * 备份历史实体类
 * @author Jk
 */
@Data
public class JobBackupHistoryInfo {
    private Long id;
    /**
     * 任务主键
     */
    private Long jjdId;
    /**
     * 备份文件名称
     */
    private String fileName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 备份状态
     */
    private Integer status;
}
