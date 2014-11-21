package com.tomleto.roix.testtaskitswork;

/**
 * Created by bsr on 19.11.14.
 */
public class PhysObject {
    protected float xPos;
    protected float yPos;
    public float speedX;
    public float speedY;
    protected float mass;
    protected float radius;

    public void setPos(float x,float y){
        speedX=xPos-x;
        speedY=yPos-y;
        xPos=x;
        yPos=y;
    }

    public boolean isCollised(PhysObject o){
        float dist=distanceTo(o);
        if(dist<(o.getRadius()+radius))return true;
        return false;
    }

    public float getX() {
        return xPos;
    }

    public float getY() {
        return yPos;
    }
    public float getRadius(){
        return radius;
    }

    protected float distanceTo(PhysObject o){
        return (float)Math.pow((xPos-o.getX())*(xPos-o.getX())+(yPos-o.getY())*(yPos-o.getY()),0.5);
    }

    private float distanceTo(float x,float y){
        return (float)Math.pow((xPos-x)*(xPos-x)+(yPos-y)*(yPos-y),0.5);
    }
}
