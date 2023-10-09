package com.prowify.wifimanager;

import static com.prowify.wifimanager.MainActivity.mMoreApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.Gson;
import com.prowify.wifimanager.Adapter.MoreAppsAdapter;
import com.prowify.wifimanager.Model.ResponseModel;
import com.prowify.wifimanager.Others.Utils;

public class MoreAppsActivity extends AppCompatActivity {

    ImageView ivBack;
    RecyclerView rvMoreApps;
    RelativeLayout relPopupTop, relPopupBtm, relPopupFlot;
    LottieAnimationView ivLottieViewTop, ivLottieViewBtm, ivLottieViewFlot;
    ImageView imgBannerBtm, imgBannerTop, imgBannerFlot;
    private WebView webNote;
    FrameLayout frameBannerTop, frameBannerBtm;
    Utils utility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_apps);
        init();
        setAdapter();
        ResponseModel responseMain = new Gson().fromJson(Utils.getAsync(MoreAppsActivity.this, "HomeData"), ResponseModel.class);
        setData(responseMain);
    }

    private void setAdapter() {

        MoreAppsAdapter moreAppsAdapter=new MoreAppsAdapter(MoreAppsActivity.this,mMoreApp);
        rvMoreApps.setAdapter(moreAppsAdapter);
        rvMoreApps.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
    }
    public void setData(ResponseModel responseMain) {

        if(responseMain!=null)
        {
            if (!Utils.isStringNullOrEmpty(responseMain.getHomeNote())) {
                webNote.setVisibility(View.VISIBLE);
                webNote.loadDataWithBaseURL(null, responseMain.getHomeNote(), "text/html", "UTF-8", null);
            }

            if (responseMain.getTopAds() != null) {
                if (responseMain.getTopAds().getType() != null) {
                    if (responseMain.getTopAds().getType().matches("1")) {
                        frameBannerTop.setVisibility(View.GONE);
                        utility.setCustomAds(MoreAppsActivity.this, responseMain.getTopAds(), relPopupTop, ivLottieViewTop, imgBannerTop);
                    } else if (responseMain.getTopAds().getType().matches("2")) {
                        frameBannerTop.setVisibility(View.VISIBLE);
                        utility.showLovinBanner(MoreAppsActivity.this, frameBannerTop);
                    } else if (responseMain.getTopAds().getType().matches("3")) {
                        frameBannerTop.setVisibility(View.VISIBLE);
                        utility.showGoogleBanner(MoreAppsActivity.this, frameBannerTop);
                    }
                }
            }

            if (responseMain.getButtomeAds() != null) {
                if (responseMain.getButtomeAds().getType() != null) {
                    if (responseMain.getButtomeAds().getType().matches("1")) {
                        frameBannerBtm.setVisibility(View.GONE);
                        utility.setCustomAds(MoreAppsActivity.this, responseMain.getButtomeAds(), relPopupBtm, ivLottieViewBtm, imgBannerBtm);
                    } else if (responseMain.getButtomeAds().getType().matches("2")) {
                        frameBannerBtm.setVisibility(View.VISIBLE);
                        utility.showLovinBanner(MoreAppsActivity.this, frameBannerBtm);
                    } else if (responseMain.getButtomeAds().getType().matches("3")) {
                        frameBannerBtm.setVisibility(View.VISIBLE);
                        utility.showGoogleBanner(MoreAppsActivity.this, frameBannerBtm);
                    }
                }
            }

            if (responseMain.getIndstrialAds() != null) {

                if (responseMain.getIndstrialAds().matches("2"))
                {
                    utility.showLovinInter(MoreAppsActivity.this, responseMain.getAdFailUrl());
                }
                else if (responseMain.getIndstrialAds().matches("3"))
                {
                    utility.showGoogleInter(MoreAppsActivity.this, responseMain.getAdFailUrl());
                }
            }

            if (responseMain.getFlotingAds() != null) {
                utility.setCustomAds(MoreAppsActivity.this, responseMain.getFlotingAds(), relPopupFlot, ivLottieViewFlot, imgBannerFlot);
            }
        }

    }

    public void init(){

        ivBack=findViewById(R.id.ivBack);
        rvMoreApps=findViewById(R.id.rvMoreApps);
        utility=new Utils();
        relPopupTop = findViewById(R.id.relPopupTop);
        relPopupBtm = findViewById(R.id.relPopupBottom);
        relPopupFlot = findViewById(R.id.relPopupFlot);
        ivLottieViewTop = findViewById(R.id.ivLottieViewTop);
        ivLottieViewBtm = findViewById(R.id.ivLottieViewBtm);
        ivLottieViewFlot = findViewById(R.id.ivLottieViewFlot);
        imgBannerBtm = findViewById(R.id.imgBannerBtm);
        imgBannerTop = findViewById(R.id.imgBannerTop);
        imgBannerFlot = findViewById(R.id.imgBannerFlot);
        webNote = findViewById(R.id.webNote);
        frameBannerTop = findViewById(R.id.frameBannerTop);
        frameBannerBtm = findViewById(R.id.frameBannerBtm);
        ivBack.setOnClickListener(v -> finish());
    }
}