package com.prowify.wifimanager.Activity;

import static com.prowify.wifimanager.MainActivity.cwifiName;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.Gson;
import com.prowify.wifimanager.Adapter.WifiListAdapter;
import com.prowify.wifimanager.Model.AvlWifi;
import com.prowify.wifimanager.Model.ResponseModel;
import com.prowify.wifimanager.Others.Speed;
import com.prowify.wifimanager.Others.Utils;
import com.prowify.wifimanager.R;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class WifiListActivity extends AppCompatActivity {

    private RecyclerView rvWifiList;
    ActivityResultLauncher<Intent> locationActivityResultLauncher;
    private long mLastRxBytes = 0;
    private long mLastTxBytes = 0;
    private long mLastTime = 0;
    Speed mSpeed;
    ImageView ivBack;
    TextView txtCurrentWifi;
    RelativeLayout relPopupTop, relPopupBtm, relPopupFlot;
    LottieAnimationView ivLottieViewTop, ivLottieViewBtm, ivLottieViewFlot;
    ImageView imgBannerBtm, imgBannerTop, imgBannerFlot;
    private WebView webNote;
    FrameLayout frameBannerTop, frameBannerBtm;
    Utils utility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_list);
        onActivityResult();
        initView();
        setUpData();

    }

    private void onActivityResult() {
        locationActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        boolean isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                        if (isGpsEnabled) {
                            initView();
                        }
                    }
                });

    }

    private void setUpData() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo.isConnected()) {
//            binding.imgWifi.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_wifi_connect));
            Observable.fromCallable(() -> {
                        downloadData();
                        return true;
                    }).subscribeOn(Schedulers.io())
                    .subscribe((result) -> {
                        runOnUiThread(() -> {

                        });
                    });

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                boolean isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                if (!isGpsEnabled) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    locationActivityResultLauncher.launch(intent);
                }
            }

            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            List<ScanResult> networkList = wifiManager.getScanResults();
            String currentSSID = wifiInfo.getSSID();
            currentSSID = currentSSID.replace("\"", "");
            String type = getString(R.string.Danger);
            ArrayList<AvlWifi> wifiList = new ArrayList<>();
            if (networkList != null) {
                for (ScanResult network : networkList) {

                    String capabilities = network.capabilities;
                    if (capabilities.contains(getString(R.string.WPA2)) || capabilities.contains(getString(R.string.WPA))
                            || capabilities.contains(getString(R.string.WEP))) {
                        type = getString(R.string.Safety);
                    } else {
                        type = getString(R.string.Danger);
                    }
                    if(network.SSID.isEmpty() || network.SSID==null)
                    {
                        wifiList.add(new AvlWifi(getString(R.string.WPA2), network.BSSID, network.capabilities, String.valueOf(network.level), String.valueOf(network.frequency), type));
                    }
                    else
                    {
                        wifiList.add(new AvlWifi(network.SSID, network.BSSID, network.capabilities, String.valueOf(network.level), String.valueOf(network.frequency), type));
                    }

                }

                WifiListAdapter wifiListAdapter=new WifiListAdapter(WifiListActivity.this,wifiList);
                rvWifiList.setAdapter(wifiListAdapter);
                rvWifiList.setLayoutManager(new LinearLayoutManager(WifiListActivity.this,LinearLayoutManager.VERTICAL,false));
            }

            String wifiName = wifiInfo.getSSID();
            if (wifiName != null)
                wifiName = wifiName.replace("\"", "");
