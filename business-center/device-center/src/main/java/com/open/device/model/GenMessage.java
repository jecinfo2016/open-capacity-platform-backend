package com.open.device.model;

import lombok.Data;

import java.util.List;

@Data
public class GenMessage {

    private String devtype;

    private boolean online;

    private List<Datarows> datarows;

}
