package com.study.util;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

class mqttReceiver implements IMqttMessageListener {

    public void messageArrived(String topic, MqttMessage message) {
        System.out.println("MQTT message received: " + message);
        if (message.toString().equals("exit")) System.exit(0);
    }

}