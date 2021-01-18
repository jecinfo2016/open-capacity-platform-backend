package com.open.device.model;

public class Tags {

    private String host;

    private String company;

    private String location;

    private String index;

    public Tags(String host, String location, String index) {
        this.host = host;
        this.location = location;
        this.index = index;
    }

    public Tags() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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
                ", company='" + company + '\'' +
                ", location='" + location + '\'' +
                ", index='" + index + '\'' +
                '}';
    }
}
