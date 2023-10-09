package com.prowify.wifimanager.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prowify.wifimanager.Activity.WifiPasswordActivity;
import com.prowify.wifimanager.Model.AvlWifi;
import com.prowify.wifimanager.R;

import java.util.ArrayList;

public class WifiListAdapter extends RecyclerView.Adapter<WifiListAdapter.MyViewholder> {
    Activity mActivity;
    ArrayList<AvlWifi> wifiList;
    public WifiListAdapter(Activity mActivity, ArrayList<AvlWifi> wifiList) {
        this.mActivity=mActivity;
        this.wifiList=wifiList;

    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_wifi_avl,parent,false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {

        holder.txtWifiMac.setText(wifiList.get(position).getWifiMac());
        holder.txtWifiSID.setText(wifiList.get(position).getWifiName());

        holder.lAvlWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mActivity, WifiPasswordActivity.class);
                intent.putExtra("from","indirect");
                intent.putExtra("sid",wifiList.get(position).getWifiName());
                intent.putExtra("mac",wifiList.get(position).getWifiMac());
                intent.putExtra("security",wifiList.get(position).getWifiSigneltype());
                intent.putExtra("level",wifiList.get(position).getWifiLevel());
                intent.putExtra("frequency",wifiList.get(position).getWifiFrn());
                mActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wifiList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        LinearLayout lAvlWifi;
        TextView txtWifiSID,txtWifiMac;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            txtWifiSID=itemView.findViewById(R.id.txtWifiSID);
            lAvlWifi=itemView.findViewById(R.id.lAvlWifi);
            txtWifiMac=itemView.findViewById(R.id.txtWifiMac);
        }
    }
}
