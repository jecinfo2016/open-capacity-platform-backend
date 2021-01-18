package com.open.capacity.developer.water.entity;

import java.util.List;

/**
 * @author DUJIN
 * @Classname LineStringGeometry
 * @Date 2020/8/26 16:49
 */
public class LineString implements Geometry {
    private String type="LineString";
    private List<double[]> coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<double[]> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<double[]> coordinates) {
        this.coordinates = coordinates;
    }
}
