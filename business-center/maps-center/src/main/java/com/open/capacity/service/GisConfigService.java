package com.open.capacity.service;


import org.springframework.http.ResponseEntity;

public interface GisConfigService {

    /**
     * 获取高德key
     * @param appId
     * @return
     */
    String getAmapKey(Integer appId);



    /**
     * 调用点位信息
     * @return
     */
    ResponseEntity<String> queryPoint();

    /**
     * 调用面要素信息
     * @return
     */
    ResponseEntity<String> queryPolygon();

    /**
     * 调用线要素信息
     * @return
     */
    ResponseEntity<String> queryLineString();
}
