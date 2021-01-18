package org.opentsdb.device;


import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.opentsdb.BrokerDescriptor;
import org.opentsdb.HttpClientImpl;
import org.opentsdb.rule.FilterRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.ConstructorProperties;
import java.util.*;

public class MyMqtt2OpenTSDB {

    static Logger log = LoggerFactory.getLogger(MyMqtt2OpenTSDB.class) ;

    private BrokerDescriptor brokerDescriptor;
    private MqttCallback callback;
    private Map<String, Integer> subscribedTopicNames;

    private List<HttpClientImpl> destinations;

    MqttClient mqttClient = null;
    MqttConnectOptions options = null;

    public Map<String,Integer> getTopics(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("AQM2.0/YuYao",1);
        map.put("AQM2.0/BEICHEN",1);
        map.put("jecinfo/water",1);
        map.put("AQM2.0/ShaoXing",1);
        map.put("AQM2.0/JIAXING",1);
        map.put("AQM2.0/JiaXing",1);
        map.put("jecinfo/rwqm",1);
        map.put("AQM2.0/liaoning",1);
        map.put("jecinfo/vocs",1);
        map.put("jecinfo/watermeter",1);
        map.put("jecinfo/220620000021",1);
        map.put("jecinfo/220620000023",1);
        map.put("jecinfo/220620000027",1);
        map.put("jecinfo/220620000028",1);
        map.put("jecinfo/220620000029",1);
        map.put("jecinfo/220620000030",1);
        map.put("jecinfo/220620000036",1);
        map.put("jecinfo/220620000076",1);
        map.put("jecinfo/220620000081",1);
        map.put("jecinfo/220620000086",1);
        map.put("jecinfo/220620000098",1);
        map.put("jecinfo/220620000099",1);
        map.put("jecinfo/220620000106",1);
        map.put("jecinfo/220620000107",1);
        map.put("jecinfo/220620000109",1);
        map.put("jecinfo/220620000110",1);
        map.put("jecinfo/220620000111",1);
        map.put("jecinfo/220620000112",1);
        map.put("jecinfo/220620000113",1);
        map.put("jecinfo/220620000115",1);
        map.put("jecinfo/220620000116",1);
        return map;
    }

    @ConstructorProperties({ "brokerDescriptor", "destination", "filterRules" })
    public MyMqtt2OpenTSDB(BrokerDescriptor brokerDescriptor, HttpClientImpl  destination,
                           List<FilterRule> filterRules) {

        super();

        this.brokerDescriptor = brokerDescriptor;
        callback = new Callback( filterRules );
        destinations = new ArrayList<HttpClientImpl>();
        destinations.add(destination);
    }

    @ConstructorProperties({ "brokerDescriptor" , "destinations" , "filterRules" })
    public MyMqtt2OpenTSDB(BrokerDescriptor brokerDescriptor,
                           List<HttpClientImpl> destinations, List<FilterRule> filterRules) {

        super();

        this.brokerDescriptor = brokerDescriptor;
        callback = new Callback(filterRules);
        this.destinations = destinations;
    }

    public void start() throws MqttException {

        Map<String, Integer> topic = getTopics();
        do {
            if (mqttClient == null){
                mqttClient = getMqttClient(brokerDescriptor);
                mqttClient.setCallback( callback );
                options = getMqttConnectOptions(brokerDescriptor);
            }
            // Connect broker when disconnected
            if ( !mqttClient.isConnected() ) {
                try {
                    log.info("Connecting MQTT broker {}" , mqttClient.getServerURI());
                    mqttClient.connect(options);
                    log.info("MQTT broker connected to {}" , mqttClient.getServerURI());
                    if ( topic == null || topic.size() == 0) {
                        log.warn("No subscriptions set! Subscribing to topic #, QOS 0");
                        mqttClient.subscribe("#", 0);
                    } else {
                        // Subscribe to each topic name
                        for ( String topicName : topic.keySet() ){
                            int qos;
                            if ( topic.get(topicName) == null ){
                                qos = 1;
                            } else {
                                qos = topic.get(topicName).intValue();
                            }
                            log.debug("Subscribing to topic name \"{}\", QOS {}." , topicName , qos);
                            mqttClient.subscribe(topicName, qos);
                        }
                        log.debug("All topics subscribed.");
                    }
                } catch (MqttException e) {
                    log.error("Connecting MQTT broker failed: " + e.getMessage());
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e1) {
                        log.error("Received InterruptedException while reconnecting broker: {}" , e.getMessage() );
                        Thread.currentThread().interrupt();
                    }
                }
            } // if mqtt not connected

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                log.error("Received InterruptedException: {}" , e.getMessage() );
                Thread.currentThread().interrupt();
            }
        } while (true) ;

    }

    protected MqttClient getMqttClient (BrokerDescriptor brokerDescriptor) throws MqttException{

        String clientId = brokerDescriptor.getClientId();
        if ( clientId == null ){
            clientId = MqttClient.generateClientId();
        }

        MqttClient client = new MqttClient(
                brokerDescriptor.getUris().get(0),  // URI
                clientId,                           //ClientId
                new MemoryPersistence());           //Persistence

        return client ;
    }

    protected MqttConnectOptions getMqttConnectOptions (BrokerDescriptor brokerDescriptor){

        MqttConnectOptions options = new MqttConnectOptions();
        options.setMqttVersion(brokerDescriptor.getMqttVersion());
        options.setServerURIs(brokerDescriptor.getUris().toArray(new String[0]));
        return options ;
    }

    public void addDestinations(List<HttpClientImpl> destinations) {
        this.destinations.addAll(destinations);
    }

    public void setSubscribedTopicNames(Map<String, Integer> subscribedTopicNames) {
        this.subscribedTopicNames = subscribedTopicNames;
    }

    public void addSubscribedTopicName(String topicName, int qos) {
        if (subscribedTopicNames == null){
            subscribedTopicNames = new TreeMap<String, Integer>();
        }
        subscribedTopicNames.put(topicName, qos);
    }
}
