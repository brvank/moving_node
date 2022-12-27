package com.example.movingnode.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.StatusBarManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.movingnode.R;

public class InitialActivity extends AppCompatActivity {

    final int SPLASH_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(InitialActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME);

    }

}