package com.jecinfo.kelamqtt.model;

import lombok.Data;

import java.util.List;

@Data
public class GenMessage {

    private String devType;

    private List<Datarows> datarows;

}
