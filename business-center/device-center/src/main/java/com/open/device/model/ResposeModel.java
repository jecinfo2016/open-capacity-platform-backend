package com.open.device.model;

import java.util.Arrays;
import java.util.HashMap;

public class ResposeModel {
    private String metric;

    private Tags tags;

    private String[] aggregateTags;

    private HashMap<Long, Double> dps;

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public Tags getTags() {
        return tags;
    }

    public void setTags(Tags tags) {
        this.tags = tags;
    }

    public String[] getAggregateTags() {
        return aggregateTags;
    }

    public void setAggregateTags(String[] aggregateTags) {
        this.aggregateTags = aggregateTags;
    }

    public HashMap<Long, Double> getDps() {
        return dps;
    }

    public void setDps(HashMap<Long, Double> dps) {
        this.dps = dps;
    }

    @Override
    public String toString() {
        return "ResposeModel{" +
                "metric='" + metric + '\'' +
                ", tags=" + tags +
                ", aggregateTags=" + Arrays.toString(aggregateTags) +
                ", dps=" + dps +
                '}';
    }
}
