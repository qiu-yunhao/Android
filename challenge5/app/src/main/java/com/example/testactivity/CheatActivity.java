package com.example.testactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = "com.example.testactivity.MainActivity.answer_is_true";//
    private static final String EXTRA_ANSWER_SHOWN = "com.example.testactivity.MainActivity.answer_shown";//
    private static final String KEY_INDEX = "index";
    private static final String T = "CheatActivity";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswerButton;
    private Bundle savedInstanceState;
    private int x = 0;//记录是否打开过‘显示答案’的按钮

    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {//设置answerIsTrue的boolean变量
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return intent;//调用这个方法得到intent函数
    }


    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);//加载activity_cheat的布局

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);//选择intent实例，调用getBoolean方法
        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

        if (savedInstanceState != null) {
            x = savedInstanceState.getInt("X",x);
            mAnswerIsTrue = savedInstanceState.getBoolean(KEY_INDEX, false);
            g();
            System.out.println(x);
        }

        if (x != 0){
            setAnswerShowResult(true);
        }


        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = 1;
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShowResult(true);
            }
        });
    }


    private void setAnswerShowResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(T, "onSavedInstanceState");
        savedInstanceState.putInt("X", x);
        savedInstanceState.putBoolean(KEY_INDEX, mAnswerIsTrue);
    }

    private void g() {
        Toast a = makeText(this, "1", Toast.LENGTH_SHORT);
        a.show();
    }
}