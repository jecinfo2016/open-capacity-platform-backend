package com.open.capacity.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
public class Features implements Serializable {

    private String type = "Feature";

    private Object properties;

    private Geometry geometry;
}
