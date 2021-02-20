package com.vhddev.task1;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "http://aryu.co.in/tracking/";

    @GET("viewreport")
    Call<JsonObject> getData();

}
