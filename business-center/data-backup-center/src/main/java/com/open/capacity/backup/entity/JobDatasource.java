package com.open.capacity.backup.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


/**
 * jdbc数据源配置实体类(job_jdbc_datasource)
 *
 * @author DUJIN
 * @since 2019-07-30
 */

@Data
public class JobDatasource implements Serializable {

    private static final long serialVersionUID = -5942605646818460334L;

    /**
     * jdbc驱动类
     */
    private final String jdbcDriverClass = "com.mysql.jdbc.Driver";

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 任务执行主键
     */
    private Long jobInfoId;

    /**
     * 数据源名称
     */
    @NotBlank(message = "数据源名称不能为空")
    private String datasourceName;

    /**
     * 用户名
     */
    @NotBlank(message = "用户名称不能为空")
    private String jdbcUsername;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String jdbcPassword;

    /**
     * jdbc url
     */
    @NotBlank(message = "jdbcUrl不能为空")
    private String jdbcUrl;

    /**
     * 定时执行时间
     */
    @NotBlank(message = "执行周期不能为空")
    private String cronStr;

    /**
     * 状态：0删除 1启用 2禁用
     */
    private Integer status;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 备注
     */
    private String comments;
}
