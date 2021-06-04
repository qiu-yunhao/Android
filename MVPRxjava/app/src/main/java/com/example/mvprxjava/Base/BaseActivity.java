package com.example.mvprxjava.Base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvprxjava.R;
import com.example.mvprxjava.presenter.IPresent;

public abstract class BaseActivity extends AppCompatActivity  {

    protected IPresent iPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    public abstract void init();

}