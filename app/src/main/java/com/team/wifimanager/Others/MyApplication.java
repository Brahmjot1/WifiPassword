package com.team.wifimanager.Others;

import android.app.Application;
import android.content.Context;

import com.onesignal.OneSignal;


public class MyApplication extends Application {

    private static final String ONESIGNAL_APP_ID = "d91d68e6-858b-4518-9ede-a14af60e30b0";
    public static final String TAG = MyApplication.class
            .getSimpleName();

    private static MyApplication mInstance;
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        context = getApplicationContext();
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);
        OneSignal.promptForPushNotifications();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

}
