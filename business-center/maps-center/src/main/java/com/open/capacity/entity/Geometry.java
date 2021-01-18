package com.open.capacity.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Geometry implements Serializable {

    private String type;

    private Object coordinates;

}
