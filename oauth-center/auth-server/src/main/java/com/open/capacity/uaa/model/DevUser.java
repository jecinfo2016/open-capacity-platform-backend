package com.open.capacity.uaa.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class DevUser implements Serializable {
    private static final long serialVersionUID = 2834517019115674526L;
    private Long id;
    private String username;
    private String password;
    private String realname;
    private String phone;
    private String createTime;
    private String updateTime;
}
