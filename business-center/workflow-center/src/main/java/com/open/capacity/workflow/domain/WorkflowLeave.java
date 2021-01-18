package com.open.capacity.workflow.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.open.capacity.workflow.constant.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 请假对象 workflow_leave
 * 
 * @author danny
 * @date 2020-10-28
 */
public class WorkflowLeave extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 请假类型 */
    private String type;

    /** 标题 */
    private String title;

    /** 原因 */
    private String reason;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date leaveStartTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date leaveEndTime;


    private String instanceId;
    private String taskName;

    /** 状态 */
    private String state;

    /** 创建人 */
    private String createName;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
    }
    public void setLeaveStartTime(Date leaveStartTime) 
    {
        this.leaveStartTime = leaveStartTime;
    }

    public Date getLeaveStartTime() 
    {
        return leaveStartTime;
    }
    public void setLeaveEndTime(Date leaveEndTime) 
    {
        this.leaveEndTime = leaveEndTime;
    }

    public Date getLeaveEndTime() 
    {
        return leaveEndTime;
    }
    public void setInstanceId(String instanceId) 
    {
        this.instanceId = instanceId;
    }

    public String getInstanceId() 
    {
        return instanceId;
    }
    public void setState(String state) 
    {
        this.state = state;
    }

    public String getState() 
    {
        return state;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("title", getTitle())
            .append("reason", getReason())
            .append("leaveStartTime", getLeaveStartTime())
            .append("leaveEndTime", getLeaveEndTime())
            .append("instanceId", getInstanceId())
            .append("state", getState())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
