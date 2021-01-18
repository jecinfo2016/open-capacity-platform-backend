package com.open.capacity.entity;

import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.List;

@Data
public class Geometry implements Serializable {

    private String type;

    private Object coordinates;

}
