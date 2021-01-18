package com.open.capacity.client.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建路由模型
 */
@EqualsAndHashCode
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WhiteListDefinition {

    //白名单的Id
    private Long id;
    //白名单路径
    private String path;
}
