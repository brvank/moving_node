package com.example.movingnode.Services.MyWebSocket;

import static com.example.movingnode.Utils.Constants.CLOSING_CODE;
import static com.example.movingnode.Utils.Constants.CLOSING_MESSAGE;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.movingnode.Services.Log.LOG;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class MyWebSocketListener extends WebSocketListener{

    Context context;

    public MyWebSocketListener(Context context) {
        this.context = context;
    }

    @Override
    public void onClosed(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
        LOG.d(reason);
        createToast(reason);
        webSocket.close(CLOSING_CODE, CLOSING_MESSAGE);
    }

    @Override
    public void onClosing(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
        LOG.d(reason);
        createToast(reason);
        webSocket.close(CLOSING_CODE, CLOSING_MESSAGE);
    }

    @Override
    public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable t, @Nullable Response response) {
        if(response != null){
            LOG.d(response.message());
        }
        LOG.d(t.getMessage());
        createToast("Disconnected");
        webSocket.close(CLOSING_CODE, CLOSING_MESSAGE);
    }

    @Override
    public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
        LOG.d(text);
        createToast(text);
    }

    @Override
    public void onMessage(@NonNull WebSocket webSocket, @NonNull ByteString bytes) {
        LOG.d(bytes.toString());
        createToast(bytes.toString());
    }

    @Override
    public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
        LOG.d("connection open");
        createToast("Connected");
    }

    void createToast(String message){
        ((Activity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
