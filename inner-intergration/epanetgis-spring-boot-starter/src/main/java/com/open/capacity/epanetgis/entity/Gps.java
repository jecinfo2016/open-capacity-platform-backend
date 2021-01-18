package com.open.capacity.epanetgis.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 经纬度坐标实体类
 */
@Setter
@Getter
public class Gps {
    double wgLon;
    double wgLat;

    public Gps() {

    }

    public Gps(double wgLon, double wgLat) {
        this.wgLon = wgLon;
        this.wgLat = wgLat;
    }
}
