package org.opentsdb.parser;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.opentsdb.device.model.Point;
import org.opentsdb.builder.MetricBuilder;

import java.text.ParseException;

/***
 * Describes how to interpret a topic name and message payload into a net.michalfoksa.mqtt2influxdb.dto.Point
 *
 * @author Michal Foksa
 *
 */
public interface PayloadParser {

    public Point parse(String topicName, MqttMessage message) throws ParseException;
    public MetricBuilder parseList(String topicName, MqttMessage message) throws ParseException;


}
