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

    Hand hand = new Hand(name);

    public Player() {
        this.name = "Player" + numberOfPlayers;
        hand.ownCards();
        numberOfPlayers++;
    }
    public Player(String name) {
        this.name = name;
        hand.ownCards();
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


    public int countCards() {
        for (Card c: cardsWon) {
            totalScore+=c.Count();
        }
        return totalScore;
    }


}
