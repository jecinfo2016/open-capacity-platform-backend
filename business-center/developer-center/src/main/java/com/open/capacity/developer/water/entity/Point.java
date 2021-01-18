package com.open.capacity.developer.water.entity;

/**
 * @author DUJIN
 * @Classname PointGeometry
 * @Date 2020/8/26 16:48
 */
public class Point implements Geometry {
    private String type="Point";
    private double[] coordinates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }
}
