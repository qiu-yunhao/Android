package com.example.mvprxjava.model;


import com.example.mvprxjava.bean.Developer;
import com.example.mvprxjava.bean.USER;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface Content {
    @GET("/repo")
    Call<USER> getRepo(
            @Query("lang") String lang,
            @Query("since") String since
    );
    @GET("/developer")
    Call<Developer> getDevelopers(
            @Query("lang") String lang,
            @Query("since") String since
    );
}
