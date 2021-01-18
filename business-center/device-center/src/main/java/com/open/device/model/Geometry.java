package com.open.device.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Geometry implements Serializable {

    private String type="Point";

    private BigDecimal[] coordinates;

}
