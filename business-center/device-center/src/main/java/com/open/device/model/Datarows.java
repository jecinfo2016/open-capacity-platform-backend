package com.open.device.model;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class Datarows {

    private long collecttime;

    private String deveui;

    private Map<String,Object> map;
}
