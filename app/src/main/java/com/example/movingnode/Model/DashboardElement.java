package com.example.movingnode.Model;

import android.content.Context;
import android.content.Intent;

import com.example.movingnode.R;
import com.example.movingnode.Services.RV.DashboardElementImageProvider;
import com.example.movingnode.Services.RV.DashboardElementTapListener;
import com.example.movingnode.View.GestureControlActivity;
import com.example.movingnode.View.GravityControlActivity;
import com.example.movingnode.View.ManualControlActivity;
import com.example.movingnode.View.ShakeControlActivity;

public class DashboardElement implements DashboardElementTapListener, DashboardElementImageProvider {
    private String title;

    public DashboardElement(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int getImage() {
        int index = getIndex(title);
        switch (index){
            case 1:
                return R.drawable.gesture;
            case 2:
                return R.drawable.gravity;
            case 3:
                return R.drawable.shake;
            case 0:
            default:
                return R.drawable.manual;
        }
    }

    @Override
    public void onTap(Context context) {
        int index = getIndex(title);
        switch (index){
            case 1:
                context.startActivity(new Intent(context, GestureControlActivity.class));
                break;
            case 2:
                context.startActivity(new Intent(context, GravityControlActivity.class));
                break;
            case 3:
                context.startActivity(new Intent(context, ShakeControlActivity.class));
                break;
            case 0:
            default:
                context.startActivity(new Intent(context, ManualControlActivity.class));
        }
    }

    private int getIndex(String str){
        switch (str.toLowerCase()){
            case "gesture":
                return 1;
            case "gravity":
                return 2;
            case "shake":
                return 3;
            case "manual":
            default:
                return 0;
        }
    }
}