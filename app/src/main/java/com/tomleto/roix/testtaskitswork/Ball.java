package com.tomleto.roix.testtaskitswork;

import android.util.Log;

/**
 * Created by bsr on 19.11.14.
 */
public class Ball extends PhysObject {

    private float wallX;
    private float wallY;


    public Ball(float xSize,float ySize){
        xPos=100;
        yPos=100;
        radius=30;
        speedX=1;
        speedY=1;
        wallX=xSize;
        wallY=ySize;
    }


    public void move(){
        xPos+=speedX*1.01;
        yPos+=speedY*1.01;
        speedX/=1.04;
        speedY/=1.04;

    }

    private void returnMove(){
        xPos-=speedX;
        yPos-=speedY;
    }

    public void checkToCollide(boolean isXPos,boolean started){
        boolean endedX=false;
        boolean endedY=false;
        if(xPos>=wallX||xPos<=0){
            xPos-=speedX;
            checkToCollide(true,true);
        }
        else endedX=true;


        if(yPos>=wallY||yPos<=0){
            yPos-=speedY;
            checkToCollide(false,true);
        }
        else endedY=true;
        if(!endedX||!endedY) return;
        if(!started) return;
        if(isXPos) speedX=-speedX;
        else speedY=-speedY;
        //move();

    }

    public void collideXAxis(float wallCoordH){
        //xPos-=speedX;
        speedX=-speedX;
        //move();
        float dx=wallCoordH-xPos-speedX;
        xPos-=wallCoordH==0?1*dx-radius:-1*dx+radius;
        //move()
    }

    public void collideYAxis(float wallCoordW){
        //yPos-=speedY;
        speedY=-speedY;
        //move();
        float dy=wallCoordW-yPos-speedY;
        yPos-=wallCoordW==0?1*dy:-1*dy;
        //move();
    }

    public void onCollideWith(PhysObject object){
        float dist=distanceTo(object);
        float r=radius+object.getRadius();
        float dx=(xPos-object.getX())*2*(dist)/(2*r-dist);
        float dy=(yPos-object.getY())*2*(dist-r)/(2*r-dist);
        speedX-=object.speedX/2;
        speedY-=object.speedY/2;
        //xPos-=dx;
        //yPos-=dy;
    }
    public void pushBack(PhysObject object,boolean isVertical){
        Log.d("TAG","push back");
        boolean ballIsLeftOfObject=xPos<object.getX()?true:false;
        boolean ballIsTopOfObject=yPos>object.getY()?true:false;
        if(!isVertical) {
            if (ballIsLeftOfObject) {
                xPos += 2 * object.getRadius();
            } else xPos -= 2 * object.getRadius();
        }else{
            if(ballIsTopOfObject) yPos-=2*object.getRadius();
            else yPos+=2*object.getRadius();
        }
    }


}
