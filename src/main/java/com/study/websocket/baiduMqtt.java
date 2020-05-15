package com.study.websocket;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


class MqttReceiver implements IMqttMessageListener {
    public void messageArrived(String topic, MqttMessage message) {
        System.out.println("MQTT message received: " + message);
        if (message.toString().equals("exit")) System.exit(0);
    }

}
@Controller
@RequestMapping("/mqtt")
public class baiduMqtt {
	private MqttClient mqttClient = null;
//	final String broker       = "ssl://iotfreetest.mqtt.iot.gz.baidubce.com:1884";
//    final String clientId     = "test_mqtt_java_" + UUID.randomUUID().toString();
//    final String username     = "iotfreetest/thing01";
//    final String password     = "YU7Tov8zFW+WuaLx9s9I3MKyclie9SGDuuNkl6o9LXo=";
    final String broker       = "ssl://81891x2.mqtt.iot.gz.baidubce.com:1884";
    final String clientId     = "DeviceId-mhzoj5p1xx" + UUID.randomUUID().toString();
    final String username     = "81891x2/client1";
    final String password     = "MjODK6ZHBy8S7rGr";

    final String topic        = "demoTopic";
    final String content      = "Message from Baidu IoT demo";
	@RequestMapping("/init")
	@ResponseBody
	public void mqtt(){
        try {
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName(username);
            connOpts.setPassword(password.toCharArray());

            System.out.println("Connecting to broker: " + broker);
            mqttClient = new MqttClient(broker, clientId);
            mqttClient.connect(connOpts);
            System.out.println("Connected. Client id is " + clientId);

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
	@RequestMapping("/subscribe")
	@ResponseBody
	public void mqttSubscribe(){
		try {
			mqttClient.subscribe(topic,new MqttReceiver());
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/publish")
	@ResponseBody
	public void mqttPublish(@RequestBody String message){
		try {
			MqttMessage message1 = new MqttMessage(message.getBytes());
			mqttClient.publish(topic, message1);
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
