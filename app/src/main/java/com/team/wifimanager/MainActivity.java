package com.team.wifimanager;

import static com.team.wifimanager.Others.Utils.isStringNullOrEmpty;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.team.wifimanager.Activity.WifiAnalyserActivity;
import com.team.wifimanager.Activity.WifiListActivity;
import com.team.wifimanager.Activity.WifiPasswordActivity;
import com.team.wifimanager.Async.DownloadImageShareAsync;
import com.team.wifimanager.Model.CategoryModel;
import com.team.wifimanager.Model.ResponseModel;
import com.team.wifimanager.Others.Speed;
import com.team.wifimanager.Others.Utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private TextView txtWifiName, txtSpeed;
    private LinearLayout lAvlWifi, lWifiPass, lWifiAnalyser, lDeviceName, lShareApp, lMoreApps, lPrivacyPolicy;
    private long mLastRxBytes = 0;
    private long mLastTxBytes = 0;
    private long mLastTime = 0;
    Speed mSpeed;
    public static String cwifiName = "Unknown";
    RelativeLayout relPopupTop, relPopupBtm, relPopupFlot;
    LottieAnimationView ivLottieViewTop, ivLottieViewBtm, ivLottieViewFlot;
    ImageView imgBannerBtm, imgBannerTop, imgBannerFlot;
    private WebView webNote;
    FrameLayout frameBannerTop, frameBannerBtm;
    Utils utility;
    Dialog dialogExit;
    public static ArrayList<String> lovinBannerID = new ArrayList<>();
    public static ArrayList<String> lovinIndstrialID = new ArrayList<>();
    public static ArrayList<String> lovinReawardID = new ArrayList<>();
    public static ArrayList<String> gogoleBannerID = new ArrayList<>();
    public static ArrayList<String> gogoleIndstrialID = new ArrayList<>();
    public static ArrayList<String> gogoleReawardID = new ArrayList<>();
    String shareImag, shareMsg, failedUrl, isInterstial,privacyPolicy;
    public static ArrayList<CategoryModel> mMoreApp = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        ResponseModel responseMain = new Gson().fromJson(Utils.getAsync(MainActivity.this, "HomeData"), ResponseModel.class);
        setHomeData(MainActivity.this, responseMain);

        lMoreApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MoreAppsActivity.class));
            }
        });


    }

    public void setHomeData(Activity activity, ResponseModel responseMain) {

        if (responseMain != null) {
            if (responseMain.getLovin_bannerID() != null) {
                lovinBannerID = responseMain.getLovin_bannerID();
            }
            if (responseMain.getLovin_indstrialID() != null) {
                lovinIndstrialID = responseMain.getLovin_indstrialID();
            }
            if (responseMain.getLovin_reawardID() != null) {
                lovinReawardID = responseMain.getLovin_reawardID();
            }
            if (responseMain.getGoogole_bannerID() != null) {
                gogoleBannerID = responseMain.getGoogole_bannerID();
            }
            if (responseMain.getGoogole_indstrialID() != null) {
                gogoleIndstrialID = responseMain.getGoogole_indstrialID();
            }
            if (responseMain.getGoogole_reawardID() != null) {
                gogoleReawardID = responseMain.getGoogole_reawardID();
            }

            if (!isStringNullOrEmpty(responseMain.getHomeNote())) {
                webNote.setVisibility(View.VISIBLE);
                webNote.loadDataWithBaseURL(null, responseMain.getHomeNote(), "text/html", "UTF-8", null);
            }
            if (responseMain.getHomeDiloage() != null) {
                ShowHomePopup(MainActivity.this, responseMain.getHomeDiloage());
            } else {
                if (responseMain.getIndstrialAds() != null) {
                    if (responseMain.getIndstrialAds().matches("2")) {
                        utility.showLovinInter(MainActivity.this, responseMain.getAdFailUrl());
                    } else if (responseMain.getIndstrialAds().matches("3")) {
                        utility.showGoogleInter(MainActivity.this, responseMain.getAdFailUrl());
                    }
                }
            }

            if (responseMain.getAdFailUrl()!=null)
            {
                failedUrl = responseMain.getAdFailUrl();
            }
            if (responseMain.getIndstrialAds()!=null)
            {
                isInterstial = responseMain.getIndstrialAds();
            }
            if (responseMain.getPrivacyPolicy()!=null)
            {
                privacyPolicy=responseMain.getPrivacyPolicy();
            }

            if (responseMain.getAppVersion() != null) {
                try {
                    PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                    String version = pInfo.versionName;
                    if (!responseMain.getAppVersion().equals(version)) {
                        Utils.UpdateApp(MainActivity.this, responseMain.getIsForupdate(), responseMain.getAppUrl(), responseMain.getUpdateMessage());
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }

            try {
                if (Utils.getIsFromnotification(MainActivity.this)) {
                    Utils.setIsFromnotification(MainActivity.this, false);
                    Utils.Redirect(MainActivity.this, Utils.getNotificationData(MainActivity.this));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (responseMain.getExitDialoge() != null) {
                loadHomeExit(MainActivity.this, responseMain.getExitDialoge());
            } else {
                loadHomeExit(MainActivity.this, null);
            }

            if (responseMain.getTopAds() != null) {
                if (responseMain.getTopAds().getType() != null) {
                    if (responseMain.getTopAds().getType().matches("1")) {
                        frameBannerTop.setVisibility(View.GONE);
                        utility.setCustomAds(MainActivity.this, responseMain.getTopAds(), relPopupTop, ivLottieViewTop, imgBannerTop);
                    } else if (responseMain.getTopAds().getType().matches("2")) {
                        frameBannerTop.setVisibility(View.VISIBLE);
                        utility.showLovinBanner(MainActivity.this, frameBannerTop);
                    } else if (responseMain.getTopAds().getType().matches("3")) {
                        frameBannerTop.setVisibility(View.VISIBLE);
                        utility.showGoogleBanner(MainActivity.this, frameBannerTop);
                    }
                }
            }

            if (responseMain.getButtomeAds() != null) {
                if (responseMain.getButtomeAds().getType() != null) {
                    if (responseMain.getButtomeAds().getType().matches("1")) {
                        frameBannerBtm.setVisibility(View.GONE);
                        utility.setCustomAds(MainActivity.this, responseMain.getButtomeAds(), relPopupBtm, ivLottieViewBtm, imgBannerBtm);
                    } else if (responseMain.getButtomeAds().getType().matches("2")) {
                        frameBannerBtm.setVisibility(View.VISIBLE);
                        utility.showLovinBanner(MainActivity.this, frameBannerBtm);
                    } else if (responseMain.getButtomeAds().getType().matches("3")) {
                        frameBannerBtm.setVisibility(View.VISIBLE);
                        utility.showGoogleBanner(MainActivity.this, frameBannerBtm);
                    }
                }
            }
//
            if (responseMain.getMoreApps() != null) {
                lMoreApps.setVisibility(View.VISIBLE);
                mMoreApp = responseMain.getMoreApps();
            } else {
                lMoreApps.setVisibility(View.GONE);
            }


            if (responseMain.getFlotingAds() != null) {
                utility.setCustomAds(MainActivity.this, responseMain.getFlotingAds(), relPopupFlot, ivLottieViewFlot, imgBannerFlot);
            }
        }

        lShareApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (responseMain.getShareImage() != null && !responseMain.getShareImage().isEmpty()) {

                    shareImag = responseMain.getShareImage();
                    shareMsg = responseMain.getShareMessage();
                    if (Build.VERSION.SDK_INT >= 23) {
                        int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 74);
                        } else {
                            shareImageData1(MainActivity.this, responseMain.getShareImage(), "", responseMain.getShareMessage());
                        }
                    } else {
                        shareImageData1(MainActivity.this, responseMain.getShareImage(), "", responseMain.getShareMessage());
                    }
                } else {
                    shareImageData1(MainActivity.this, "", "", responseMain.getShareMessage());
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 74) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                shareImageData1(MainActivity.this, shareImag, "", shareMsg);
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void shareImageData1(Activity activity, String img, String pkg, String msg) {
        Intent share;
        if (img.trim().length() > 0) {
            File dir = new File(String.valueOf(Environment.getExternalStoragePublicDirectory((Environment.DIRECTORY_PICTURES) + File.separator)));
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String[] str = img.trim().split("/");
            String extension = "";
            if (str[str.length - 1].contains(".")) {
                extension = str[str.length - 1].substring(str[str.length - 1].lastIndexOf("."));
            }
            if (extension.equals(".png") || extension.equals(".jpg") || extension.equals(".gif")) {
                extension = "";
            } else {
                extension = ".png";
            }
            File file = new File(dir, str[str.length - 1] + extension);
            if (file.exists()) {
                try {
                    share = new Intent(Intent.ACTION_SEND);
                    Uri uri = null;
                    if (Build.VERSION.SDK_INT >= 24) {
                        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        uri = FileProvider.getUriForFile(activity.getApplicationContext(), activity.getPackageName(), file);
                    } else {
                        uri = Uri.fromFile(file);
                    }
                    share.setType("image/*");
                    if (img.contains(".gif")) {
                        share.setType("image/gif");
                    } else {
                        share.setType("image/*");
                    }
                    share.putExtra(Intent.EXTRA_STREAM, uri);
                    share.putExtra(Intent.EXTRA_SUBJECT, Utils.APPNAME);
                    share.putExtra(Intent.EXTRA_TEXT, msg);
                    activity.startActivity(Intent.createChooser(share, "Share"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (Utils.checkInternetConnection(activity)) {
                new DownloadImageShareAsync(activity, file, img, "", msg).execute();
            }
        } else {
            try {
                share = new Intent(Intent.ACTION_SEND);
                share.putExtra(Intent.EXTRA_SUBJECT, "");
                share.putExtra(Intent.EXTRA_TEXT, msg);
                share.setType("text/plain");
                activity.startActivity(Intent.createChooser(share, "Share"));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void ShowHomePopup(Activity activity, CategoryModel popData) {
        if (activity != null) {
            final Dialog dialog1 = new Dialog(activity);
            dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog1.getWindow().setBackgroundDrawableResource(R.color.black);
            dialog1.setContentView(R.layout.dialogue_home_popup);
            dialog1.setCancelable(false);
            Button btnOk = dialog1.findViewById(R.id.btnYesExit);
            TextView txtTitle = dialog1.findViewById(R.id.txtTitle);
            Button btnCancel = dialog1.findViewById(R.id.btnNoExit);
            LottieAnimationView ivLottieView = dialog1.findViewById(R.id.ivLottieView);
            ProgressBar probrBanner = dialog1.findViewById(R.id.probrBanner);
            RelativeLayout relPopup = dialog1.findViewById(R.id.relPopup);
            ImageView imgBanner = dialog1.findViewById(R.id.imgBanner);

            if (popData != null) {
                txtTitle.setText(popData.getTitle());
                TextView txtMessage = dialog1.findViewById(R.id.txtMessage);
                txtMessage.setText(popData.getDescription());
                if (!isStringNullOrEmpty(popData.getIsForce()) && popData.getIsForce().equals("1")) {
                    btnCancel.setVisibility(View.GONE);
                } else {
                    btnCancel.setVisibility(View.VISIBLE);
                }

                if (!isStringNullOrEmpty(popData.getBtnName())) {
                    btnOk.setText(popData.getBtnName());
                }
                if (!isStringNullOrEmpty(popData.getImage())) {
                    if (popData.getImage().contains(".json")) {
                        probrBanner.setVisibility(View.GONE);
                        imgBanner.setVisibility(View.GONE);
                        ivLottieView.setVisibility(View.VISIBLE);
                        ivLottieView.setAnimationFromUrl(popData.getImage());
                        ivLottieView.setRepeatCount(LottieDrawable.INFINITE);
                    } else if (popData.getImage().contains(".gif")) {
                        imgBanner.setVisibility(View.VISIBLE);
                        probrBanner.setVisibility(View.GONE);
                        ivLottieView.setVisibility(View.GONE);
                        Glide.with(activity)
                                .load(popData.getImage())
                                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                                .into(imgBanner);
                    } else {
                        probrBanner.setVisibility(View.VISIBLE);
                        imgBanner.setVisibility(View.VISIBLE);
                        ivLottieView.setVisibility(View.GONE);

                        Glide.with(activity)
                                .load(popData.getImage())
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                                        probrBanner.setVisibility(View.GONE);
                                        return false;
                                    }
                                })
                                .into(imgBanner);
                    }

                } else {
                    imgBanner.setVisibility(View.GONE);
                    probrBanner.setVisibility(View.GONE);
                }
            }

            btnCancel.setOnClickListener(v -> {
                if (!activity.isFinishing()) {
                    dialog1.dismiss();
                }
            });

            relPopup.setOnClickListener(v -> Utils.Redirect(activity, popData));
            btnOk.setOnClickListener(view -> {
                if (!activity.isFinishing()) {
                    dialog1.dismiss();
                }
                Utils.Redirect(activity, popData);
            });

            Display display = activity.getWindowManager().getDefaultDisplay();
            int width = display.getWidth();
            WindowManager.LayoutParams lp;
            lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog1.getWindow().getAttributes());
            lp.width = (int) (width);
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            dialog1.getWindow().setAttributes(lp);
            if (!activity.isFinishing() && !dialog1.isShowing()) {
                dialog1.show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (dialogExit != null) {
            if (!dialogExit.isShowing()) {
                dialogExit.show();
            }
        }
    }

    public void loadHomeExit(Activity activity, CategoryModel popData) {
        if (activity != null) {
            dialogExit = new Dialog(activity);
            dialogExit.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogExit.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogExit.setContentView(R.layout.dialogue_home_exit);
            dialogExit.setCancelable(false);
            Button btnYesExit = dialogExit.findViewById(R.id.btnYesExit);
            LinearLayout lWatch = dialogExit.findViewById(R.id.lWatch);
            TextView txtWatch = dialogExit.findViewById(R.id.txtWatch);
            TextView txtTitle = dialogExit.findViewById(R.id.txtTitle);
            TextView btnNoExit = dialogExit.findViewById(R.id.btnNoExit);
            LinearLayout lDataExit = dialogExit.findViewById(R.id.lDataExit);
            LottieAnimationView ivLottieView = dialogExit.findViewById(R.id.ivLottieView);
            ProgressBar probrBanner = dialogExit.findViewById(R.id.probrBanner);
            RelativeLayout relPopup = dialogExit.findViewById(R.id.relPopup);
            ImageView imgBanner = dialogExit.findViewById(R.id.imgBanner);

            if (popData != null) {
                lDataExit.setVisibility(View.VISIBLE);
                txtTitle.setText(popData.getTitle());
                TextView txtMessage = dialogExit.findViewById(R.id.txtMessage);
                txtMessage.setText(popData.getDescription());
                if (!isStringNullOrEmpty(popData.getBtnName())) {
                    txtWatch.setText(popData.getBtnName());
                }

                if (!isStringNullOrEmpty(popData.getBtnColor())) {
                    Drawable mDrawable = ContextCompat.getDrawable(MainActivity.this, R.drawable.bg_button_gradiant);
                    mDrawable.setColorFilter(new PorterDuffColorFilter(Color.parseColor(popData.getBtnColor()), PorterDuff.Mode.SRC_IN));
                    lWatch.setBackground(mDrawable);
                }
                if (!isStringNullOrEmpty(popData.getImage())) {
                    if (popData.getImage().contains("json")) {
                        probrBanner.setVisibility(View.GONE);
                        imgBanner.setVisibility(View.GONE);
                        ivLottieView.setVisibility(View.VISIBLE);
                        ivLottieView.setAnimationFromUrl(popData.getImage());
                        ivLottieView.setRepeatCount(LottieDrawable.INFINITE);
                    } else if (popData.getImage().contains(".gif")) {
                        imgBanner.setVisibility(View.VISIBLE);
                        probrBanner.setVisibility(View.GONE);
                        ivLottieView.setVisibility(View.GONE);
                        Glide.with(activity)
                                .load(popData.getImage())
                                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                                .into(imgBanner);
                    } else {
                        probrBanner.setVisibility(View.VISIBLE);
                        imgBanner.setVisibility(View.VISIBLE);
                        ivLottieView.setVisibility(View.GONE);

                        Glide.with(activity)
                                .load(popData.getImage())
                                .listener(new RequestListener<Drawable>() {
                                    @Override
                                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                        return false;
                                    }

                                    @Override
                                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                                        probrBanner.setVisibility(View.GONE);
                                        return false;
                                    }
                                })
                                .into(imgBanner);
                    }

                } else {

                    imgBanner.setVisibility(View.GONE);
                    probrBanner.setVisibility(View.GONE);
                }

                relPopup.setOnClickListener(v -> Utils.Redirect(activity, popData));

                lWatch.setOnClickListener(v -> {
                    if (!activity.isFinishing()) {
                        dialogExit.dismiss();
                    }
                    Utils.Redirect(activity, popData);
                });
            } else {
                lDataExit.setVisibility(View.GONE);
            }

            btnNoExit.setOnClickListener(v -> {
                if (!activity.isFinishing()) {
                    dialogExit.dismiss();
                }

            });

            btnYesExit.setOnClickListener(view -> {
                if (!activity.isFinishing()) {
                    dialogExit.dismiss();
                }
                finish();
            });

            Display display = activity.getWindowManager().getDefaultDisplay();
            int width = display.getWidth() - 18;
            WindowManager.LayoutParams lp;
            lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialogExit.getWindow().getAttributes());
            lp.width = (int) (width);
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            dialogExit.getWindow().setAttributes(lp);
        }
    }

    private void initView() {
        mSpeed = new Speed(this);
        utility = new Utils();
        txtWifiName = findViewById(R.id.txtWifiName);
        txtSpeed = findViewById(R.id.txtSpeed);
        lAvlWifi = findViewById(R.id.lAvlWifi);
        lWifiPass = findViewById(R.id.lWifiPass);
        lWifiAnalyser = findViewById(R.id.lWifiAnalyser);
        lDeviceName = findViewById(R.id.lDeviceName);
        lShareApp = findViewById(R.id.lShareApp);
        lMoreApps = findViewById(R.id.lMoreApps);
        lPrivacyPolicy = findViewById(R.id.lPrivacyPolicy);
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

        getWifiData();
        lAvlWifi.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, WifiListActivity.class)));

        lWifiPass.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, WifiPasswordActivity.class).putExtra("from", "direct")));

        lWifiAnalyser.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, WifiAnalyserActivity.class)));

        lDeviceName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deviceinfoDialog();
            }
        });

        lPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (privacyPolicy!=null)
                {
                    Uri uri = Uri.parse(privacyPolicy);
                    try {

                        Intent i = new Intent("android.intent.action.MAIN");
                        i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                        i.addCategory("android.intent.category.LAUNCHER");
                        i.setData(uri);
                        startActivity(i);

                    } catch (ActivityNotFoundException e) {
                        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                        try {
                            startActivity(goToMarket);
                        } catch (ActivityNotFoundException ee) {
                            ee.printStackTrace();
                        }
                    }
                }

            }

        });
    }

    public void getWifiData() {
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (networkInfo.isConnected()) {
            Observable.fromCallable(() -> {
                        downloadData();
                        return true;
                    }).subscribeOn(Schedulers.io())
                    .subscribe((result) -> {
                        runOnUiThread(() -> {

                        });
                    });
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            List<ScanResult> networkList = wifiManager.getScanResults();
            String currentSSID = wifiInfo.getSSID();
            currentSSID = currentSSID.replace("\"", "");
            String type = getString(R.string.Danger);
            if (networkList != null) {
                for (ScanResult network : networkList) {
                    if (currentSSID.equals(network.SSID)) {
                        //get capabilities of current connection
                        String capabilities = network.capabilities;
                        if (capabilities.contains(getString(R.string.WPA2)) || capabilities.contains(getString(R.string.WPA))
                                || capabilities.contains(getString(R.string.WEP))) {
                            type = getString(R.string.Safety);
                        } else
                            type = getString(R.string.Danger);
                        break;
                    }
                }
            }
            String wifiName = wifiInfo.getSSID();

            if (wifiName != null)
                wifiName = wifiName.replace("\"", "");

            txtWifiName.setText(wifiName);
            cwifiName = wifiName;
        }

    }

    public void deviceinfoDialog() {
        Dialog dialog = new Dialog(MainActivity.this, R.style.WideDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_phone_details);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);

        ImageView ivClosed = dialog.findViewById(R.id.ivClosed);
        TextView txtModel = dialog.findViewById(R.id.txtModel);
        TextView txtBrand = dialog.findViewById(R.id.txtBrand);
        TextView txtSDK = dialog.findViewById(R.id.txtSDK);
        TextView txtResolution = dialog.findViewById(R.id.txtResolution);

        txtModel.setText(android.os.Build.MODEL);
        txtBrand.setText(Build.BRAND);
        txtSDK.setText(String.valueOf(Build.VERSION.RELEASE));
        txtResolution.setText(getScreenResolution(MainActivity.this));
        ivClosed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        if (dialog != null) {
            dialog.show();
        }
    }

    private String getScreenResolution(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        return width + "*" + height;
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
                    runOnUiThread(() -> {
                    });
                    isTextAdd = true;
                }
            }
            input.close();
        } catch (Exception e) {
            Log.e("Exception--)", "" + e.getMessage());
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
            txtSpeed.setText(speedToShow.speedValue + " " + speedToShow.speedUnit);
        });

    }
}