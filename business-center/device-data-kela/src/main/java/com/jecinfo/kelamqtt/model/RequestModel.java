package com.jecinfo.kelamqtt.model;

import java.util.List;

public class RequestModel {

    private String start;

    private Long end;

    private List<Querie> queries;

    public RequestModel(String start, Long end, List<Querie> queries) {
        this.start = start;
        this.end = end;
        this.queries = queries;
    }

    public RequestModel() {
    }


    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public List<Querie> getQueries() {
        return queries;
    }

    public void setQueries(List<Querie> queries) {
        this.queries = queries;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }
}
