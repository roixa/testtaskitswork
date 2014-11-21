package com.tomleto.roix.testtaskitswork;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

/**
 * Created by bsr on 19.11.14.
 */
public class DrawView extends SurfaceView implements SurfaceHolder.Callback{

    private DrawThread drawThread;
    private DrawScene scene;
    private boolean onTouched=false;
    private boolean onDoubleTouch=false;

    public DrawView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        drawThread = new DrawThread(getHolder());
        scene=new DrawScene();
        scene.setSize(this.getWidth(),this.getHeight());
        drawThread.setRunning(true);
        drawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        drawThread.setRunning(false);
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    public void onTouch(float x, float y,int pointIndex){
        Log.d("DrawView","on touch"+x+y);
        scene.onTouch(x,y,pointIndex);
        onTouched=true;
        onDoubleTouch=pointIndex==1?true:false;
    }
    public void onEndTouch(){
        scene.onEndTouch();
        onTouched=false;

    }

    public class DrawThread extends Thread {

        private boolean running = false;
        private SurfaceHolder surfaceHolder;
        private Paint p;

        public DrawThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;
            p=new Paint();
        }

        public void setRunning(boolean running) {
            this.running = running;
        }

        @Override
        public void run() {
            Canvas canvas;
            while (running) {
                canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas(null);
                    if (canvas == null)
                        continue;

                    scene.refresh();
                    Bite b1=scene.getPlayer1BiteCoord();
                    Bite b2=scene.getPlayer2BiteCoord();
                    Ball ball=scene.getBallCoord();
                    canvas.drawColor(Color.WHITE);
                    p.setColor(Color.GREEN);
                    canvas.drawRect(scene.get1Gates(),p);
                    canvas.drawRect(scene.get2Gates(),p);

                    p.setColor(Color.RED);
                    canvas.drawCircle(ball.getX(),ball.getY(),ball.getRadius(),p);

                    //Toast.makeText(getContext(),"ff",Toast.LENGTH_LONG).show();
                    p.setTextSize(50);
                    canvas.drawText(scene.player1Goals+":" +scene.player2Goals,scene.xSize/3,scene.ySize/3,p);
                    if(onTouched) {
                        p.setColor(Color.BLACK);
                        canvas.drawCircle(b1.getX(), b1.getY(), b1.getRadius(), p);
                        if(onDoubleTouch)
                            canvas.drawCircle(b2.getX(), b2.getY(), b2.getRadius(), p);
                    }


                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }

}
