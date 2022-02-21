package com.example.diy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Test extends View {
    private Paint mPaint;
    private Path path;
    public Test(Context context) {
        super(context);
        init();
    }
    public Test(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }
    public Test(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    private void init(){
        mPaint = new Paint();
        path = new Path();
        path.moveTo(50,50);
        path.lineTo(100,100);
        //mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        //init();
        canvas.translate(10,10);
        canvas.drawPath(path,mPaint);
    }
}
