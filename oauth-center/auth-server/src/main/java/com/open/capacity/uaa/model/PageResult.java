package com.open.capacity.uaa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 5060457046379971922L;
    private Long count;
    private int resp_code;
    private List<T> data;
    private Integer current;
    private Integer size;
}
