package com.example.mvprxjava.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import androidx.recyclerview.widget.RecyclerView;

import com.example.mvprxjava.bean.USER;
import com.example.mvprxjava.model.Content;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class User_Adapter extends RecyclerView.Adapter<ViewHolder_choose> {
    private Context context;
    private Bitmap bitmap;
    private List<USER.ItemsDTO> userDataList = new ArrayList<>();

    public User_Adapter(Context context, List<USER.ItemsDTO> list) {
        this.context = context;
        this.userDataList = list;
    }

    public ViewHolder_choose onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder_choose(inflater, parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder_choose holder, int position) {
        USER.ItemsDTO userDate = userDataList.get(position);
        holder.bind(userDate);
        updatePic(holder,userDate);
        /*try {
            upMap(holder,userDate);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/
        holder.hide();
    }


    public int getItemCount() {
        if (userDataList.size()== 0){
            return 1;
        }
        else {
            return userDataList.size();
        }
    }

    public void updatePic(ViewHolder_choose holder, USER.ItemsDTO uDto) {
        Log.d("TEST", "尝试更新图片");
        Handler handler = new Handler() {
            @SuppressLint("HandlerLeak")
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 0x8848) {
                    holder.imageView.setImageBitmap(bitmap);
                    Log.d("Main_Activity", "尝试更新图片");
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    List<String> uList = uDto.getAvatars();
                    url = new URL(uList.get(0));
                    Log.d("GET_TRY", url.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                InputStream inputStream = null;
                try {
                    inputStream = url.openStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bitmap = BitmapFactory.decodeStream(inputStream);
                handler.sendEmptyMessage(0x8848);

            }
        }).start();
    }

    public void upMap(ViewHolder_choose holder,USER.ItemsDTO uDto) throws MalformedURLException {
        List<String> uList = uDto.getAvatars();
        URL url = new URL(uList.get(0));
        Retrofit retrofit = new Retrofit.
                Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Content api = retrofit.create(Content.class);
        api.getImage(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map((Func1<ResponseBody, Bitmap>) responsebody -> {
                    InputStream inputStream = null;
                    try {
                        inputStream = responsebody.byteStream();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (inputStream != null) {
                        return BitmapFactory.decodeStream(inputStream);
                    }
                    return null;
                }).subscribe(new Subscriber<Bitmap>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Bitmap bitmap) {
                holder.imageView.setImageBitmap(bitmap);
            }
        });
    }
}
