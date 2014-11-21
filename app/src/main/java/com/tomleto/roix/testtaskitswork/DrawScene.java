package com.tomleto.roix.testtaskitswork;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

/**
 * Created by bsr on 19.11.14.
 */
public class DrawScene {
    private Ball ball;
    private Bite player1Bite;
    private Bite player2Bite;
    public int xSize;
    public int ySize;
    public int player1Goals;
    public int player2Goals;
    private boolean onTouched;



    public DrawScene(){

        player1Bite=new Bite();
        player2Bite=new Bite();
        player1Goals=0;
        player2Goals=0;
        onTouched=false;
    }


    public void onTouch(float x, float y,int pointerIndex){
        onTouched=true;
        if(pointerIndex==0)
            player1Bite.setPos(x,y);
        if(pointerIndex==1)
            player2Bite.setPos(x,y);
    }

    public void setSize(int x,int y) {
        this.xSize = x;
        this.ySize=y;
        ball=new Ball(x,y);

    }


    public void onEndTouch(){
        //playerBite.moveToStartedPosition();
        onTouched=false;
    }

    public void refresh(){

        boolean onTheWall=false;
        boolean isVertical=false;


        ball.checkToCollide(false,false);
        ball.checkToCollide(true,false);


        ball.move();

        if(get1Gates().contains(ball.getX(),ball.getY())){
            player1Goals++;
            ball=new Ball(xSize,ySize);
        }
        if(get2Gates().contains(ball.getX(),ball.getY())){
            player2Goals++;
            ball=new Ball(xSize,ySize);
        }

        if(!onTouched)return;
        if(ball.isCollised(player1Bite)) ball.onCollideWith(player1Bite);

        if(ball.isCollised(player1Bite)&&onTheWall) ball.pushBack(player1Bite,isVertical);

        if(ball.isCollised(player2Bite)) ball.onCollideWith(player2Bite);

        if(ball.isCollised(player2Bite)&&onTheWall) ball.pushBack(player2Bite,isVertical);


    }

    public RectF get1Gates(){
        float size=xSize>ySize?ySize:xSize;
        float add=size/3;
        float r=60;
        float a=xSize<ySize?add:0;
        float b=xSize<ySize?0:add;
        float c=xSize<ySize?2*add:r;
        float d=xSize<ySize?r:2*add;
        RectF rectF=new RectF(a,b,c,d);
        return rectF;
    }

    public RectF get2Gates(){
        float size=xSize>ySize?ySize:xSize;
        float add=size/3;
        float r=60;
        float a=xSize<ySize?add:xSize-r;
        float b=xSize<ySize?ySize-r:add;
        float c=xSize<ySize?2*add:xSize;
        float d=xSize<ySize?ySize:2*add;
        RectF rectF=new RectF(a,b,c,d);
        return rectF;

    }

    public Bite getPlayer1BiteCoord(){
        return player1Bite;
    }
    public Bite getPlayer2BiteCoord(){
        return player2Bite;
    }
    public Ball getBallCoord(){
        return ball;
    }
}


