package com.example.testactivity;//问题：不点next数据会丢失

import androidx.appcompat.app.AppCompatActivity;

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
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
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

    private int mCurrentIndex = 0;
    private int round = 0;
    private int correct = 0;
    private int x = 0;
    private int y =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, mCurrentIndex);
            x=savedInstanceState.getInt(KEY_INDEX,x);
            correct=savedInstanceState.getInt(KEY_INDEX,correct);
            y=savedInstanceState.getInt(KEY_INDEX,y);
        }
        if(y>mCurrentIndex)
            mCurrentIndex=y;
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                x=x+1;
                if(x<2) {
                    checkAnswer(true);
                    y=y+1;
                }
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x=x+1;
                if(x<2) {
                    checkAnswer(false);
                    y=y+1;
                }
            }
        });
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;

                if (mCurrentIndex == 0) {
                    round++;
                    if (round != 0)
                        grade();
                }

                if (mCurrentIndex < mQuestionBank.length) {
                    updateQuestion();
                    x=0;
                }
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
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putInt(KEY_INDEX,x);
        savedInstanceState.putInt(KEY_INDEX,correct);
        savedInstanceState.putInt(KEY_INDEX,y);
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
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;

        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
            correct = correct + 1;

        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast toast = makeText(this, messageResId, LENGTH_SHORT);
        toast.show();
    }

    private void grade() {
        double g = correct * 100 / mQuestionBank.length;
        String x = "正确率是：";
        Toast T = makeText(this, x + g + "%", LENGTH_LONG);
        T.setGravity(Gravity.CENTER, 0, 0);
        T.show();
    }
}