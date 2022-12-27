package com.example.movingnode.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.movingnode.Model.DashboardElement;
import com.example.movingnode.R;
import com.example.movingnode.Services.RV.DashboardAdapter;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity{

    RecyclerView rvDashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        rvDashboard = findViewById(R.id.rvDashboard);

        rvDashboardStuff();
    }

    private void rvDashboardStuff(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        ArrayList<DashboardElement> dashboardElementArrayList = new ArrayList<>();
        dashboardElementArrayList.add(new DashboardElement("Manual"));
        dashboardElementArrayList.add(new DashboardElement("Gesture"));
        dashboardElementArrayList.add(new DashboardElement("Gravity"));
        dashboardElementArrayList.add(new DashboardElement("Shake"));

        DashboardAdapter dashboardAdapter = new DashboardAdapter(this, dashboardElementArrayList);
        rvDashboard.setLayoutManager(linearLayoutManager);
        rvDashboard.setAdapter(dashboardAdapter);
    }
}