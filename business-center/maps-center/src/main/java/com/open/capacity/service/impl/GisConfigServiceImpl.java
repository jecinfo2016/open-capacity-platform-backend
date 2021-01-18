package com.open.capacity.service.impl;

import com.open.capacity.dao.GisConfigDao;
import com.open.capacity.entity.LineString;
import com.open.capacity.entity.Point;
import com.open.capacity.entity.Polygon;
import com.open.capacity.service.GisConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class GisConfigServiceImpl implements GisConfigService {


    @Autowired
    private GisConfigDao gisConfigDao;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> queryPoint() {
        Point point = new Point();
        // return this.restTemplate.getForObject("http://device-center/deviceinfo/Point", Point.class);
        //return this.restTemplate.postForLocation("http://device-center/deviceinfo/Point",point,String.class);
        ResponseEntity<String> entity = restTemplate.postForEntity("http://device-center/deviceinfo/Point", point, String.class);
        return entity;

    }

    public ResponseEntity<String> queryPolygon() {
        Polygon polygon = new Polygon();
        return  restTemplate.postForEntity("http://dma-center/ndmainfo/Polygon", polygon, String.class);
    }

    @Override
    public ResponseEntity<String> queryLineString() {
        LineString lineString = new LineString();
        return  restTemplate.postForEntity("http://dma-center/npipelineview/LineString", lineString, String.class);
    }

    @Override
    public String getAmapKey(Integer appId) {
        return gisConfigDao.getAmapKey(appId);
    }
}
