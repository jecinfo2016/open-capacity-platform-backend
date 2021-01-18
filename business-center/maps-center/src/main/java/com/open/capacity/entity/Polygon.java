package com.open.capacity.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Polygon implements Serializable {


    private String type="FeatureCollection";

    private List<Features> features;


}
