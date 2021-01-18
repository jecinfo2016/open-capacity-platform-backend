package com.jecinfo.kelamqtt.model;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class MyData {

    private String host;
    private long timestap;
    private long interval;
    private Double vol;
    private Double rssi;
    private Double at;
    private Double ah;
    private Double type;

    private HashMap<String, Double> map;

    private List<Integer> list;

}
