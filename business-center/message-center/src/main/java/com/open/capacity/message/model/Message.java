package com.open.capacity.message.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.util.Date;

@Data
public class Message {

    private Integer id;
    private String title;
    private String content;
    private Integer type;//0:短信；1：mqtt
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sendTime;
    private String mobile;
    private Integer sendUser;
    private Integer appId;

    @Transient
    private Integer page;
    @Transient
    private Integer limit;
    @Transient
    private String appName;
    @Transient
    private String nickname;

}
