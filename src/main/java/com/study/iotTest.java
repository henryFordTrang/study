package com.study;
import java.lang.Thread;
import java.util.UUID;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
//class MqttReceiver implements IMqttMessageListener {
//
//    @Override
//    public void messageArrived(String topic, MqttMessage message) {
//        System.out.println("MQTT message received: " + message);
//        if (message.toString().equals("exit")) System.exit(0);
//    }
//
//}
public class iotTest {
	public static void main(String[] args) {
        final String broker       = "tcp://cyqitud.mqtt.iot.gz.baidubce.com:1883";
        							 //tcp://81891x2.mqtt.iot.gz.baidubce.com:1883
        //final String clientId     = "test_mqtt_java_" + UUID.randomUUID().toString();
        final String clientId     = "temperatureshadow" + UUID.randomUUID().toString();
        final String username     = "cyqitud/temperatureshadow";
        final String password     = "f1447wy0mq824v13";

        final String topic        = "$baidu/iot/shadow/temperatureshadow/update";
        JsonObject inner =new JsonObject();
        inner.addProperty("temperature", 20);
        inner.addProperty("humidity", 20);
//        JsonObject  content =new JsonObject();
//        content.addProperty("reported", inner);
        final String content      = "Message from Baidu IoT demo";

        try {
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName(username);
            connOpts.setPassword(password.toCharArray());

            System.out.println("Connecting to broker: " + broker);
            MqttClient mqttClient = new MqttClient(broker, clientId);
            mqttClient.connect(connOpts);
            System.out.println("Connected. Client id is " + clientId+"----mqttClient:"+mqttClient);

            mqttClient.subscribe(topic, new MqttReceiver());
            System.out.println("Subscribed to topic: " + topic);

            MqttMessage message = new MqttMessage(content.getBytes());
            mqttClient.publish(topic, message);
            System.out.println("Published message: " + content);

            Thread.sleep(Long.MAX_VALUE);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
