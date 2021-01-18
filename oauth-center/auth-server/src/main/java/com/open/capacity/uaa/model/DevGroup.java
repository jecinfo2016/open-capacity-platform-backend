package com.open.capacity.uaa.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class DevGroup implements Serializable {
    private static final long serialVersionUID = -3958811560634158452L;
    private Integer id;
    private Integer userId;
    private String name;
}
