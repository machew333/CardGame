package com.edge.cardgame;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Matt on 12/21/15.
 */




public class Card {
    public String TAG = "hepMe";

    int faceValue;
    int suitValue;
    String title = "";
    String owner;
    String imageName="";


    Rect postion = new Rect();
    Region region = new Region();
    boolean isClickable;

    public void setIsClickable(boolean isClickable) {
        this.isClickable = isClickable;
    }

    //Creates title from suit and card value
    public Card(int faceValue, int suitValue) {
        this.faceValue=faceValue;
        this.suitValue=suitValue;

        title+= determineFace(faceValue);
        title+=determineSuit(suitValue);

    }


    private String determineSuit(int suitValue) {
        String suit="";

        if (suitValue ==1) {
            suit+="Clubs";
            imageName = "C"+imageName;
        }
        else if (suitValue==2) {
            suit+="Hearts";
            imageName = "H"+imageName;
        }
        else if (suitValue==3) {
            suit+="Spades";
            imageName="S" +imageName;
        }
        else if (suitValue==4) {
            suit+="Diamonds";
            imageName = "D" +imageName;
        }
        return suit;
    }

    private String determineFace(int faceValue) {
        String value="";

        if (faceValue==2) {
            value+="twoOf";
            imageName+="2";
        }
        else if (faceValue==3) {
            value+="threeOf";
            imageName+="3";
        }
        else if (faceValue==4) {
            value+="fourOf";
            imageName+="4";
        }
        else if (faceValue==5) {
            value+="fiveOf";
            imageName+="5";
        }
        else if (faceValue==6) {
            value+="sixOf";
            imageName+="6";
        }
        else if (faceValue==7) {
            value+="sevenOf";
            imageName+="7";
        }
        else if (faceValue==8) {
            value+="eightOf";
            imageName+="8";
        }
        else if (faceValue==9) {
            value+="nineOf";
            imageName+="9";
        }
        else if (faceValue==10) {
            value+="tenOf";
            imageName+="10";
        }
        else if (faceValue==11) {
            value+="jackOf";
            imageName+="J";
        }
        else if (faceValue==12) {
            value+="queenOf";
            imageName+="Q";
        }
        else if (faceValue==13) {
            value+="kingOf";
            imageName+="K";
        }
        else if (faceValue==14) {
            value+="aceOf";
            imageName+="A";
        }

        return value;

    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int Count() {
        int score=0;

        if (suitValue ==2) {
            score++;
        }
        else if (suitValue==3 && faceValue ==12) {
            score = score+13;
        }
        return score;
    }

    public Bitmap findBitmap(Map bitmapMap ) {
        Bitmap cardBitmap = (Bitmap) bitmapMap.get(imageName);
        return cardBitmap;
    }

    public void setPostion(Rect postion) {
        this.postion = postion;
        this.region.set(postion);
    }

    public int getHeartsScore() {
        if (this.suitValue ==2) {
            return 1;
        }
        else if (this.suitValue==3 && this.faceValue==12) {
            return 13;
        }
        else {
            return 0;
        }
    }
}

