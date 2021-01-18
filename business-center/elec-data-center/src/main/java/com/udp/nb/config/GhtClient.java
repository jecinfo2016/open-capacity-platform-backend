package com.udp.nb.config;

import com.alibaba.fastjson.JSON;
import com.udp.nb.entity.GhtProperties;
import com.udp.nb.util.HttpRequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0
 * @date 18/3/24 下午3:44
 */
@Component
public class GhtClient {

    private Logger logger = LoggerFactory.getLogger(GhtClient.class);

    @Autowired
    private GhtProperties ghtProperties;

    //报警WebSocket
    public String doDeviceAiralertRequest(String type,String time,String mac,String nno) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", type);
        map.put("time", time);
        map.put("mac", mac);
        map.put("nno", nno);
        logger.debug("doDeviceAiralertRequest:"+ JSON.toJSONString(map));
        HttpRequestUtil.doGet(ghtProperties.getDeviceidurl(), map);
        return null;
    }
    //开关WebSocket
    public String doSwitchcontrolRequest(String time,String mac,String nno,String openstatus) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("openstatus", openstatus);
        map.put("time", time);
        map.put("mac", mac);
        map.put("nno", nno);
        logger.debug("doSwitchcontrolRequest:"+ JSON.toJSONString(map));
        HttpRequestUtil.doGet(ghtProperties.getSwitchcontrolurl(), map);
        return null;
    }
}
