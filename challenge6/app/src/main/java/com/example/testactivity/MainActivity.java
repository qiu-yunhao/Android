package com.example.testactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private TextView mNumber;
    private Question[] mQuestionBank = new Question[]
            {
                    new Question(R.string.question_america, true),
                    new Question(R.string.question_oceans, true),
                    new Question(R.string.question_mideast, false),
                    new Question(R.string.question_africa, false),
                    new Question(R.string.question_asia, true),
                    new Question(R.string.question_australia, true)
            };

    private number[] number = new number[]
            {
                    new number(R.string.number_0),
                    new number(R.string.number_1),
                    new number(R.string.number_2),
                    new number(R.string.number_3),
            };

    private int mCurrentIndex = 0;//控制数组下标来达到控制问题输出的目的
    private boolean mIsCheater;//作为判断是否查看答案的变量
    public  static int num = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {/*如果saveInstanceState被操作过，则不为null*/
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);//读取saveInstanceState中保存的mCurrentIndex的值
        }
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mNumber = (TextView) findViewById(R.id.show_number);
        upgo();

        /*True按钮部分*/
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });


        /*False按钮部分*/
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });


        /*Cheat按钮部分*/
        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (num < 3) {
                    boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

                    Intent intent = CheatActivity.newIntent(MainActivity.this, answerIsTrue,num);
                    startActivityForResult(intent, REQUEST_CODE_CHEAT);


                } else
                    tu();

            }
        });




        /*Next按钮部分*/
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();

            }
        });
        updateQuestion();
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*实现点击cheat按钮后更变次数*/
        Log.d(TAG, "onStart() called");
        if (mIsCheater)
        {
            num = num + 1;
            if (num < 4 && num > 0) {
                upgo();
                //Intent t = new Intent(MainActivity.this, CheatActivity.class);
                //t.putExtra("A", num);
                //startActivity(t);
            }
        }


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
    protected void onSaveInstanceState(Bundle savedInstanceState) {    /*设置一个叫onSaveInstanceState的方法，创造一份叫saveInstanceState的bundle来储存变量*/
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);//将秘钥设置为KET_INDEX,储存的变量是mCurrentIndex
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG,"输入mIscheat的布尔值 called");
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
        /*使question=相应的m数组【mc】的R。string的代号，貌似在打的时候后面有一个数字，就是那个数。盲猜是地址之类的*/
        mQuestionTextView.setText(question);
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

    private void upgo() {
        int go = number[num].getNumber();
        mNumber.setText(go);
    }


    private void tu() {
        Toast f = makeText(this, R.string.wdnmd, Toast.LENGTH_SHORT);
        f.show();
    }
}