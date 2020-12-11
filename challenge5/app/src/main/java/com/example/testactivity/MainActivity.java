package com.example.testactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank = new Question[]
            {
                    new Question(R.string.question_america, true),
                    new Question(R.string.question_oceans, true),
                    new Question(R.string.question_mideast, false),
                    new Question(R.string.question_africa, false),
                    new Question(R.string.question_asia, true),
                    new Question(R.string.question_australia, true)
            };
    private int mCurrentIndex = 0;//控制数组下标来达到控制问题输出的目的
    private int x = 0;
    private int y = 0;
    private boolean mIsCheater;//作为判断是否查看答案的变量
    private int[] t = {0, 0, 0, 0, 0, 0, 0};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {/*如果saveInstanceState被操作过，则不为null*/
            mCurrentIndex = savedInstanceState.getInt("Q", mCurrentIndex);//读取saveInstanceState中保存的mCurrentIndex的值
            mIsCheater = savedInstanceState.getBoolean("W", mIsCheater);//读取saveInstanceState中保存的mIsCheat的值
            x = savedInstanceState.getInt("E", x);
            y = savedInstanceState.getInt("R", y);
            t=savedInstanceState.getIntArray(KEY_INDEX);
        }
        if (y > mCurrentIndex)
            mCurrentIndex = y;


        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);


        /*True按钮部分*/
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                x = x + 1;
                if (x < 2) {
                    checkAnswer(true);
                    y = y + 1;
                }
            }
        });


        /*False按钮部分*/
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = x + 1;
                if (x < 2) {
                    checkAnswer(false);
                    y = y + 1;
                }
            }
        });


        /*Cheat按钮部分*/
        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();//创造一个answerIsTrue的参数来储存相关问题的答案
                Intent intent = CheatActivity.newIntent(MainActivity.this, answerIsTrue);//创造一个叫intent的Intent类，
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });


        /*Next按钮部分*/
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsCheater)
                    t[mCurrentIndex]++;
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                if (t[mCurrentIndex] == 0)
                    mIsCheater = false;//重置mIsCheat中的作弊纪律
                else {
                    mIsCheater = true;
                }
                updateQuestion();
            }
        });
        updateQuestion();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {    /*重写一个叫onSaveInstanceState的方法，创造一份叫saveInstanceState的bundle来储存变量*/
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState");
        savedInstanceState.putInt("Q", mCurrentIndex);//将秘钥设置为KET_INDEX,储存的变量是mCurrentIndex
        savedInstanceState.putBoolean("W", mIsCheater);//将秘钥设置为KET_INDEX,储存的变量是mIsCheat
        savedInstanceState.putInt("E", x);
        savedInstanceState.putInt("R", y);
        savedInstanceState.putIntArray(KEY_INDEX,t);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroyed() called");
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
        x = 0;
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (mIsCheater) {//判断是否作弊
            messageResId = R.string.judgment_toast;
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;

            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast toast = makeText(this, messageResId, LENGTH_SHORT);
        toast.show();
    }
}