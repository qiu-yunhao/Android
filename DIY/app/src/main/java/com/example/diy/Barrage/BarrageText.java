package com.example.diy.Barrage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Random;

public class BarrageText extends androidx.appcompat.widget.AppCompatTextView {
    private Paint mPaint;
    private int posX;
    private int posY;
    private final int textSize = 80;
    private int mHeight;
    private int mWidth;
    private RollThread thread = null;
    private Random random;
    private int v;
    public static int num = 0;
    private static final String[] s = {"白羽祺yyds","I Love 白羽祺","兴许会相逢","爱是反反复复","也是有始无终"};

    public BarrageText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BarrageText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    public BarrageText(Context context) {
        super(context);
        init();
    }


    public void init(){
        Rect rect = new Rect();
        getWindowVisibleDisplayFrame(rect);
        mWidth = rect.width();
        mHeight = rect.height();
        random = new Random();
        mPaint = new Paint();
        mPaint.setTextSize(random.nextInt(textSize) + 10);
        mPaint.setAlpha(random.nextInt(255));
        int color = Color.rgb(random.nextInt(256),random.nextInt(256),random.nextInt(256));
        mPaint.setColor(color);
        //mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.FILL);
        posX = 0;
        posY = random.nextInt(mHeight);
        v = random.nextInt(10) + 1;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(s[random.nextInt(s.length)],posX,posY,mPaint);
        if (thread == null){
            thread = new RollThread();
            thread.start();
            num++;
        }
    }

    public void roll(){
        posX += v;
    }

    class RollThread extends Thread {


        @Override
        public void run() {
            super.run();
            while (true) {
                roll();
                postInvalidate();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (Stopped()){
                    num--;
                    break;
                }

            }
        }

        public boolean Stopped(){
            return posX > mWidth;
        }
    }

}
