package com.open.capacity.developer.water.entity;

import java.util.List;

/**
 * @author DUJIN
 * @Classname GeoPoint
 * @Description  geoJSON实体类
 * @Date 2020/8/26 16:17
 */
public class Geo {
    private String type="FeatureCollection";
    private List<Feature> features;

    public String getType() {
        return type;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "Geo{" +
                "type='" + type + '\'' +
                ", features=" + features +
                '}';
    }
}
