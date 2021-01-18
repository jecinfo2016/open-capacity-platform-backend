package com.open.device.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Point implements Serializable {


    private String type="FeatureCollection";

    private List<Features> features;


}
