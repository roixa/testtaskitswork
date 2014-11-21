package com.tomleto.roix.testtaskitswork;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;


public class MainActivity extends Activity implements View.OnTouchListener{

    private DrawView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drawView=new DrawView(this);
        setContentView(drawView);
        drawView.setOnTouchListener(this);

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int pointerIndex = motionEvent.getActionIndex();
        int pointerCount = motionEvent.getPointerCount();
        switch (motionEvent.getAction()){

            case MotionEvent.ACTION_MOVE:
                if(pointerCount==2) {
                    drawView.onTouch(motionEvent.getX(0), motionEvent.getY(0), 0);
                    drawView.onTouch(motionEvent.getX(1), motionEvent.getY(1), 1);
                }
                if(pointerCount==1) {
                    drawView.onTouch(motionEvent.getX(), motionEvent.getY(), 0);
                }

                break;
            case MotionEvent.ACTION_UP:
                drawView.onEndTouch();

                break;
        }


        return true;
    }
}
