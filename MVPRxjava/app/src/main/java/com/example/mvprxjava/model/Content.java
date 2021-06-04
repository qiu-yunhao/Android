package com.example.mvprxjava.model;


import com.example.mvprxjava.bean.Developer;
import com.example.mvprxjava.bean.USER;

import java.net.URL;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public interface Content {
    @GET("/repo")
    Observable<USER> getRepo(
            @Query("lang") String lang,
            @Query("since") String since
    );
    @GET("/developer")
    Call<Developer> getDevelopers(
            @Query("lang") String lang,
            @Query("since") String since
    );
    @GET
    Observable getImage(
            @Url String url
    );
}
