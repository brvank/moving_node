package com.example.movingnode.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movingnode.R;
import com.example.movingnode.Services.Log.LOG;
import com.example.movingnode.Services.MyOkhttpClient.MyOkHttpClient;
import com.example.movingnode.Utils.Action;

public class GestureControlActivity extends AppCompatActivity {

    GestureDetectorCompat gestureDetectorCompat;

    Action action = Action.ACTION_IDLE;
    MyOkHttpClient myOkHttpClient;

    TextView tvGestX, tvGestY;
    ImageView ivToggle;

    boolean on = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_control);
        setTitle(R.string.gesture);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        //setting up text views
        tvGestX = findViewById(R.id.tvGestX);
        tvGestY = findViewById(R.id.tvGestY);
        ivToggle = findViewById(R.id.ivToggleGesture);

        ivToggle.setImageResource(R.drawable.off);
        ivToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                on = !on;
                if(on){
                    ivToggle.setImageResource(R.drawable.on);
                }else{
                    ivToggle.setImageResource(R.drawable.off);
                }
            }
        });

        //client
        myOkHttpClient = new MyOkHttpClient(this);

        //gesture detector
        gestureDetectorCompat = new GestureDetectorCompat(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                if(on){
                    tvGestX.setText(String.valueOf(v));
                    tvGestY.setText(String.valueOf(v1));

                    if(v > 0){
                        action = Action.ACTION_LEFT;
                        myOkHttpClient.performAction(action);
                    }else{
                        action = Action.ACTION_RIGHT;
                        myOkHttpClient.performAction(action);
                    }

                    if(v1 > 0){
                        action = Action.ACTION_UP;
                        myOkHttpClient.performAction(action);
                    }else{
                        action = Action.ACTION_DOWN;
                        myOkHttpClient.performAction(action);
                    }
                }else{
                    action = Action.ACTION_IDLE;
                    myOkHttpClient.performAction(action);
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {

            }

            @Override
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
                return false;
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        myOkHttpClient.close();
        super.onDestroy();
    }
}