package com.prowify.wifimanager.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.gson.Gson;
import com.prowify.wifimanager.Model.ResponseModel;
import com.prowify.wifimanager.Others.Utils;
import com.prowify.wifimanager.R;

import java.util.Random;

public class WifiPasswordActivity extends AppCompatActivity {

    ImageView ivBack,ivGenerate,ivCopy;
    TextView txtRandomValue,txtWifiSID,txtMacAddress,txtSecurity,txtFrequency;
    LinearLayout cardIPSetting,cardHotspot,lOtherDetail;
    private String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";
    String which,sid,mac,security,frequency;
    RelativeLayout relPopupTop, relPopupBtm, relPopupFlot;
    LottieAnimationView ivLottieViewTop, ivLottieViewBtm, ivLottieViewFlot;
    ImageView imgBannerBtm, imgBannerTop, imgBannerFlot;
    private WebView webNote;
    FrameLayout frameBannerTop, frameBannerBtm;
    Utils utility;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_password);
        Intent intent=getIntent();
        which=intent.getStringExtra("from");
        init();

        if (which.matches("direct"))
        {
            lOtherDetail.setVisibility(View.GONE);
        }
        else
        {
            lOtherDetail.setVisibility(View.VISIBLE);
            sid=intent.getStringExtra("sid");
            mac=intent.getStringExtra("mac");
            security=intent.getStringExtra("security");
            frequency=intent.getStringExtra("frequency");

            txtWifiSID.setText(sid);
            txtMacAddress.setText(mac);
            txtSecurity.setText(security);
            txtFrequency.setText(frequency);
        }

        ivBack.setOnClickListener(v -> finish());
        cardHotspot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
                final Intent intent = new Intent(Intent.ACTION_MAIN, null);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                final ComponentName cn = new ComponentName("com.android.settings", "com.android.settings.TetherSettings");
                intent.setComponent(cn);
                startActivity(intent);

            }
        });

        cardIPSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(android.provider.Settings.ACTION_WIFI_IP_SETTINGS));

            }
        });

        ivCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=txtRandomValue.getText().toString();
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copied Text", text);
                clipboard.setPrimaryClip(clip);
            }
        });

        ivGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRandomString();
            }
        });
    }

    private void getRandomString()
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(10);
        for(int i=0;i<10;++i)
        {
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        }
        txtRandomValue.setText(sb.toString());
    }
    public void init()
    {
        cardHotspot=findViewById(R.id.cardHotspot);
        txtRandomValue=findViewById(R.id.txtRandomValue);
        cardIPSetting=findViewById(R.id.cardIPSetting);
        lOtherDetail=findViewById(R.id.lOtherDetail);
        ivBack=findViewById(R.id.ivBack);
        ivGenerate=findViewById(R.id.ivGenerate);
        ivCopy=findViewById(R.id.ivCopy);
        txtWifiSID=findViewById(R.id.txtWifiSID);
        txtMacAddress=findViewById(R.id.txtMacAddress);
        txtSecurity=findViewById(R.id.txtSecurity);
        txtFrequency=findViewById(R.id.txtFrequency);
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

        getRandomString();

        ResponseModel responseMain = new Gson().fromJson(Utils.getAsync(WifiPasswordActivity.this, "HomeData"), ResponseModel.class);
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
                        utility.setCustomAds(WifiPasswordActivity.this, responseMain.getTopAds(), relPopupTop, ivLottieViewTop, imgBannerTop);
                    } else if (responseMain.getTopAds().getType().matches("2")) {
                        frameBannerTop.setVisibility(View.VISIBLE);
                        utility.showLovinBanner(WifiPasswordActivity.this, frameBannerTop);
                    } else if (responseMain.getTopAds().getType().matches("3")) {
                        frameBannerTop.setVisibility(View.VISIBLE);
                        utility.showGoogleBanner(WifiPasswordActivity.this, frameBannerTop);
                    }
                }
            }

            if (responseMain.getButtomeAds() != null) {
                if (responseMain.getButtomeAds().getType() != null) {
                    if (responseMain.getButtomeAds().getType().matches("1")) {
                        frameBannerBtm.setVisibility(View.GONE);
                        utility.setCustomAds(WifiPasswordActivity.this, responseMain.getButtomeAds(), relPopupBtm, ivLottieViewBtm, imgBannerBtm);
                    } else if (responseMain.getButtomeAds().getType().matches("2")) {
                        frameBannerBtm.setVisibility(View.VISIBLE);
                        utility.showLovinBanner(WifiPasswordActivity.this, frameBannerBtm);
                    } else if (responseMain.getButtomeAds().getType().matches("3")) {
                        frameBannerBtm.setVisibility(View.VISIBLE);
                        utility.showGoogleBanner(WifiPasswordActivity.this, frameBannerBtm);
                    }
                }
            }

            if (responseMain.getIndstrialAds() != null) {

                if (responseMain.getIndstrialAds().matches("2"))
                {
                    utility.showLovinInter(WifiPasswordActivity.this, responseMain.getAdFailUrl());
                }
                else if (responseMain.getIndstrialAds().matches("3"))
                {
                    utility.showGoogleInter(WifiPasswordActivity.this, responseMain.getAdFailUrl());
                }
            }

            if (responseMain.getFlotingAds() != null) {
                utility.setCustomAds(WifiPasswordActivity.this, responseMain.getFlotingAds(), relPopupFlot, ivLottieViewFlot, imgBannerFlot);
            }
        }

    }

}