package com.prowify.wifimanager.Activity;

import static com.prowify.wifimanager.MainActivity.cwifiName;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.Gson;
import com.prowify.wifimanager.Adapter.WifiAnalyserAdapter;
import com.prowify.wifimanager.Interface.OnDeviceFoundListener;
import com.prowify.wifimanager.Model.DeviceItem;
import com.prowify.wifimanager.Model.ResponseModel;
import com.prowify.wifimanager.Others.DeviceFinder;
import com.prowify.wifimanager.Others.Utils;
import com.prowify.wifimanager.R;

import java.util.ArrayList;
import java.util.List;

public class WifiAnalyserActivity extends AppCompatActivity {

    ImageView ivBack;
    TextView txtCurrentWifi,txtConnectedDevice;
    RecyclerView rvWifiAnalyzer;
    private final ArrayList<DeviceItem> devices = new ArrayList<>();
    private long start, end;
    ProgressDialog dialog;

    RelativeLayout relPopupTop, relPopupBtm, relPopupFlot;
    LottieAnimationView ivLottieViewTop, ivLottieViewBtm, ivLottieViewFlot;
    ImageView imgBannerBtm, imgBannerTop, imgBannerFlot;
    private WebView webNote;
    FrameLayout frameBannerTop, frameBannerBtm;
    Utils utility;
    String failedUrl,isInterstial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_analyser);
        init();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtCurrentWifi.setText(cwifiName);
//        setAdapter();
    }
    private void setAdapter() {
        dialog = ProgressDialog.show(WifiAnalyserActivity.this, "",
                "Getting Data...", true);
        DeviceFinder devicesFinder = new DeviceFinder(this, new OnDeviceFoundListener() {

            @Override
            public void onStart(DeviceFinder deviceFinder) {
                start = System.currentTimeMillis();
            }

            @Override
            public void onFinished(DeviceFinder deviceFinder, List<DeviceItem> deviceItems) {
                end = System.currentTimeMillis();


                for (DeviceItem deviceItem : deviceItems) {
                    String data = "Device Name: " + deviceItem.getDeviceName() + "\n" +
                            "Ip Address: " + deviceItem.getIpAddress() + "\n" +
                            "MAC Address: " + deviceItem.getMacAddress() + "\n" +
                            "Vendor Name: " + deviceItem.getVendorName();

                    devices.add(deviceItem);
                }
                if (dialog!=null)
                {
                    dialog.dismiss();
                }

                if (isInterstial!= null) {

                    if (isInterstial.matches("2"))
                    {
                        utility.showLovinInter(WifiAnalyserActivity.this, failedUrl);
                    }
                    else if (isInterstial.matches("3"))
                    {
                        utility.showGoogleInter(WifiAnalyserActivity.this, failedUrl);
                    }
                }
                txtConnectedDevice.setText(String.valueOf(devices.size())+" Device Connected");
                WifiAnalyserAdapter mAdapter=new WifiAnalyserAdapter(WifiAnalyserActivity.this,devices);
                rvWifiAnalyzer.setAdapter(mAdapter);
                rvWifiAnalyzer.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
            }
            @Override
            public void onFailed(DeviceFinder deviceFinder, int errorCode) {

            }
        });

        devicesFinder.setTimeout(500).start();
    }

    public void init()
    {
        utility=new Utils();
        ivBack=findViewById(R.id.ivBack);
        txtCurrentWifi=findViewById(R.id.txtCurrentWifi);
        txtConnectedDevice=findViewById(R.id.txtConnectedDevice);
        rvWifiAnalyzer=findViewById(R.id.rvWifiAnalyzer);

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

        ResponseModel responseMain = new Gson().fromJson(Utils.getAsync(WifiAnalyserActivity.this, "HomeData"), ResponseModel.class);
        setData(responseMain);
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
                        utility.setCustomAds(WifiAnalyserActivity.this, responseMain.getTopAds(), relPopupTop, ivLottieViewTop, imgBannerTop);
                    } else if (responseMain.getTopAds().getType().matches("2")) {
                        frameBannerTop.setVisibility(View.VISIBLE);
                        utility.showLovinBanner(WifiAnalyserActivity.this, frameBannerTop);
                    } else if (responseMain.getTopAds().getType().matches("3")) {
                        frameBannerTop.setVisibility(View.VISIBLE);
                        utility.showGoogleBanner(WifiAnalyserActivity.this, frameBannerTop);
                    }
                }
            }
            if (responseMain.getButtomeAds() != null) {
                if (responseMain.getButtomeAds().getType() != null) {
                    if (responseMain.getButtomeAds().getType().matches("1")) {
                        frameBannerBtm.setVisibility(View.GONE);
                        utility.setCustomAds(WifiAnalyserActivity.this, responseMain.getButtomeAds(), relPopupBtm, ivLottieViewBtm, imgBannerBtm);
                    } else if (responseMain.getButtomeAds().getType().matches("2")) {
                        frameBannerBtm.setVisibility(View.VISIBLE);
                        utility.showLovinBanner(WifiAnalyserActivity.this, frameBannerBtm);
                    } else if (responseMain.getButtomeAds().getType().matches("3")) {
                        frameBannerBtm.setVisibility(View.VISIBLE);
                        utility.showGoogleBanner(WifiAnalyserActivity.this, frameBannerBtm);
                    }
                }
            }

            if (responseMain.getIndstrialAds() != null) {
                failedUrl=responseMain.getAdFailUrl();
                isInterstial=responseMain.getIndstrialAds();
            }


            if (responseMain.getFlotingAds() != null) {
                utility.setCustomAds(WifiAnalyserActivity.this, responseMain.getFlotingAds(), relPopupFlot, ivLottieViewFlot, imgBannerFlot);
            }
        }

    }
}