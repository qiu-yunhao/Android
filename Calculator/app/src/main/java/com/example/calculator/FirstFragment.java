package com.example.calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static com.example.calculator.MainActivity.textView1;
import static com.example.calculator.MainActivity.textView2;

public class FirstFragment extends Fragment implements View.OnClickListener {

    private Boolean an = false;
    private StringBuilder stringBuilder = new StringBuilder();
    private StringBuilder stringBuilder2 = new StringBuilder();
    public static String string_1;
    public static String string_2;
    private TextView textView_0;
    private TextView textView_1;
    private TextView textView_2;
    private TextView textView_3;
    private TextView textView_4;
    private TextView textView_5;
    private TextView textView_6;
    private TextView textView_7;
    private TextView textView_8;
    private TextView textView_9;
    private TextView textView_jia;
    private TextView textView_jian;
    private TextView textView_cheng;
    private TextView textView_chu;
    private TextView textView_mod;
    private TextView textView_re;
    private TextView textView_dian;
    private TextView textView_kuohao;
    private TextView textView_tui;
    private TextView textView_dengyu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first, container, false);
        textView_re = (TextView) v.findViewById(R.id.fresh);
        textView_mod = (TextView)v.findViewById(R.id.mod);
        textView_tui = (TextView)v.findViewById(R.id.behind);
        textView_chu = (TextView)v.findViewById(R.id.chu);
        textView_jia = (TextView)v.findViewById(R.id.jia);
        textView_jian = (TextView)v.findViewById(R.id.jian);
        textView_cheng = (TextView)v.findViewById(R.id.cheng);
        textView_dengyu = (TextView)v.findViewById(R.id.dengyu);
        textView_kuohao = (TextView)v.findViewById(R.id.more);
        textView_dian = (TextView)v.findViewById(R.id.dian);
        textView_0 = (TextView)v.findViewById(R.id.key_0);
        textView_1 = (TextView)v.findViewById(R.id.key_1);
        textView_2 = (TextView)v.findViewById(R.id.key_2);
        textView_3 = (TextView)v.findViewById(R.id.key_3);
        textView_4 = (TextView)v.findViewById(R.id.key_4);
        textView_5 = (TextView)v.findViewById(R.id.key_5);
        textView_6 = (TextView)v.findViewById(R.id.key_6);
        textView_7 = (TextView)v.findViewById(R.id.key_7);
        textView_8 = (TextView)v.findViewById(R.id.key_8);
        textView_9 = (TextView)v.findViewById(R.id.key_9);

        textView_re.setOnClickListener(this);
        textView_jia.setOnClickListener(this);
        textView_jian.setOnClickListener(this);
        textView_cheng.setOnClickListener(this);
        textView_chu.setOnClickListener(this);
        textView_mod.setOnClickListener(this);
        textView_dian.setOnClickListener(this);
        textView_dengyu.setOnClickListener(this);
        textView_kuohao.setOnClickListener(this);
        textView_tui.setOnClickListener(this);
        textView_0.setOnClickListener(this);
        textView_1.setOnClickListener(this);
        textView_2.setOnClickListener(this);
        textView_3.setOnClickListener(this);
        textView_4.setOnClickListener(this);
        textView_5.setOnClickListener(this);
        textView_6.setOnClickListener(this);
        textView_7.setOnClickListener(this);
        textView_8.setOnClickListener(this);
        textView_9.setOnClickListener(this);
        return v;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dengyu:
                Refresh();
                stringBuilder.append("=");
                string_1 = stringBuilder.toString();
                StrChange strChange = new StrChange(string_1);
                double b = strChange.change(string_1);
                stringBuilder2.append('=');
                stringBuilder2.append(String.valueOf(b));
                textView1.setTextSize(32);
                textView2.setTextSize(40);
                textView2.setText(stringBuilder2.toString());
                break;
            case R.id.fresh:
                string_1 = null;
                string_2 = null;
                stringBuilder.delete(0,stringBuilder.length());
                Refresh();
                break;
            case R.id.mod:
                stringBuilder.append('%');
                Log.d("fragment","点击成功");
                Refresh();
                break;
            case R.id.jia:
                stringBuilder.append('+');
                Refresh();
                break;
            case R.id.jian:
                stringBuilder.append('-');
                Refresh();
                break;
            case R.id.cheng:
                stringBuilder.append('X');
                Refresh();
                break;
            case R.id.chu:
                stringBuilder.append('÷');
                Refresh();
                break;
            case R.id.key_0:
                stringBuilder.append('0');
                Refresh();
                break;
            case R.id.key_1:
                stringBuilder.append("1");
                Refresh();
                break;
            case R.id.key_2:
                stringBuilder.append('2');
                Refresh();
                break;
            case R.id.key_3:
                stringBuilder.append('3');
                Refresh();
                break;
            case R.id.key_4:
                stringBuilder.append('4');
                Refresh();
                break;
            case R.id.key_5:
                stringBuilder.append('5');
                Refresh();
                break;
            case R.id.key_6:
                stringBuilder.append('6');
                Refresh();
                break;
            case R.id.key_7:
                stringBuilder.append('7');
                Refresh();
                break;
            case R.id.key_8:
                stringBuilder.append('8');
                Refresh();
                break;
            case R.id.key_9:
                stringBuilder.append('9');
                Refresh();
                break;
            case R.id.dian:
                stringBuilder.append('.');
                Refresh();
                break;
            case R.id.behind:
                stringBuilder.delete(stringBuilder.length()-1,stringBuilder.length());
                Refresh();
                break;
            case R.id.more:
                if(an == false){
                    textView_kuohao.setText("(");
                    stringBuilder.append("(");
                    Refresh();
                }
                else{
                    textView_kuohao.setText(")");
                    stringBuilder.append(")");
                    Refresh();
                }
        }
    }

    public void Refresh(){
        textView1.setText(stringBuilder);

        textView2.setTextSize(32);
        textView1.setTextSize(40);
    }

}