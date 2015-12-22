package com.edge.cardgame;

import java.util.ArrayList;

/**
 * Created by Matt on 12/21/15.
 */
public class HeartsTrick {

    ArrayList<Card> playedCards = new ArrayList<Card>();
    ArrayList<Player> players = new ArrayList<Player>();

    int numberOfPlayers;

    int mainSuitValue;


    public HeartsTrick(ArrayList<Player> players) {
        this.players = players;
        this.numberOfPlayers = players.size();
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
        Card highest = playedCards.remove(0);

        while (playedCards.size()>0) {
            Card test = playedCards.remove(0);
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
            }
        }
    }






}
