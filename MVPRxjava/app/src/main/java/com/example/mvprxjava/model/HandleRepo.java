package com.example.mvprxjava.model;

import android.util.Log;

import com.example.mvprxjava.bean.USER;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HandleRepo implements RepoListener {
    private List<USER.ItemsDTO> uList;
    private HandleRepo handleRepo;
    private RepoListener repoListener;

    public HandleRepo GetNewRepo() {
        if (handleRepo == null) {
            return new HandleRepo();
        } else {
            return handleRepo;
        }
    }


    public List<USER.ItemsDTO> getuList() {
        return uList;
    }

    @Override
    public void RespondSuccess(List<USER.ItemsDTO> list) {

    }

    @Override
    public void RespondOver() {

    }

    @Override
    public void RespondFailure() {

    }

    @Override
    public void GetRope(RepoListener repoListener, String lang, String since) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://trendings.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Content content = retrofit.create(Content.class);
        content.getRepo(lang, since)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<USER>() {
                    @Override
                    public void onNext(USER value) {
                        Log.d("TRY", "try");
                        uList = value.getItems();
                        repoListener.RespondSuccess(uList);
                    }

                    @Override
                    public void onCompleted() {
                        repoListener.RespondOver();

                    }

                    @Override
                    public void onError(Throwable e) {
                        repoListener.RespondOver();
                    }
                });
    }
}