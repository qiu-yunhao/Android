package com.example.mvprxjava.view.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.mvprxjava.Base.BaseActivity;
import com.example.mvprxjava.R;
import com.example.mvprxjava.model.Show_append;
import com.example.mvprxjava.view.Fragment.Main_Fragment;
import com.example.mvprxjava.view.Fragment.StartFragment;
import com.example.mvprxjava.view.Fragment.Without_wlan_Fragment;

public class MainActivity extends BaseActivity implements View.OnClickListener, Show_append {
    private Main_Fragment fragment1;
    private Without_wlan_Fragment fragment2;
    private TextView repo;
    private TextView textView1;
    private TextView textView2;

    private int number;
    private String str1;
    private String str2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        INITStart();
        Log.d("MainActivity", "成功");
    }
    public void init(){
        repo = (TextView)findViewById(R.id.repo_2);
        textView1 = (TextView) findViewById(R.id.lang);
        textView2 = (TextView)findViewById(R.id.since);
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        number = 0;
        str1 = "java";
        str2 = "weekly";


    }
    public void INITStart(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = StartFragment.newInstance(str1,str2,number);
        fragmentTransaction.add(R.id.request_fragment,fragment);
        fragmentTransaction.commit();

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.lang:
                Show_choose_language();
                break;
            case R.id.since:
                Show_choose_frequent();
                break;
        }
    }

    public void Show_choose_language(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View v = View.inflate(this, R.layout.append_show_1, null);
        TextView textView = (TextView)v.findViewById(R.id.language_txt);
        EditText editText1 =(EditText) v.findViewById(R.id.choose_language);
        TextView textView_1 = (TextView)v.findViewById(R.id.language_quit);
        TextView textView_2 = (TextView)v.findViewById(R.id.language_ready);
        textView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        textView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1 = editText1.getText().toString();
                textView1.setText(str1.toString());
                dialog.dismiss();
            }
        });
        dialog.setView(v);
        dialog.show();
    }

    public void Show_choose_frequent(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View v = View.inflate(this, R.layout.append_show_2, null);
        TextView textView = (TextView)v.findViewById(R.id.time_txt);
        EditText editText1 =(EditText) v.findViewById(R.id.time_edit);
        TextView textView_1 = (TextView)v.findViewById(R.id.time_quit);
        TextView textView_2 = (TextView)v.findViewById(R.id.time_ready);
        textView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        textView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str2 = editText1.getText().toString();
                textView2.setText(str2.toString());
                dialog.dismiss();
            }
        });
        dialog.setView(v);
        dialog.show();
    }
}