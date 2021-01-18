package com.open.capacity.epanetgis;


import com.open.capacity.epanetgis.util.EpanetFileReadUtil;
import com.open.capacity.epanetgis.util.MapUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EpanetAutoConfig {


    /**
     * map工具类
     */
    @Bean("mapUtil")
    public MapUtil mapUtil(){
        MapUtil mapUtil = new MapUtil();
        return mapUtil;
    }

    /**
     * 模型转换工具类
     * @return
     */
    @Bean
    public EpanetFileReadUtil epanetFileReadUtil(){
        EpanetFileReadUtil epanetFileReadUtil = new EpanetFileReadUtil();
        return epanetFileReadUtil;
    }
}
