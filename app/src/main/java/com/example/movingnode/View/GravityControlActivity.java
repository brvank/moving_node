package com.example.movingnode.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movingnode.R;
import com.example.movingnode.Services.Log.LOG;
import com.example.movingnode.Services.MyGyroscope.MyGyroscope;
import com.example.movingnode.Services.MyOkhttpClient.MyOkHttpClient;
import com.example.movingnode.Utils.Action;

public class GravityControlActivity extends AppCompatActivity {
    MyGyroscope myGyroscope;

    Action action = Action.ACTION_IDLE;
    MyOkHttpClient myOkHttpClient;

    TextView tvRotX, tvRotY, tvRotZ;

    ImageView ivToggle;

    boolean on = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gravity_control);
        setTitle(R.string.gravity);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        viewsSetUp();

        //client
        myOkHttpClient = new MyOkHttpClient(this);

        gyroscopeSetUp();
    }

    public void viewsSetUp(){
        tvRotX = findViewById(R.id.tvRotX);
        tvRotY = findViewById(R.id.tvRotY);
        tvRotZ = findViewById(R.id.tvRotZ);

        ivToggle = findViewById(R.id.ivToggleGravity);

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

    public void gyroscopeSetUp(){
        myGyroscope = new MyGyroscope(this);

        myGyroscope.setListener(new MyGyroscope.Listener() {
            @Override
            public void onRotation(float x, float y, float z) {
                if(on){
                    if((x>=0.2 && x<=5) || (x<=-0.2 && x>=-5)){
                        tvRotX.setText(String.valueOf(x));
                        if(x >= 0){
                            action = Action.ACTION_DOWN;
                        }else {
                            action = Action.ACTION_UP;
                        }
                        myOkHttpClient.performAction(action);
                    }else{
                        tvRotX.setText("- - -");
                    }
                    if((y>=0.2 && y<=5) || (y<=-0.2 && y>=-5)){
                        tvRotY.setText(String.valueOf(y));
                        if(y >= 0){
                            action = Action.ACTION_RIGHT;
                        }else {
                            action = Action.ACTION_LEFT;
                        }
                        myOkHttpClient.performAction(action);
                    }else{
                        tvRotY.setText("- - -");
                    }
                    if((z>=0.5 && z<=5) || (z<=-0.5 && z>=-5)){
                        tvRotZ.setText(String.valueOf(z));
                    }else{
                        tvRotZ.setText("- - -");
                    }
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        myGyroscope.unregister();
    }

    @Override
    protected void onResume() {
        super.onResume();
        myGyroscope.register();
    }

    @Override
    protected void onDestroy() {
        myOkHttpClient.close();
        super.onDestroy();
    }
}