//            binding.txtNetworkName.setText(wifiName);
//            binding.txtConnectionType.setText(type);
        } else {
            Toast.makeText(this, getString(R.string.wifi_not_connect), Toast.LENGTH_SHORT).show();
//            binding.txtNetworkName.setText(getString(R.string.unknown_ssid));
//            binding.txtConnectionType.setText(getString(R.string.Unknown));
//            binding.txtSpeed.setText(getString(R.string.Network_fluctuation));
//            binding.txtSignal.setText(getString(R.string.no_Network));
//            binding.progressBar.setVisibility(View.GONE);
//            binding.imgWifi.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_wifi_disconnect));
//            binding.imgWifi.setVisibility(View.VISIBLE);
        }
    }

    private void downloadData() {

        mLastRxBytes = TrafficStats.getTotalRxBytes();
        mLastTxBytes = TrafficStats.getTotalTxBytes();
        mLastTime = System.currentTimeMillis();

        int count;
        String downloadUri = getString(R.string.download_url);
        boolean isTextAdd = false;
        try {
            URL url = new URL(downloadUri);
            URLConnection connection = url.openConnection();
            connection.connect();
            checkWifiSpeed();

            int lenghtOfFile = connection.getContentLength();
            InputStream input = new BufferedInputStream(url.openStream());
            byte[] data = new byte[1024];
            long total = 0;
            while ((count = input.read(data)) != -1) {
                total += count;
                int progress = (int) ((total * 100) / lenghtOfFile);
                if (!isTextAdd) {
                    checkWifiSpeed();
                    wifiLevel();
                    runOnUiThread(() -> {
//                        binding.progressBar.setVisibility(View.GONE);
//                        binding.imgWifi.setVisibility(View.VISIBLE);
                    });
                    isTextAdd = true;
                }
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkWifiSpeed() {
        long currentRxBytes = TrafficStats.getTotalRxBytes();
        long currentTxBytes = TrafficStats.getTotalTxBytes();
        long usedRxBytes = currentRxBytes - mLastRxBytes;
        long usedTxBytes = currentTxBytes - mLastTxBytes;
        long currentTime = System.currentTimeMillis();
        long usedTime = currentTime - mLastTime;

        mLastRxBytes = currentRxBytes;
        mLastTxBytes = currentTxBytes;
        mLastTime = currentTime;
        mSpeed.calcSpeed(usedTime, usedRxBytes, usedTxBytes);

        Speed.HumanSpeed speedToShow = mSpeed.getHumanSpeed(getString(R.string.down));

        runOnUiThread(() -> {
//            binding.txtSpeed.setText(speedToShow.speedValue + " " + speedToShow.speedUnit);
        });
    }

    private void wifiLevel() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        int numberOfLevels = 5;
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int level = WifiManager.calculateSignalLevel(wifiInfo.getRssi(), numberOfLevels);
        String strLevel;
        if (level == 2)
            strLevel = getString(R.string.POOR);
        else if (level == 3)
            strLevel = getString(R.string.MODERATE);
        else if (level == 4)
            strLevel = getString(R.string.GOOD);
        else if (level == 5)
            strLevel = getString(R.string.EXCELLENT);
        else
            strLevel = getString(R.string.UNKNOWN);

        runOnUiThread(() -> {
//            binding.txtSignal.setText(strLevel);
        });
    }

    private void initView() {
        rvWifiList = findViewById(R.id.rvWifiList);
        ivBack = findViewById(R.id.ivBack);
        txtCurrentWifi = findViewById(R.id.txtCurrentWifi);
        utility=new Utils();
        txtCurrentWifi.setText(cwifiName);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

        ResponseModel responseMain = new Gson().fromJson(Utils.getAsync(WifiListActivity.this, "HomeData"), ResponseModel.class);
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
                        utility.setCustomAds(WifiListActivity.this, responseMain.getTopAds(), relPopupTop, ivLottieViewTop, imgBannerTop);
                    } else if (responseMain.getTopAds().getType().matches("2")) {
                        frameBannerTop.setVisibility(View.VISIBLE);
                        utility.showLovinBanner(WifiListActivity.this, frameBannerTop);
                    } else if (responseMain.getTopAds().getType().matches("3")) {
                        frameBannerTop.setVisibility(View.VISIBLE);
                        utility.showGoogleBanner(WifiListActivity.this, frameBannerTop);
                    }
                }
            }

            if (responseMain.getButtomeAds() != null) {
                if (responseMain.getButtomeAds().getType() != null) {
                    if (responseMain.getButtomeAds().getType().matches("1")) {
                        frameBannerBtm.setVisibility(View.GONE);
                        utility.setCustomAds(WifiListActivity.this, responseMain.getButtomeAds(), relPopupBtm, ivLottieViewBtm, imgBannerBtm);
                    } else if (responseMain.getButtomeAds().getType().matches("2")) {
                        frameBannerBtm.setVisibility(View.VISIBLE);
                        utility.showLovinBanner(WifiListActivity.this, frameBannerBtm);
                    } else if (responseMain.getButtomeAds().getType().matches("3")) {
                        frameBannerBtm.setVisibility(View.VISIBLE);
                        utility.showGoogleBanner(WifiListActivity.this, frameBannerBtm);
                    }
                }
            }

            if (responseMain.getIndstrialAds() != null) {

                if (responseMain.getIndstrialAds().matches("2"))
                {
                    utility.showLovinInter(WifiListActivity.this, responseMain.getAdFailUrl());
                }
                else if (responseMain.getIndstrialAds().matches("3"))
                {
                    utility.showGoogleInter(WifiListActivity.this, responseMain.getAdFailUrl());
                }
            }

            if (responseMain.getFlotingAds() != null) {
                utility.setCustomAds(WifiListActivity.this, responseMain.getFlotingAds(), relPopupFlot, ivLottieViewFlot, imgBannerFlot);
            }
        }

    }
}