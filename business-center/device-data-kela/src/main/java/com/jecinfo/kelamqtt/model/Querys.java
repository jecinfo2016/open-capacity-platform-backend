package com.jecinfo.kelamqtt.model;

import lombok.Data;

import java.util.List;

@Data
public class Querys {

    private String start;
    private List<Querie> queries;
}
