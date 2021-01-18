package com.open.device.controller;

import com.open.device.model.DeviceUpgrade;
import com.open.device.model.HResponse;
import com.open.device.service.DeviceUpgradeService;
import com.open.device.push.MqttGateway;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/mqtt")
public class DeviceUpgradeController {

    private final MqttGateway mqttGateway;

    public DeviceUpgradeController(MqttGateway mqttGateway) {
        this.mqttGateway = mqttGateway;
    }

    @Autowired
    private DeviceUpgradeService deviceUpgradeService;
    /**
     * 获取版本号
     * @return
     */
    @PostMapping("/getver")
    public HResponse getver(){
        final String msg = "S_VER?";
        this.mqttGateway.sendToMqtt(msg,"jecinfo/rtu");
        return HResponse.success();

    }

    /**
     * 设备升级
     * @return
     */
    @PostMapping("/upgrade")
    public HResponse upgrade(MultipartFile file){
        final String msg = "S_Upg,dtu_tb26V";
        File file1 = new File("D:");
        int Zzzz = (int) ((file1.length()+256)/256);
        List<DeviceUpgrade> all = deviceUpgradeService.findAll();
        all.forEach(a->{
            String checkCode = a.getCheckCode();
            String ver = a.getVer();
            this.mqttGateway.sendToMqtt(msg+checkCode+"."+ver+","+Zzzz);
        });
        return HResponse.success();
    }

    /**
     * 无需升级发送
     * @return
     */
    @PostMapping("/end")
    public HResponse end(){
        final String msg = "S_End";
        this.mqttGateway.sendToMqtt(msg);
        return HResponse.success();
    }

    /**
     *
     * @return
     */
    @PostMapping("/upd")
    public HResponse upd(){
        final String msg = "S_UpD,xxxx,abcd";
        this.mqttGateway.sendToMqtt(msg);
        return HResponse.success();
    }

}
