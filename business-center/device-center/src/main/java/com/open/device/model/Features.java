package com.open.device.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Features implements Serializable {

    private String type = "Feature";

    private DeviceInfo properties;

    private Geometry geometry;
}
