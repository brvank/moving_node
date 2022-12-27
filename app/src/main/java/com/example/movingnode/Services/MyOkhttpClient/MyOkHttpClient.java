package com.example.movingnode.Services.MyOkhttpClient;

import static com.example.movingnode.Utils.Constants.CLOSING_CODE;
import static com.example.movingnode.Utils.Constants.CLOSING_MESSAGE;
import static com.example.movingnode.Utils.Constants.WebsocketUrl;

import android.content.Context;
import android.widget.Toast;

import com.example.movingnode.Services.Log.LOG;
import com.example.movingnode.Services.MyWebSocket.MyWebSocketListener;
import com.example.movingnode.Utils.Action;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class MyOkHttpClient {

    Context context;
    OkHttpClient client;
    WebSocket webSocket;

    public MyOkHttpClient(Context context){
        this.context = context;

        //client setup
        clientSetUp();
    }

    void clientSetUp(){
        client = new OkHttpClient();
        Request request = new Request.Builder().url(WebsocketUrl).build();
        MyWebSocketListener myWebSocketListener = new MyWebSocketListener(context);
        webSocket = client.newWebSocket(request, myWebSocketListener);

        client.dispatcher().executorService().shutdown();
    }

    public void performAction(Action action){
        switch (action){
            case ACTION_UP:
                //move forward
                moveForward();
                break;
            case ACTION_DOWN:
                //move backward
                moveBackward();
                break;
            case ACTION_LEFT:
                //move left
                moveLeft();
                break;
            case ACTION_RIGHT:
                //move right
                moveRight();
                break;
            case ACTION_IDLE:
                //stop device
                stopDevice();
                break;
            default:
                break;
        }
    }

    void stopDevice(){
        move(0);
    }

    void moveForward(){
        move(1);
    }

    void moveBackward(){
        move(2);
    }

    void moveLeft(){
        move(3);
    }

    void moveRight(){
        move(4);
    }

    void move(int direction){
        try{
            webSocket.send(String.valueOf(direction));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void close(){
        webSocket.close(CLOSING_CODE, CLOSING_MESSAGE);
    }

}
