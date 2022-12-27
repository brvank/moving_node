package com.example.movingnode.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movingnode.R;
import com.example.movingnode.Services.MyAccelerometer.MyAccelerometer;
import com.example.movingnode.Services.MyOkhttpClient.MyOkHttpClient;
import com.example.movingnode.Utils.Action;

public class ShakeControlActivity extends AppCompatActivity {
    MyAccelerometer myAccelerometer;

    Action action = Action.ACTION_IDLE;
    MyOkHttpClient myOkHttpClient;

    TextView tvTransX, tvTransY, tvTransZ;
    ImageView ivToggle;

    boolean on = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake_control);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        viewsSetUp();

        //client
        myOkHttpClient = new MyOkHttpClient(this);

        accelerometerSetUp();
    }

    public void viewsSetUp(){
        tvTransX = findViewById(R.id.tvTransX);
        tvTransY = findViewById(R.id.tvTransY);
        tvTransZ = findViewById(R.id.tvTransZ);

        ivToggle = findViewById(R.id.ivToggleShake);

        ivToggle.setImageResource(R.drawable.off);
        ivToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                on = !on;
                if(on){
                    ivToggle.setImageResource(R.drawable.on);
                }else{
                    ivToggle.setImageResource(R.drawable.off);
                    action = Action.ACTION_IDLE;
                    myOkHttpClient.performAction(action);
                }
            }
        });
    }

    public void accelerometerSetUp(){
        myAccelerometer = new MyAccelerometer(this);

        myAccelerometer.setListener(new MyAccelerometer.Listener() {
            @Override
            public void onTransition(float x, float y, float z) {
                if(on){
                    if((x>=0.2 && x<=5) || (x<=-0.2 && x>=-5)){
                        tvTransX.setText(String.valueOf(x));
                        if(x >= 0){
                            action = Action.ACTION_DOWN;
                        }else {
                            action = Action.ACTION_UP;
                        }
                        myOkHttpClient.performAction(action);
                    }else{
                        tvTransX.setText("- - -");
                    }
                    if((y>=0.2 && y<=5) || (y<=-0.2 && y>=-5)){
                        tvTransY.setText(String.valueOf(y));
                        if(y >= 0){
                            action = Action.ACTION_RIGHT;
                        }else {
                            action = Action.ACTION_LEFT;
                        }
                        myOkHttpClient.performAction(action);
                    }else{
                        tvTransY.setText("- - -");
                    }
                    if((z>=0.5 && z<=5) || (z<=-0.5 && z>=-5)){
                        tvTransZ.setText(String.valueOf(z));
                    }else{
                        tvTransZ.setText("- - -");
                    }
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        myAccelerometer.unregister();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myAccelerometer.register();
    }

    @Override
    protected void onDestroy() {
        myOkHttpClient.close();
        super.onDestroy();
    }
}