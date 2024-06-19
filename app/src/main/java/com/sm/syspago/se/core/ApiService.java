package com.sm.syspago.se.core;

import com.sm.syspago.model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiService {
    @GET("1/me/customer")
    Call<ResponseModel> getUserInfo(@Header("Authorization") String authToken);
}
