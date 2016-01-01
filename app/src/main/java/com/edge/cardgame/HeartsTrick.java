package com.edge.cardgame;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Matt on 12/21/15.
 */
public class HeartsTrick {
    String TAG = "hepMe";

    ArrayList<Card> playedCards = new ArrayList<Card>();
    ArrayList<Player> players = new ArrayList<Player>();

    static Player playerThatWonLastTrick;

    int numberOfPlayers;

    int mainSuitValue;

    static int trickCount=0;

    String title;


    public HeartsTrick(ArrayList<Player> players) {
        this.players = players;
        this.numberOfPlayers = players.size();
        trickCount++;
        this.title = "Trick"+trickCount;
    }

    public Player whoGoesFirst() {
        if (!playerThatWonLastTrick.equals(null)) {
            return findPlayerWithTwoOfClubs();
        }
        else {
            return playerThatWonLastTrick;

        }
    }

    public Player findPlayerWithTwoOfClubs() {
        for (Player player: players) {
            for (Card card: player.hand) {
                if (card.title.matches("twoOfClubs")) {
                    return player;
                }
            }
        }
        return null;
    }



    public void playCard(Card card) {
        if (playedCards.size() ==0) {

            mainSuitValue = card.suitValue;
        }

        playedCards.add(card);

        if (playedCards.size() == numberOfPlayers) {
            endTrick();
        }
    }

    private void endTrick() {

        Card winningCard = findHighestCard();
        findOwnerAndStickItToThem(winningCard);


    }


    private Card findHighestCard() {

        ArrayList<Card> copyCards = (ArrayList<Card>) playedCards.clone();

        Card highest = copyCards.remove(0);

        while (copyCards.size()>0) {
            Card test = copyCards.remove(0);
            highest = isHigher(test,highest);
        }
        return highest;

    }

    private Card isHigher(Card test, Card currentHighest) {
        if (test.suitValue != mainSuitValue) {
            return currentHighest;
        }
        if (test.faceValue < currentHighest.faceValue) {
            return currentHighest;
        }
        else  {
            return test;
        }

    }

    private void findOwnerAndStickItToThem(Card winningCard) {

        for (Player p: players) {
            if (p.name == winningCard.owner) {
                p.giveCards(playedCards);
                playerThatWonLastTrick=p;
            }
        }
    }

    public static void resetTrickCount() {
        trickCount=0;
    }






}
