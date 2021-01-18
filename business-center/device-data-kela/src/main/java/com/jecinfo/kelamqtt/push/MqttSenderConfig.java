package com.jecinfo.kelamqtt.push;

import com.jecinfo.kelamqtt.opentsdb.builder.MetricBuilder;
import com.jecinfo.kelamqtt.service.DeviceService;
import com.jecinfo.kelamqtt.service.ToTsdb;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.*;

import java.util.*;


@Configuration
@IntegrationComponentScan
@Slf4j
public class MqttSenderConfig {

    @Autowired
    private DeviceService deviceService;

    @Value("${spring.mqtt.username}")
    private String username;

    @Value("${spring.mqtt.password}")
    private String password;

    @Value("${spring.mqtt.url}")
    private String hostUrl;

    @Value("${spring.mqtt.client.id}")
    private String clientId;

    @Value("${spring.mqtt.default.topic}")
    private String defaultTopic;

    @Value("${opentsdb.serviceUrl}")
    private String serviceUrl;

    private List<String> byModel = null;

    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        //mqttConnectOptions.setUserName(username);
        //mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{hostUrl});
        mqttConnectOptions.setKeepAliveInterval(20);
        return mqttConnectOptions;
    }
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("mq" + System.currentTimeMillis(), mqttClientFactory());
        DefaultPahoMessageConverter converter = new DefaultPahoMessageConverter();
        converter.setPayloadAsBytes(true);
        messageHandler.setConverter(converter);
        messageHandler.setAsync(false);
        messageHandler.setDefaultTopic(defaultTopic);
        messageHandler.setDefaultQos(1);
        return messageHandler;
    }
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    /*-----------receiver------------*/
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    //配置client,监听的topic
    @Bean
    public MessageProducer inbound() {
        byModel = deviceService.getByModel();
        String[] topics = byModel.toArray(new String[0]);
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter("mqtt" + System.currentTimeMillis(), mqttClientFactory(),
                        topics);
        DefaultPahoMessageConverter converter = new DefaultPahoMessageConverter();
        converter.setPayloadAsBytes(true);
        adapter.setConverter(converter);
        adapter.setCompletionTimeout(30000);
        adapter.setQos(0);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    //通过通道获取数据
    private static final String DECEUI = "deveui";
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                byte[] payload = (byte[]) message.getPayload();
                System.out.println(payload.length);
                if (payload.length > 16) {
                    Object payLoad = message.getPayload();
                    // 如果不设置转换器这里强转byte[]会报错
                    byte[] data = (byte[]) payLoad;
                    System.out.println("**************************" + Arrays.toString(data));
                    MetricBuilder builder = MetricBuilder.getInstance();
                    String topic = (String) message.getHeaders().get("mqtt_receivedTopic");
                    System.out.println("-------------------->" + topic);
                    for (String a : byModel) {
                        assert topic != null;
                        if (topic.equals(a)) {
                            ToTsdb.toOpenTsdb(data, builder, topic, serviceUrl);
                            return;
                        }
                    }
                }
            }
//                //获取时间戳
//                Long timestamp = message.getHeaders().getTimestamp();
//                log.info("GenMessage timestamp===>  "+timestamp);
//                String topic = (String) message.getHeaders().get("mqtt_receivedTopic");
//                HttpClientImpl client = new HttpClientImpl(serviceUrl);
//                String payload = (String) message.getPayload();
//                log.info("GenMessage payload===>  "+payload);
//                //数据转换成JSONObject
//                JSONObject jsonObj = JSON.parseObject(payload);
//                if (topic.equals("devstate")){
//                    //获取设备状态
//                    Integer state = (Integer) jsonObj.get("state");
//                    String deveui = (String) jsonObj.get(DECEUI);
//                    String rpttime = (String) jsonObj.get("rpttime");
//                    //String类型时间转long
//                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    Date date = null;
//                    try {
//                        date = formatter.parse(rpttime);
//                        long time = date.getTime();
//                        //设备状态存入opentsdb
//                        builder.addMetric("kela.state").setDataPoint(time,state).addTag(DECEUI,deveui);
//                        Response response = client.pushMetrics(builder,
//                                ExpectResponse.SUMMARY);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (topic.equals("metedata")){
//                    //mqtt上报的数据
//                    GenMessage genMessage = new GenMessage();
//                    //获取设备类型
//                    String devType = (String) jsonObj.get("devtype");
//                    genMessage.setDevType(devType);
//                    log.info("GenMessage devType===>  "+devType);
//                    //获取实际数据转成jsonArray
//                    JSONArray jsonArray = (JSONArray) jsonObj.get("datarows");
//                    log.info("hello,metedata,"+jsonArray);
//                    Datarows datarow = new Datarows();
//                    List<Map<String, Object>> mapListJson = (List) jsonArray;
//                    //jsonArray转换成map
//                    mapListJson.forEach(map->{
//                        datarow.setMap(map);
//                    });
//                    //获取设备标识
//                    String deveui = (String) datarow.getMap().get(DECEUI);
//                    datarow.setDeveui(deveui);
//                    datarow.setCollecttime(timestamp);
//                    List<Datarows> datarowsList = new ArrayList<>();
//                    datarowsList.add(datarow);
//                    genMessage.setDatarows(datarowsList);
//                    //调用存储方法
//                    data2opentsdb(genMessage);
//                }
//            }
//            //数据存入opentsdb
//            public void data2opentsdb(GenMessage genMessage){
//                String deveui = "";
//                long collecttime = 0;
//                Map<String, Object> map = null;
//                HttpClientImpl client = new HttpClientImpl(serviceUrl);
//                MetricBuilder builder = MetricBuilder.getInstance();
//                //tags
//                HashMap<String, String> tags = new HashMap<>();
//                List<Datarows> datarows = genMessage.getDatarows();
//                //设备类型
//                String devType = genMessage.getDevType();
//                //遍历数据
//                for (Datarows datarow : datarows){
//                    deveui = datarow.getDeveui().replace("@","_");
//                    collecttime = datarow.getCollecttime();
//                    map = datarow.getMap();
//                    //移除无用的数据
//                    map.remove("deveui");
//                    map.remove("collecttime");
//                }
//                tags.put("deveui", deveui);
//                if (map!=null){
//                    for (Map.Entry<String,Object> entry : map.entrySet()){
//                        //获取值
//                        String str = entry.getValue().toString();
//                        //获取数字类型数据
//                        Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
//                        //如果不是数字类型数据转换为tags存储
//                        if (!pattern.matcher(str).matches()&&!str.contains("+")){
//                            String value = (String) entry.getValue();
//                            tags.put(entry.getKey(),value);
//                            if (value.equals("实时数据")){
//                                tags.put(entry.getKey(),"Realtime-data");
//                            }
//                        }else {
//                            //数据转换成double
//                            double value = Double.parseDouble(str);
//                            //key存为index
//                            tags.put("index", entry.getKey());
//                            builder.addMetric("kela."+devType).setDataPoint(collecttime,value).addTags(tags);
//                        }
//                    }
//                }
//                try {
//                    //存入opentsdb
//                    Response response = client.pushMetrics(builder,
//                            ExpectResponse.SUMMARY);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
        };
    }
}