package com.example.diy.codeRain;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//TODO 数组满屏后会出现ConcurrentModificationException的报错，待改正
//实现代码雨
public class CodeRain extends View {
    private Paint mPaint;
    private int mHeight;
    private int mWidth;
    private int textSize = 50;
    private static final String[] s = {"0","1","2","3","4","5","6","7","8","9"};
    private Random random;
    private List<Line> lines;

    public CodeRain(Context context){
        super(context);
        init();
    }

    public CodeRain(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CodeRain(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        random = new Random();
        Rect rect = new Rect();
        getWindowVisibleDisplayFrame(rect);
        mHeight = rect.height();
        mWidth = rect.width();

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GREEN);
        mPaint.setTextSize(textSize);
       // mPaint.setAntiAlias(true);

        int n = (int) (mWidth / (1.2*textSize));
        lines = new ArrayList<>();
        for(int i = 0 ; i < n ;i++){
            Line l = new Line((int) (i*textSize*1.2));
            lines.add(l);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 0 ;i < lines.size();i++) {
            drawLine(canvas, i);
            Line l = lines.get(i);
            if (l.thread == null) {
                l.thread = new RollThread(l);
                l.thread.start();
            }
        }
    }

    private void drawLine(Canvas canvas,int index){
        Line l = lines.get(index);
        for(Item i : l.line){
            canvas.drawText(i.text,i.x,i.y,mPaint);
        }

    }

    class Line{
        private int x;
        private List<Item> line;
        private RollThread thread;

        Line(int x){
            this.x = x;
            line = new ArrayList<>();
        }

        public void roll(){
            for(Item i : line){
                i.y += textSize*1.2;
                if(i.y > mHeight){
                    line.remove(i);
                }
            }
            Item item = new Item();
            item.x = x;
            item.y = (int) (textSize * 1.2);
            item.text = s[random.nextInt(s.length)];
            line.add(item);
        }
    }

    public class RollThread extends Thread{
        private Line l;

        RollThread(Line l){
            this.l = l;
        }
        @Override
        public void run() {
            super.run();
            while (true) {
                l.roll();
                postInvalidate();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    class Item {
        private int x;
        private int y;
        private String text;
    }
}


