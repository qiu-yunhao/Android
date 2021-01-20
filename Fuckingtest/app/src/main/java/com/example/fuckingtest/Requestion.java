package com.example.fuckingtest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Requestion extends AppCompatActivity {

    private static final String TAG = "Requestion";
    private Button mButton;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton = (Button) findViewById(R.id.button1);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRequest();
            }
        });

        mTextView = (TextView) findViewById(R.id.text);
    }
    public void getRequest()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http:api.github.com").build();
        API_lab api = retrofit.create(API_lab.class);
        Call<ResponseBody> test = api.getJson();
        test.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG,"OnResponse---->"+response.code());
                if(response.code()== HttpURLConnection.HTTP_OK)
                {
                    try {
                        Log.d(TAG,"json--->"+response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}