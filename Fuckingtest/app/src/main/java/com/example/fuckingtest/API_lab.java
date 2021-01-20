package com.example.fuckingtest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API_lab {

    @GET("repos/qiu-yunhao/restapi-lab/pulls")

    Call<ResponseBody> getJson();
}
