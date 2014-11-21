package com.tomleto.roix.testtaskitswork;

/**
 * Created by bsr on 19.11.14.
 */
public class Bite extends PhysObject{
    private float startedXPos;
    private float startedYPos;

    public Bite(){
       startedXPos=0;
       startedYPos=0;
       radius=50;
    }

    public void setStartedPosition(float x,float y){
        startedXPos=x;
        startedYPos=y;
    }

    public void moveToStartedPosition(){
        xPos=startedXPos;
        yPos=startedYPos;
    }
}
