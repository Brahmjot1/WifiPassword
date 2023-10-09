package com.prowify.wifimanager.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.prowify.wifimanager.Async.HomeDataAsync;
import com.prowify.wifimanager.MainActivity;
import com.prowify.wifimanager.Others.Utils;
import com.prowify.wifimanager.R;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity implements MaxAdListener
{

    TextView txtStart;
//    private static String Inter_AD_ID="ca-app-pub-3940256099942544/8691691433";
    ActivityResultLauncher<Intent> permissionSettingActivityResultLauncher;


    private MaxInterstitialAd interstitialAd;
    private int retryAttempt;

    void createInterstitialAd()
    {
        interstitialAd = new MaxInterstitialAd( "ca-app-pub-3940256099942544/1033173712", this );
        interstitialAd.setListener(this);

        // Load the first ad
        interstitialAd.loadAd();
    }

    // MAX Ad Listener
    @Override
    public void onAdLoaded(final MaxAd maxAd)
    {
        // Interstitial ad is ready to be shown. interstitialAd.isReady() will now return 'true'

        // Reset retry attempt
        retryAttempt = 0;
    }

    @Override
    public void onAdLoadFailed(final String adUnitId, final MaxError error)
    {
        // Interstitial ad failed to load
        // AppLovin recommends that you retry with exponentially higher delays up to a maximum delay (in this case 64 seconds)

        retryAttempt++;
        long delayMillis = TimeUnit.SECONDS.toMillis( (long) Math.pow( 2, Math.min( 6, retryAttempt ) ) );

        new Handler().postDelayed( new Runnable()
        {
            @Override
            public void run()
            {
                interstitialAd.loadAd();
            }
        }, delayMillis );
    }

    @Override
    public void onAdDisplayFailed(final MaxAd maxAd, final MaxError error)
    {
        // Interstitial ad failed to display. AppLovin recommends that you load the next ad.
        interstitialAd.loadAd();
    }

    @Override
    public void onAdDisplayed(final MaxAd maxAd) {}

    @Override
    public void onAdClicked(final MaxAd maxAd) {}

    @Override
    public void onAdHidden(final MaxAd maxAd)
    {
        // Interstitial ad is hidden. Pre-load the next ad
        interstitialAd.loadAd();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(1024,1024);
        setContentView(R.layout.activity_splash);
        initView();
        createInterstitialAd();



    }

    private void initView() {

        permissionSettingActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (checkLocationPermission()) {
                            showHomeScreen();
                        }
                    }
                });

        txtStart = findViewById(R.id.txtStart);

        int todayOpen=Utils.getTodayOpen(SplashActivity.this)+1;
        Utils.setTodayOpen(SplashActivity.this,todayOpen);

        if (Utils.getDate(SplashActivity.this).matches("0"))
        {
            Utils.setDate(SplashActivity.this,getCurrentDate());
        }

        if (!Utils.getDate(SplashActivity.this).matches(getCurrentDate()))
        {
            Utils.setTodayOpen(SplashActivity.this,1);
            Utils.setTotalOpen(SplashActivity.this,Utils.getTotalOpen(SplashActivity.this)+1);
            Utils.setDate(SplashActivity.this,getCurrentDate());
        }


        try {
            if (getIntent().getStringExtra("bundle") != null && getIntent().getStringExtra("bundle").trim().length() > 0) {
                Utils.setNotificationData(SplashActivity.this, getIntent().getExtras().getString("bundle"));
                Utils.setIsFromnotification(SplashActivity.this, true);
            } else {
                Utils.setIsFromnotification(SplashActivity.this, false);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            Utils.setIsFromnotification(SplashActivity.this, false);
        }
        FirebaseMessaging.getInstance().subscribeToTopic("global");

        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            Utils.setAppVersion(SplashActivity.this, version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
                            try {
                                if (deepLink != null) {
                                    String str = new Gson().toJson(splitQuery(new URL(deepLink.toString())));
                                    Utils.setReferData(SplashActivity.this,str.toString());

                                }else {
                                    Utils.setReferData(SplashActivity.this,"");
                                }

                            } catch (UnsupportedEncodingException | MalformedURLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Utils.setReferData(SplashActivity.this,"");
                    }
                });

        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                AdvertisingIdClient.Info idInfo = null;
                try {
                    idInfo = AdvertisingIdClient.getAdvertisingIdInfo(getApplicationContext());
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String advertId = null;
                try {
                    advertId = idInfo.getId();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                return advertId;
            }

            @Override
            protected void onPostExecute(String advertId) {
                Utils.setAdID(SplashActivity.this, advertId);
            }
        };

        task.execute();

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        String token = task.getResult();
                        Utils.setFCMRegId(SplashActivity.this, token);
                    }
                });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                new HomeDataAsync(SplashActivity.this,"Spls");
            }
        }, 800);

        txtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkLocationPermission()) {
                    showHomeScreen();
                    showInterstialAd();
                }
                else
                {
                    Dexter.withContext(SplashActivity.this)
                            .withPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                            .withListener(new MultiplePermissionsListener() {
                                @Override
                                public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                                    if (multiplePermissionsReport.areAllPermissionsGranted())
                                        showHomeScreen();

                                    if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied())
                                        showSettingPermission();
                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                                    permissionToken.continuePermissionRequest();
                                }
                            }).check();
                }
            }
        });
    }

    private void showInterstialAd()
    {
        if ( interstitialAd.isReady() )
        {
            interstitialAd.showAd();
        }
    }

    public static Map<String, String> splitQuery(URL url) throws UnsupportedEncodingException {
        Map<String, String> query_pairs = new LinkedHashMap<String, String>();
        String query = url.getQuery();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }
        return query_pairs;
    }
    public String getCurrentDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat curFormater = new SimpleDateFormat("dd/MM/yyyy");
        return curFormater.format(c);
    }

    private void showHomeScreen() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    private void showSettingPermission() {
        Toast.makeText(SplashActivity.this, getString(R.string.permission_denied_msg), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        permissionSettingActivityResultLauncher.launch(intent);
    }


    private boolean checkLocationPermission()
    {
        int result = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        int result1 = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

}