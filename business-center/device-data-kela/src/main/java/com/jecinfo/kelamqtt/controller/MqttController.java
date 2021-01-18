package com.jecinfo.kelamqtt.controller;

import com.alibaba.fastjson.JSONObject;
import com.jecinfo.kelamqtt.model.HResponse;
import com.jecinfo.kelamqtt.model.Message;
import com.jecinfo.kelamqtt.push.MqttGateway;
import com.open.capacity.common.auth.details.LoginAppUser;
import com.open.capacity.common.util.SysUserUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
@RequestMapping("/public/mqtt")
@SuppressWarnings("all")
public class MqttController {

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate loadBalanced;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MqttGateway mqttGateway;

    public MqttController(MqttGateway mqttGateway) {
        this.mqttGateway = mqttGateway;
    }
    /**
     * 发送mqtt消息
     *
     * @param topic 消息主题
     * @param data  消息内容
     * @return
     */
    @PostMapping("/send")
    public HResponse send(@RequestParam(value = "topic")String topic,@RequestParam(value = "data") String data,
                          @RequestParam(value = "appId", required = false, defaultValue = "124") Integer appId) {
        this.logger.info("开始发送mqtt消息,主题：{},消息：{}", topic, data);
        Message message = new Message();
        if (StringUtils.isNotBlank(topic)) {
            this.mqttGateway.sendToMqtt(topic, data);
            message.setTitle(topic);
            message.setSendTime(new Date());
            message.setContent(data);
            message.setType(1);

            LoginAppUser loginAppUser = SysUserUtil.getLoginAppUser();
            //发送人
            if (null != loginAppUser) {
                message.setSendUser(loginAppUser.getId().intValue());
            }
            //应用id 124 消息中心
            message.setAppId(appId);

        } else {
            this.mqttGateway.sendToMqtt(data);
        }
        try {
            String s = loadBalanced.postForObject("http://message-center/message/save", message, String.class);
            JSONObject json = JSONObject.parseObject(s);
            Integer code = json.getInteger("resp_code");
            if (code == 0) {
                System.out.println("保存成功！");
            }
        } catch (RestClientException e) {
            e.printStackTrace();
            System.out.println("保存消息失败！");
        }
        this.logger.info("发送mqtt消息完成,主题：{},消息：{}", topic, data);
        return HResponse.success();
    }
}


