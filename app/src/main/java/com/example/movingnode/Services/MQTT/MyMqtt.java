/*
package com.example.movingnode.Services.MQTT;

import static com.example.movingnode.Utils.Constants.ClientId;
import static com.example.movingnode.Utils.Constants.Password;
import static com.example.movingnode.Utils.Constants.ServerURI;
import static com.example.movingnode.Utils.Constants.UserName;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.movingnode.Services.Log.LOG;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MyMqtt {

    Context context;
    MqttAndroidClient mqttAndroidClient;
    IMqttToken iMqttToken;
    String clientId;
    String topic;
    MqttConnectOptions mqttConnectOptions;

    public MyMqtt(Context context, String topic){
        this.context = context;
        clientId = MqttClient.generateClientId();
        LOG.d(clientId);
        this.topic = topic;
        mqttAndroidClient = new MqttAndroidClient(context, ServerURI, clientId);

        //setting up connect options
        mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(UserName);
        mqttConnectOptions.setPassword(Password.toCharArray());
        mqttConnectOptions.setKeepAliveInterval(3600);

        //connecting to the server
        connect();
    }

    public void connect(){
        try {
            iMqttToken = mqttAndroidClient.connect(mqttConnectOptions);
            iMqttToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(context, "Error while connecting\nPlease try again!", Toast.LENGTH_SHORT).show();
                    LOG.e("Not Connected: " + exception.getMessage());
                    ((Activity)context).finish();
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, "Error while connecting\nPlease try again!", Toast.LENGTH_SHORT).show();
            ((Activity)context).finish();
            e.printStackTrace();
            LOG.e("Exception: " + e.getMessage());
        }

        mqttAndroidClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                Toast.makeText(context, "Connection lost!\nPlease connect again", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Toast.makeText(context, topic + " : " + message.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
    }

    public void disconnect(){
        try {
            iMqttToken = mqttAndroidClient.disconnect();
            iMqttToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(context, "Error while disconnecting with MQTT client. Please try again!", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, "Error while disconnecting with MQTT client. Please try again!", Toast.LENGTH_SHORT).show();
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
            LOG.e("Exception: " + e.getMessage());
        }
    }

    public void subscribe(){
        try {
            iMqttToken = mqttAndroidClient.subscribe(topic, 0);
            iMqttToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(context, "Subscribed to " + topic +  "!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(context, "Subscription for " + topic + " failed!", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            LOG.e("Exception: " + e.getMessage());
        }
    }

    public void publish(String message){
        try {
            iMqttToken = mqttAndroidClient.publish(topic, message.getBytes(), 0, false);
            iMqttToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast.makeText(context,"Message not sent\nTarget device might not be connected!", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
            LOG.e("Exception: " + e.getMessage());
        }
    }

}
*/
