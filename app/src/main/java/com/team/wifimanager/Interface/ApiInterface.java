package com.team.wifimanager.Interface;


import com.team.wifimanager.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiInterface {

    @FormUrlEncoded
    @POST("HomeData")
    Call<ResponseModel> getHomeData(@Field("details") String device_id);

}
