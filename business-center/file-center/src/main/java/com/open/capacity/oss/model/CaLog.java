package com.open.capacity.oss.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CaLog {

    private String catalog;

    private List<CaLog> level = new ArrayList<>();

    public void add(CaLog caLog) {
        level.add(caLog);
    }
}
