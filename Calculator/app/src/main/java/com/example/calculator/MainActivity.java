package com.example.calculator;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Fragment firstFragment;
    public static TextView textView1;
    public static TextView textView2;
    private TextView textView_0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        textView1 = (TextView)findViewById(R.id.biaodashi);
        textView2 = (TextView)findViewById(R.id.result);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        firstFragment = new FirstFragment();
        fragmentTransaction.replace(R.id.fragment,firstFragment);
        fragmentTransaction.commit();
        textView1.setText("");
        textView2.setText("");
    }

}