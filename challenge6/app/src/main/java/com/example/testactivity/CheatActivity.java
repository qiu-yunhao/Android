package com.example.testactivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import static android.widget.Toast.makeText;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = "com.example.testactivity.MainActivity.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN = "com.example.testactivity.MainActivity.answer_shown";
    private static final String KEY_index = "key";
    private static final String T = "CheatActivity";
    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private TextView mNumber;
    private Button mShowAnswerButton;
    private number[] number = new number[]
            {
                    new number(R.string.number_0),
                    new number(R.string.number_1),
                    new number(R.string.number_2),
                    new number(R.string.number_3),
            };
    private int n;
    private int x;


    public static Intent newIntent(Context packageContext, boolean answerIsTrue, int n) {
        Intent intent = new Intent(packageContext, CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        intent.putExtra("A", n);
        return intent;
    }

    /*解析result这一Intent类的数值*/
    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    public static int GETnum(Intent result) {
        return result.getIntExtra("A", 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        n = getIntent().getIntExtra("A", 0);
        x = 0;

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
        mNumber = (TextView) findViewById(R.id.show_number);
        upgo();


        if (savedInstanceState != null)
            n = savedInstanceState.getInt("WHAT", 0);


        /*showAnswer按钮部分*/
        mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n = n + 1;
                x = x + 1;
                {
                    if (x == 1)
                        if (n < 4)
                            upgo();
                        else
                            tu();
                }
                if (mAnswerIsTrue) {
                    mAnswerTextView.setText(R.string.true_button);
                } else {
                    mAnswerTextView.setText(R.string.false_button);
                }


                setAnswerShowResult(true);

                /*检查设备编译版本*/
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    /*动画特效*/
                    int cx = mShowAnswerButton.getWidth() / 2 * 3;
                    int cy = mShowAnswerButton.getHeight() / 2 * 3;
                    float ra = mShowAnswerButton.getWidth();
                    Animator anim = ViewAnimationUtils.createCircularReveal(mShowAnswerButton, cx, cy, ra, 0);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationCancel(Animator animation) {
                            super.onAnimationCancel(animation);
                            mShowAnswerButton.setVisibility(View.INVISIBLE);
                        }
                    });
                    anim.start();
                } else {
                    mShowAnswerButton.setVisibility(View.INVISIBLE);
                }
            }
        });

    }


    /*输入要传递的信息，并将其储存在Intent中*/
    private void setAnswerShowResult(Boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle out) {
        super.onSaveInstanceState(out);
        out.putInt("WHAT", n);
    }

    private void upgo() {
        int go = number[n].getNumber();
        mNumber.setText(go);
    }

    private void tu() {
        Toast f = makeText(this, R.string.wdnmd, Toast.LENGTH_SHORT);
        f.show();
    }


    protected void onStart() {
        super.onStart();
        /*实现点击cheat按钮后更变次数*/
        Log.d(T, "CHEAT onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(T, "CHEAT onResume() called");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(T, "CHEAT onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(T, "CHEAT onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(T, "CHEAT onDestroyed() called");
    }

    private int Get() {
        Intent e = getIntent();
        n = e.getIntExtra("A", 0);
        return n;
    }

}



