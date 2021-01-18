package com.open.capacity.client.entity;

import lombok.Data;

import java.util.Date;

@Data
public class IpFilter {
    private Integer id;
    private String ip;
    private Date createTime;
    private Date updateTime;
    private String mark;
}
