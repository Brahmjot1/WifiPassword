package com.team.wifimanager.Others;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdFormat;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.gson.Gson;
import com.team.wifimanager.MainActivity;
import com.team.wifimanager.Model.CategoryModel;
import com.team.wifimanager.R;

import org.apache.commons.lang3.ArrayUtils;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;


public class Utils {
    public static String APPNAME = "Wifi Password";
    public static String msg_Service_Error = "Oops! This service is taking too much time to respond. please check your internet connection & try again.";
    public static String STATUS_ERROR = "0";
    public static String STATUS_SUCCESS = "1";
    UrlControl urlControl;
    private com.google.android.gms.ads.interstitial.InterstitialAd googleAds;
    private RewardedAd mRewardedAd;
    private MaxRewardedAd rewardedAd;

    public static String parseIpAddress(long ip) {
        try {
            byte[] byteAddress = BigInteger.valueOf(ip).toByteArray();
            ArrayUtils.reverse(byteAddress);
            return InetAddress.getByAddress(byteAddress).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void setNotificationData(Context activity, String NDATA) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(activity).edit();
        editor.putString("NDATA", NDATA);
        editor.commit();
    }


    public static void setReferData(Activity activity, String regId) {
        SharedPreferences pref = activity.getSharedPreferences("ReferData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("ReferData", regId);
        editor.apply();
    }


    public static String getReferData(Activity activity) {
        SharedPreferences pref = activity.getSharedPreferences("ReferData", Context.MODE_PRIVATE);
        return pref.getString("ReferData", "");
    }
    public static CategoryModel getNotificationData(Context activity) {
        try {
            String data = PreferenceManager.getDefaultSharedPreferences(activity).getString("NDATA", "");
            return new Gson().fromJson(data, CategoryModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setIsFromnotification(Activity activity, boolean value) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("IsFromnotification", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean("IsFromnotification", value);
        prefsEditor.commit();
    }
    public static boolean getIsFromnotification(Activity activity) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("IsFromnotification", Context.MODE_PRIVATE);
        if (sharedPreferences != null) {
            return sharedPreferences.getBoolean("IsFromnotification", false);
        }
        return false;
    }
    public static void setAsync(Context activity, String Key, String regId) {
        SharedPreferences pref = activity.getSharedPreferences(Key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Key, regId);
        editor.apply();
    }
    public static void setAppVersion(Context activity, String regId) {
        SharedPreferences pref = activity.getSharedPreferences("AppVersion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("AppVersion", regId);
        editor.apply();
    }

    public static void setAdID(Activity activity, String regId) {
        SharedPreferences pref = activity.getSharedPreferences("AdID", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("ReferData", regId);
        editor.apply();
    }


    public static String getAdID(Activity activity) {
        SharedPreferences pref = activity.getSharedPreferences("AdID", Context.MODE_PRIVATE);
        return pref.getString("AdID", "");
    }
    public static void setFCMRegId(Context activity, String regId) {
        SharedPreferences pref = activity.getSharedPreferences("DEVICETOKEN", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (regId != null) {
            editor.putString("FCMregId", regId);
        } else {
            editor.putString("FCMregId", "");
        }
        editor.apply();
    }
    public static String getFCMRegId(Context activity) {
        String regId = "";
        if (activity != null) {
            SharedPreferences pref = activity.getSharedPreferences("DEVICETOKEN", Context.MODE_PRIVATE);
            if (pref != null) {
                regId = pref.getString("FCMregId", "0");
            }
        }
        return regId;
    }
    public static String getAppVersion(Context activity) {
        String regId = "";
        if (activity != null) {
            SharedPreferences pref = activity.getSharedPreferences("AppVersion", Context.MODE_PRIVATE);
            if (pref != null) {
                regId = pref.getString("AppVersion", "");
            }
        }
        return regId;
    }

    public static boolean isStringNullOrEmpty(String text) {
        return (text == null || text.trim().equals("null") || text.trim()
                .length() <= 0);
    }

    public static void setTodayOpen(Activity activity, int spin) {
        SharedPreferences pref = activity.getSharedPreferences("todayOpen", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("todayOpen", spin);
        editor.apply();
    }

    public static int getTodayOpen(Activity activity) {
        SharedPreferences pref = activity.getSharedPreferences("todayOpen", Context.MODE_PRIVATE);
        return pref.getInt("todayOpen",0 );
    }

    public static void setTotalOpen(Activity activity, int spin) {
        SharedPreferences pref = activity.getSharedPreferences("TotalOpen", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("TotalOpen", spin);
        editor.apply();
    }

    public static int getTotalOpen(Activity activity) {
        SharedPreferences pref = activity.getSharedPreferences("TotalOpen", Context.MODE_PRIVATE);
        return pref.getInt("TotalOpen",1 );
    }

    public static void setDate(Activity activity, String CurrDate) {
        SharedPreferences pref = activity.getSharedPreferences("CurrDate", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("CurrDate", CurrDate);
        editor.apply();
    }

    public static String getDate(Activity activity) {
        SharedPreferences pref = activity.getSharedPreferences("CurrDate", Context.MODE_PRIVATE);
        return pref.getString("CurrDate", "0");
    }

    public static void NotifyFinish(final Activity activity, String title, String message) {
        if (activity != null) {
            final Dialog dialog1 = new Dialog(activity);
            dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog1.setContentView(R.layout.dialogue_notify);
            dialog1.setCancelable(false);
            Button btnOk = dialog1.findViewById(R.id.btnSubmit);
            TextView txtTitle = dialog1.findViewById(R.id.txtTitle);
            txtTitle.setText(title);
            TextView txtMessage = dialog1.findViewById(R.id.txtMessage);
            txtMessage.setText(message);
            btnOk.setOnClickListener(v -> {
                if (!activity.isFinishing()) {
                    dialog1.dismiss();
                    activity.finish();
                }
            });
            Display display = activity.getWindowManager().getDefaultDisplay();
            int width = display.getWidth();
            WindowManager.LayoutParams lp;
            lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog1.getWindow().getAttributes());
            lp.width = (int) (width - (width * 0.07));
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialog1.getWindow().setAttributes(lp);
            if (!activity.isFinishing()) {
                dialog1.show();
            }
        }
    }

    public static void Notify(final Activity activity, String title, String message) {
        if (activity != null) {
            final Dialog dialog1 = new Dialog(activity);
            dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog1.setContentView(R.layout.dialogue_notify);
            dialog1.setCancelable(false);
            Button btnOk = dialog1.findViewById(R.id.btnSubmit);
            TextView txtTitle = dialog1.findViewById(R.id.txtTitle);
            txtTitle.setText(title);
            TextView txtMessage = dialog1.findViewById(R.id.txtMessage);
            txtMessage.setText(message);
            btnOk.setOnClickListener(v -> {
                if (!activity.isFinishing()) {
                    dialog1.dismiss();
                }
            });
            Display display = activity.getWindowManager().getDefaultDisplay();
            int width = display.getWidth();
            WindowManager.LayoutParams lp;
            lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog1.getWindow().getAttributes());
            lp.width = (int) (width - (width * 0.07));
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialog1.getWindow().setAttributes(lp);
            if (!activity.isFinishing()) {
                dialog1.show();
            }
        }
    }

    public static void UpdateApp(final Activity activity, String isForupdate, final String appurl, String msg) {
        if (activity != null) {
            final Dialog dialog1 = new Dialog(activity);
            dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog1.setContentView(R.layout.dialogue_updateapp);
            Button btnUpdate = dialog1.findViewById(R.id.btnUpdate);
            Button btnCancel = dialog1.findViewById(R.id.btnCancel);
            TextView txtMessage = dialog1.findViewById(R.id.txtMessage);
            txtMessage.setText(msg);
            View viewDivider = dialog1.findViewById(R.id.viewDivider);
            if (isForupdate.equals("1")) {
                dialog1.setCancelable(false);
                btnUpdate.setVisibility(View.VISIBLE);
                viewDivider.setVisibility(View.GONE);
                btnCancel.setVisibility(View.GONE);
                btnUpdate.setBackgroundResource(R.drawable.btn_bg_diloage);
            } else {
                dialog1.setCancelable(true);
                btnUpdate.setVisibility(View.VISIBLE);
                viewDivider.setVisibility(View.VISIBLE);
                btnCancel.setVisibility(View.VISIBLE);
            }
            btnUpdate.setOnClickListener(v -> {
                if (!activity.isFinishing() && !isForupdate.equals("1")) {
                    dialog1.dismiss();
                }
                try {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(appurl));
                    activity.startActivity(browserIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(activity, "No application can handle this request."
                            + " Please install a webbrowser", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            });
            btnCancel.setOnClickListener(view -> {
                if (!activity.isFinishing()) {
                    dialog1.dismiss();
                }
            });
            Display display = activity.getWindowManager().getDefaultDisplay();
            int width = display.getWidth();
            int hight = display.getHeight();
            WindowManager.LayoutParams lp;
            lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog1.getWindow().getAttributes());
            lp.width = (int) (width - (width * 0.07));
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialog1.getWindow().setAttributes(lp);
            if (!activity.isFinishing()) {
                dialog1.show();
            }
        }
    }

    public void setCustomAds(Activity activity, CategoryModel categoryModel, RelativeLayout relView, LottieAnimationView ltBanner, ImageView ivBanner)
    {
        if (categoryModel!=null)
        {
            if (categoryModel.getImage().contains(".json")) {
                Log.e("getBgImage2--)", "" + categoryModel.getImage());
                ivBanner.setVisibility(View.GONE);
                ltBanner.setVisibility(View.VISIBLE);
                ltBanner.setAnimationFromUrl(categoryModel.getImage());
                ltBanner.setRepeatCount(LottieDrawable.INFINITE);
            } else {
                ivBanner.setVisibility(View.VISIBLE);
                ltBanner.setVisibility(View.GONE);
                Log.e("getBgImage1--)", "" + categoryModel.getImage());
                Glide.with(activity)
                        .load(categoryModel.getImage())
                        .listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                return false;
                            }
                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                                return false;
                            }
                        })
                        .into(ivBanner);


            }
        }


        relView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Redirect(activity,categoryModel);
            }
        });
    }
    public static void Redirect(Activity context, CategoryModel model) {
        Log.e("ClickEvent--)",""+model.getUrl()+"--)"+model.getScreenNo());
        if (model.getScreenNo() != null && model.getScreenNo().length() > 0) {
            if (model.getUrl()!=null)
            {
                Uri uri = Uri.parse(model.getUrl());
                try {

                    if (context!=null)
                    {
                        Intent i = new Intent("android.intent.action.MAIN");
                        i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                        i.addCategory("android.intent.category.LAUNCHER");
                        i.setData(uri);
                        context.startActivity(i);
                    }
                }
                catch(ActivityNotFoundException e) {
                    Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                    goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    try {
                        context.startActivity(goToMarket);
                    } catch (ActivityNotFoundException ee) {
                        ee.printStackTrace();
                    }
                }
            }
        }
    }

    public void showGoogleBanner(Activity activity, FrameLayout frameLayout)
    {
        String bannerId=MainActivity.gogoleBannerID.get(getGoogleBanner());
        AdView adView = new AdView(activity);
        adView.setAdUnitId(bannerId);
        frameLayout.removeAllViews();
        frameLayout.addView(adView);
        AdSize adSize = getAdSize(activity, frameLayout);
        adView.setAdSize(adSize);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    public void showLovinBanner(Activity activity,FrameLayout frameLayout)
    {
        String lovinID= MainActivity.lovinBannerID.get(getLovinBanner());

        MaxAdView adView = new MaxAdView(lovinID, MaxAdFormat.BANNER, activity);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int heightPx = 150;

        adView.setLayoutParams( new ViewGroup.LayoutParams( width, heightPx ) );
        frameLayout.addView(adView);
        adView.loadAd();
    }
    public static boolean checkInternetConnection(Activity context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null && cm.getActiveNetworkInfo() != null) {
                return cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected();
            }
        }
        return false;
    }


    private AdSize getAdSize(Activity activity, View adContainerView) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = outMetrics.density;

        float adWidthPixels = adContainerView.getWidth();

        if (adWidthPixels == 0) {
            adWidthPixels = outMetrics.widthPixels;
        }

        int adWidth = (int) (adWidthPixels / density);
        return com.google.android.gms.ads.AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);
    }

    public int getRandomNumber()
    {
        int random = new Random().nextInt((5 - 1) + 1) + 1;
        return  random;
    }
    public void showLovinInter(Activity activity,String adFaileUrl)
    {
        int num=getRandomNumber();
        urlControl=new UrlControl(activity);
        Log.e("Random--)",""+num);
        if (num==1)
        {
            showLovinReward(activity,adFaileUrl);
        }
        else
        {
            urlControl.showAdLoader();
            String lovinID=MainActivity.lovinIndstrialID.get(getLovinInter());
            MaxInterstitialAd interstitialAd = new MaxInterstitialAd( lovinID, activity );
            interstitialAd.setListener(new MaxAdViewAdListener() {
                @Override
                public void onAdExpanded(MaxAd ad) {

                }

                @Override
                public void onAdCollapsed(MaxAd ad) {

                }

                @Override
                public void onAdLoaded(MaxAd ad) {
                    urlControl.dismissADLoader();
                    Log.e("lovinInter--)", " "+ad.getAdUnitId());
                    if ( interstitialAd.isReady() )
                    {
                        interstitialAd.showAd();
                    }
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {

                }

                @Override
                public void onAdHidden(MaxAd ad) {

                }

                @Override
                public void onAdClicked(MaxAd ad) {

                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    Log.e("lovinInterError--)", " "+error);
                    urlControl.dismissADLoader();
                    showAdFailed(adFaileUrl,activity);
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                    Log.e("lovinInterError1--)", " "+error);
                }
            });
            interstitialAd.loadAd();
        }

    }
    public void showLovinReward(Activity activity,String adFailedURL)
    {
        urlControl.showAdLoader();
        String rewardID=MainActivity.lovinReawardID.get(getLovinReward());
        rewardedAd = MaxRewardedAd.getInstance( rewardID, activity );
        rewardedAd.setListener(new MaxRewardedAdListener() {
            @Override
            public void onRewardedVideoStarted(MaxAd ad) {

            }

            @Override
            public void onRewardedVideoCompleted(MaxAd ad) {

            }

            @Override
            public void onUserRewarded(MaxAd ad, MaxReward reward) {

            }

            @Override
            public void onAdLoaded(MaxAd ad) {
                urlControl.dismissADLoader();
                if (rewardedAd.isReady())
                {
                    rewardedAd.showAd();
                }
            }

            @Override
            public void onAdDisplayed(MaxAd ad) {

            }

            @Override
            public void onAdHidden(MaxAd ad) {

            }

            @Override
            public void onAdClicked(MaxAd ad) {

            }

            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                urlControl.dismissADLoader();
                Log.e("AdfailedUnity--)",""+adFailedURL);
                showAdFailed(adFailedURL,activity);
            }

            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                Log.e("AdfailedUnity1--)",""+adFailedURL);

            }
        });
        rewardedAd.loadAd();

    }
    public void showGoogleReward(Activity activity, String adFailUrl)
    {
        urlControl.showAdLoader();
        String rewardID=MainActivity.gogoleReawardID.get(getGoogleReward());
        AdRequest adRequest = new AdRequest.Builder().build();

        RewardedAd.load(activity, rewardID,
                adRequest, new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        Log.e("TAG", loadAdError.toString());
                        mRewardedAd = null;
                        urlControl.dismissADLoader();
                        showAdFailed(adFailUrl,activity);
                    }

                    @Override
                    public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                        mRewardedAd = rewardedAd;
                        urlControl.dismissADLoader();
                        if (mRewardedAd != null) {
                            mRewardedAd.show(activity, rewardItem -> {
                                int rewardAmount = rewardItem.getAmount();
                                String rewardType = rewardItem.getType();

                                Log.e("reWardType--)",""+rewardType+"--)"+rewardAmount);
                            });
                        }
                        Log.e("TAG", "Ad was loaded.");
                    }
                });
    }

    public void showGoogleInter(Activity activity,String adFailUrl)
    {
        urlControl=new UrlControl(activity);
        int num=getRandomNumber();

        Log.e("Random--)",""+num);
        if (num==1)
        {
            showGoogleReward(activity,adFailUrl);
        }
        else
        {
            urlControl.showAdLoader();
            String interID=MainActivity.gogoleIndstrialID.get(getGoogleInter());
            AdRequest adRequest = new AdRequest.Builder().build();
            com.google.android.gms.ads.interstitial.InterstitialAd.load(activity, interID, adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {

                    urlControl.dismissADLoader();
                    googleAds = interstitialAd;

                    if (googleAds != null) {
                        googleAds.show(activity);
                    } else {
                        Log.d("TAG", "The interstitial ad wasn't ready yet");
                    }

                    googleAds.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {

                            Log.d("TAG", "The ad was dismissed.");
                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            Log.d("TAG", "The ad failed to show.");
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            googleAds = null;
                            Log.d("TAG", "The ad was shown.");
                        }
                    });


                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    googleAds = null;
                    urlControl.dismissADLoader();
                    showAdFailed(adFailUrl,activity);
                }
            });
        }
    }

    public void showAdFailed(String url,Activity activity)
    {
        Log.e("adFaileurl--)",""+url);
        if (url!=null)
        {
            Uri uri = Uri.parse(url);
            try {
                Intent i = new Intent("android.intent.action.MAIN");
                i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                i.addCategory("android.intent.category.LAUNCHER");
                i.setData(uri);
                activity.startActivity(i);
            }
            catch(ActivityNotFoundException e) {
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    activity.startActivity(goToMarket);
                } catch (ActivityNotFoundException ee) {
                    ee.printStackTrace();
                }
            }

        }
    }
    public int getLovinBanner()
    {
        if (MainActivity.lovinBannerID.size()>1)
        {
            int random = new Random().nextInt(MainActivity.lovinBannerID.size());
            return random;
        }
        return 0;
    }
    public int getLovinInter()
    {
        if (MainActivity.lovinIndstrialID.size()>1)
        {
            int random = new Random().nextInt(MainActivity.lovinIndstrialID.size());
            return random;
        }
        return 0;
    }
    public int getLovinReward()
    {
        if (MainActivity.lovinReawardID.size()>1)
        {
            int random = new Random().nextInt(MainActivity.lovinReawardID.size() );
            return random;
        }
        return 0;
    }

    public int getGoogleBanner()
    {
        if (MainActivity.gogoleBannerID.size()>1)
        {
            int random = new Random().nextInt(MainActivity.gogoleBannerID.size());
            return random;
        }
        return 0;

    }
    public int getGoogleInter()
    {
        if (MainActivity.gogoleIndstrialID.size()>1)
        {
            int random = new Random().nextInt(MainActivity.gogoleIndstrialID.size());
            return random;
        }
        return 0;

    }
    public int getGoogleReward()
    {
        if (MainActivity.gogoleReawardID.size()>1)
        {
            int random = new Random().nextInt(MainActivity.gogoleReawardID.size());
            return random;
        }
        return 0;

    }

    public static String getAsync(Context activity, String Key) {
        String regId = "";
        if (activity != null) {
            SharedPreferences pref = activity.getSharedPreferences(Key, Context.MODE_PRIVATE);
            if (pref != null) {
                regId = pref.getString(Key, "");
            }
        }
        return regId;
    }
}
