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
//    Button temp;
//    GoogleMap googleMap;
//    TextView tvLat, tvLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        rvDashboard = findViewById(R.id.rvDashboard);
//        temp = findViewById(R.id.temp);
//        tvLat = findViewById(R.id.tvLat);
//        tvLong = findViewById(R.id.tvLong);
//
//        temp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                googleMap.clear();
//            }
//        });
//        temp.setVisibility(View.GONE);

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

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        if(mapFragment != null){
//            mapFragment.getMapAsync(this);
//        }
    }

//    @Override
//    public void onMapReady(@NonNull GoogleMap googleMap) {
//        this.googleMap = googleMap;
//        LatLng patna = new LatLng(25.5941, 85.1376);
//        googleMap.addMarker(new MarkerOptions().position(patna).draggable(true));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(patna, 13));
//        googleMap.setOnMapClickListener(this);
//
//        googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
//            @Override
//            public void onMarkerDrag(@NonNull Marker marker) {
//
//            }
//
//            @Override
//            public void onMarkerDragEnd(@NonNull Marker marker) {
//                tvLat.setText(String.valueOf(marker.getPosition().latitude));
//                tvLong.setText(String.valueOf(marker.getPosition().longitude));
//            }
//
//            @Override
//            public void onMarkerDragStart(@NonNull Marker marker) {
//                tvLat.setText("");
//                tvLong.setText("");
//            }
//        });
//        temp.setVisibility(View.VISIBLE);
//    }
//
//    @Override
//    public void onMapClick(@NonNull LatLng latLng) {
//        googleMap.clear();
//        MarkerOptions markerOption = new MarkerOptions();
//        markerOption.position(latLng);
//        markerOption.draggable(true);
//        googleMap.addMarker(markerOption);
//        tvLat.setText(String.valueOf(markerOption.getPosition().latitude));
//        tvLong.setText(String.valueOf(markerOption.getPosition().longitude));
//        Logger.d(String.valueOf(latLng.toString()));
//    }
}