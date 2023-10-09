package com.prowify.wifimanager.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prowify.wifimanager.Model.DeviceItem;
import com.prowify.wifimanager.R;

import java.util.ArrayList;

public class WifiAnalyserAdapter extends RecyclerView.Adapter<WifiAnalyserAdapter.MyViewholder> {
    Activity mActivity;
    ArrayList<DeviceItem> mList;

    public WifiAnalyserAdapter(Activity mActivity, ArrayList<DeviceItem> mList) {
        this.mActivity=mActivity;
        this.mList=mList;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_wifi_analyser,parent,false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {

        holder.txtIPAddress.setText(mList.get(position).getIpAddress());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        LinearLayout lAnalyser;
        TextView txtIPAddress;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            txtIPAddress=itemView.findViewById(R.id.txtIPAddress);
            lAnalyser=itemView.findViewById(R.id.lAnalyser);
        }
    }
}
