package com.team.wifimanager.Adapter;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.team.wifimanager.Activity.WifiPasswordActivity;
import com.team.wifimanager.Model.AvlWifi;
import com.team.wifimanager.Model.CategoryModel;
import com.team.wifimanager.Others.Utils;
import com.team.wifimanager.R;

import java.util.ArrayList;

public class MoreAppsAdapter extends RecyclerView.Adapter<MoreAppsAdapter.MyViewholder> {
    Activity mActivity;
    ArrayList<CategoryModel> appsList;
    public MoreAppsAdapter(Activity mActivity, ArrayList<CategoryModel> appsList) {
        this.mActivity=mActivity;
        this.appsList=appsList;

    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_more_apps,parent,false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {

        holder.txtAppName.setText(appsList.get(position).getTitle());
        holder.txtRating.setText("( "+appsList.get(position).getRating()+" )");

        Glide.with(mActivity)
                .load(appsList.get(position).getBgImage())
                .into(holder.ivBgBanner);
        Glide.with(mActivity)
                .load(appsList.get(position).getIcone())
                .into(holder.ivSmallIcon);
        holder.txtInstall.setText(appsList.get(position).getBtnName());

        holder.cardApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=appsList.get(position).getUrl();

                if (url!=null)
                {
                    Uri uri = Uri.parse(url);
                    try {

                        if (mActivity!=null)
                        {
                            Intent i = new Intent("android.intent.action.MAIN");
                            i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                            i.addCategory("android.intent.category.LAUNCHER");
                            i.setData(uri);
                            mActivity.startActivity(i);
                        }
                    }
                    catch(ActivityNotFoundException e) {
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        try {
                            mActivity.startActivity(goToMarket);
                        } catch (ActivityNotFoundException ee) {
                            ee.printStackTrace();
                        }
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return appsList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        CardView cardApps;
        TextView txtAppName,txtInstall,txtRating;
        ImageView ivBgBanner,ivSmallIcon;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            txtAppName=itemView.findViewById(R.id.txtAppName);
            txtInstall=itemView.findViewById(R.id.txtInstall);
            ivBgBanner=itemView.findViewById(R.id.ivBgBanner);
            ivSmallIcon=itemView.findViewById(R.id.ivSmallIcon);
            cardApps=itemView.findViewById(R.id.cardApps);
            txtRating=itemView.findViewById(R.id.txtRating);
        }
    }
}
