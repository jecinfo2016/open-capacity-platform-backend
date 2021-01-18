package com.jecinfo.kelamqtt.model;

public class Tags {

    private String host;

    private String index;

    public Tags(String host) {
        this.host = host;
    }

    public Tags() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "host='" + host + '\'' +
                '}';
    }
}
