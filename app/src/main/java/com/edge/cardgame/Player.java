package com.edge.cardgame;

import java.util.ArrayList;

/**
 * Created by Matt on 12/21/15.
 */
public class Player {
    String name;
    int roundScore=0;
    int totalScore=0;
    ArrayList<Card> cardsWon = new ArrayList<Card>();
    static int numberOfPlayers;
    int orderNumber;
    ArrayList<Card> hand = new ArrayList<Card>();
    ArrayList<Card> possibleMoves = new ArrayList<Card>();


    public Player() {
        this.name = "Player" + numberOfPlayers;
        numberOfPlayers++;
    }
    public Player(String name) {
        this.name = name;
        numberOfPlayers++;
    }
    public Player(String name, int orderNumber) {
        this.name = name;
        this.orderNumber=orderNumber;
        numberOfPlayers++;
    }

    public void giveCards(ArrayList<Card> trickCards) {
        for (Card c: trickCards) {
            cardsWon.add(c);
        }
    }
    public void dealCard(Card dealtCard) {
        //hand.add
    }

    public int calculateRoundScore() {
        int roundScore =0;
        for (Card card: this.cardsWon) {
            roundScore = roundScore+ card.getHeartsScore();
        }
        this.roundScore = roundScore;
        return roundScore;
    }
    public int getRoundScore() {
        return this.roundScore;
    }
    public void addRoundToTotalScore() {
        totalScore = totalScore +roundScore;
    }

    public int getTotalScore() {
        return this.totalScore;

    }

    public void setName(String name) {
        this.name = name;
    }
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setRoundScore(int roundScore) {
        this.roundScore = roundScore;
    }

    public void clearCardsWon() {
        cardsWon.clear();
    }


}
