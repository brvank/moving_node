package com.example.movingnode.View;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movingnode.R;
import com.example.movingnode.Services.Log.LOG;
import com.example.movingnode.Services.MyOkhttpClient.MyOkHttpClient;
import com.example.movingnode.Utils.Action;
import com.example.movingnode.Utils.widget.CImageView;

public class ManualControlActivity extends AppCompatActivity {

    CImageView ivLeft, ivRight, ivUp, ivDown;
    Action action = Action.ACTION_IDLE;
    MyOkHttpClient myOkHttpClient;

    TextView tvDirStat;
    ImageView ivToggle;

    boolean on = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_control);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        //setting up views
        ivLeft = findViewById(R.id.ivLeft);
        ivRight = findViewById(R.id.ivRight);
        ivUp = findViewById(R.id.ivUp);
        ivDown = findViewById(R.id.ivDown);
        tvDirStat = findViewById(R.id.tvDirStat);
        ivToggle = findViewById(R.id.ivToggleManual);

        ivToggle.setImageResource(R.drawable.off);
        ivToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                on = !on;
                if(on){
                    ivToggle.setImageResource(R.drawable.on);
                }else{
                    tvDirStat.setText("- - -");
                    ivToggle.setImageResource(R.drawable.off);
                    action = Action.ACTION_IDLE;
                    myOkHttpClient.performAction(action);
                }
            }
        });

        //client
        myOkHttpClient = new MyOkHttpClient(this);

        //setting click listeners
        setClickListeners();

    }

    void stopDevice(){
        tvDirStat.setText("- - -");
        action = Action.ACTION_IDLE;
        myOkHttpClient.performAction(action);
    }

    void setClickListeners(){

        //for left button
        ivLeft.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    if (on) {
                        action = Action.ACTION_LEFT;
                        myOkHttpClient.performAction(action);
                        tvDirStat.setText("LEFT");
                    }
                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    stopDevice();
                }
                return true;
            }

        });

        //for right button
        ivRight.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    if (on) {
                        action = Action.ACTION_RIGHT;
                        myOkHttpClient.performAction(action);
                        tvDirStat.setText("RIGHT");
                    }
                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    stopDevice();
                }
                return true;
            }

        });

        //for up button
        ivUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                LOG.d("here");
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    if (on) {
                        action = Action.ACTION_UP;
                        myOkHttpClient.performAction(action);
                        tvDirStat.setText("FORWARD");
                    }
                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    stopDevice();
                }
                return true;
            }
        });

        //for down button
        ivDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN || motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    if (on) {
                        action = Action.ACTION_DOWN;
                        myOkHttpClient.performAction(action);
                        tvDirStat.setText("BACKWARD");
                    }
                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    stopDevice();
                }
                return true;
            }

        });


    }

    @Override
    protected void onDestroy() {
        myOkHttpClient.close();
        super.onDestroy();
    }
}