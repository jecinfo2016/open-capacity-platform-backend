package com.open.capacity.backup.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dujin
 */
@Data
public class JobParam implements Serializable {

    private static final long serialVersionUID = -9079875383154181489L;
    /**
     * 备份任务主键
     */
    private Long jjdId;

    /**
     * 用户名
     */
    private String jdbcUsername;
    /**
     * 密码
     */
    private String jdbcPassword;
    /**
     * jdbcUrl
     */
    private String jdbcUrl;
}
