package com.example.movingnode.Services.RV;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movingnode.Model.DashboardElement;
import com.example.movingnode.R;

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DashboardElement> dashboardElementArrayList;

    public DashboardAdapter(Context context, ArrayList<DashboardElement> dashboardElementArrayList) {
        this.context = context;
        this.dashboardElementArrayList = dashboardElementArrayList;
    }

    @NonNull
    @Override
    public DashboardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_dashboard,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardAdapter.ViewHolder holder, int position) {
        int pos = position;
        holder.tvElementTitle.setText(dashboardElementArrayList.get(position).getTitle());
        holder.ivElementImage.setImageDrawable(context.getDrawable(dashboardElementArrayList.get(position).getImage()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dashboardElementArrayList.get(pos).onTap(context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dashboardElementArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivElementImage;
        TextView tvElementTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivElementImage = itemView.findViewById(R.id.ivElementImage);
            tvElementTitle = itemView.findViewById(R.id.tvElementTitle);
        }
    }
}
