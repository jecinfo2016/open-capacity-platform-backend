package com.open.device.model;

public class Querie {

    private String aggregator;

    private String downsample;

    private String metric;

    private Tags tags;

    public Querie(String aggregator, String downsample, String metric, Tags tags) {
        this.aggregator = aggregator;
        this.downsample = downsample;
        this.metric = metric;
        this.tags = tags;
    }

    public Querie() {
    }

    public String getAggregator() {
        return aggregator;
    }

    public void setAggregator(String aggregator) {
        this.aggregator = aggregator;
    }

    public String getDownsample() {
        return downsample;
    }

    public void setDownsample(String downsample) {
        this.downsample = downsample;
    }

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
}
