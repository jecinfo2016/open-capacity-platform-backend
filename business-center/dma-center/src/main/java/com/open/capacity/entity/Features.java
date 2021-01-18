package com.open.capacity.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Features implements Serializable {

    private String type = "Feature";

    private Object properties;

    private Geometry geometry;
}
