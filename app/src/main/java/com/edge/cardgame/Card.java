package com.edge.cardgame;

/**
 * Created by Matt on 12/21/15.
 */




public class Card {
    int faceValue;
    int suitValue;
    String title = "";
    String owner;



    //Creates title from suit and card value
    public Card(int faceValue, int suitValue) {

        title+= determineFace(faceValue);
        title+=determineSuit(suitValue);
    }


    private String determineSuit(int suitValue) {
        String suit="";

        if (suitValue ==1) {
            suit+="Clubs";
        }
        else if (suitValue==2) {
            suit+="Hearts";
        }
        else if (suitValue==3) {
            suit+="Spades";
        }
        else if (suitValue==4) {
            suit+="Diamonds";
        }
        return suit;
    }

    private String determineFace(int faceValue) {
        String value="";

        if (faceValue==2) {
            value+="twoOf";
        }
        else if (faceValue==3) {
            value+="threeOf";
        }
        else if (faceValue==4) {
            value+="fourOf";
        }
        else if (faceValue==5) {
            value+="fiveOf";
        }
        else if (faceValue==6) {
            value+="sixOf";
        }
        else if (faceValue==7) {
            value+="sevenOf";
        }
        else if (faceValue==8) {
            value+="eightOf";
        }
        else if (faceValue==9) {
            value+="nineOf";
        }
        else if (faceValue==10) {
            value+="tenOf";
        }
        else if (faceValue==11) {
            value+="jackOf";
        }
        else if (faceValue==12) {
            value+="queenOf";
        }
        else if (faceValue==13) {
            value+="kingOf";
        }
        else if (faceValue==14) {
            value+="aceOf";
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

}

