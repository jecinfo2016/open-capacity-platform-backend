package com.open.capacity.common.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SysDept {

    private static final long serialVersionUID = 460960503528232497L;
    private Long id;
    private String name;
    private String parentId;
    private String level;
    private String remark;
    private String operator;
    @TableField(value="updateTime")
    private Date updateTime;

    private List<SysDept> children;



}
