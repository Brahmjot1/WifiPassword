package com.prowify.wifimanager.Async;

import android.app.Activity;
import android.provider.Settings;
import android.util.Log;

import com.google.gson.Gson;
import com.prowify.wifimanager.Interface.ApiInterface;
import com.prowify.wifimanager.MainActivity;
import com.prowify.wifimanager.Model.ResponseModel;
import com.prowify.wifimanager.Others.ApiClient;
import com.prowify.wifimanager.Others.Utils;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeDataAsync {
    private Activity activity;
    private JSONObject jObject;
    private String from;

    public HomeDataAsync(final Activity activity, String from) {
        this.activity = activity;
        this.from = from;
        try {
            jObject = new JSONObject();
            jObject.put("token", Utils.getFCMRegId(activity));
            jObject.put("device_id", Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.ANDROID_ID));
            jObject.put("deviceName", android.os.Build.MODEL);
            jObject.put("appVersion", Utils.getAppVersion(activity));
            jObject.put("adId", Utils.getAdID(activity));
            jObject.put("todayOpen", String.valueOf(Utils.getTodayOpen(activity)));
            jObject.put("totalOpen", String.valueOf(Utils.getTotalOpen(activity)));
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<ResponseModel> call = apiService.getHomeData(jObject.toString());
            call.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    onPostExecute(response.body(), activity);
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    if (!call.isCanceled()) {
                        Utils.NotifyFinish(activity, Utils.APPNAME, Utils.msg_Service_Error);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onPostExecute(ResponseModel responseModel, Activity activity) {
        try {
            Log.v("AAAAA",""+new Gson().toJson(responseModel));
            if (responseModel.getStatus().equals(Utils.STATUS_SUCCESS)) {
                Utils.setAsync(activity, "HomeData", new Gson().toJson(responseModel));
                if (from.equals("Spls")) {
                } else {
                    ((MainActivity) activity).setHomeData(activity, responseModel);
                }
            } else if (responseModel.getStatus().equals(Utils.STATUS_ERROR)) {
                Utils.Notify(activity, Utils.APPNAME, responseModel.getMessage());
            } else if (responseModel.getStatus().equals("2")) {
                Utils.Notify(activity, Utils.APPNAME, responseModel.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Utils.NotifyFinish(activity, Utils.APPNAME, e.getMessage());
        }
    }

}
