package com.edge.cardgame;

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


    public ArrayList<Card> getPossibleMoves(Player currentPlayer,ArrayList<Card> playedCards, HeartsTrick currentTrick, boolean heartsIsBroken) {
        ArrayList<Card> possibleMoves = new ArrayList<Card>();


        if (playedCards.isEmpty()) {

            if (HeartsTrick.trickCount ==1) {

                for (Card card: currentPlayer.hand) {

                    if (card.title.equals("twoOfClubs")) {
                        possibleMoves.clear();
                        possibleMoves.add(card);
                        break;
                    }

                }

            }
            else {
                for (Card c: currentPlayer.hand) {

                    if (HeartsTrick.trickCount==1) {
                        //Can't play value cards
                        if (c.getHeartsScore() ==0) {
                            //Log.d(TAG,"card = "+card.title);
                            possibleMoves.add(c);
                        }
                    }
                    else {
                        if (c.suitValue ==2) {
                            if (heartsIsBroken) {
                                possibleMoves.add(c);
                            }
                        }
                        else {
                            possibleMoves.add(c);
                        }
                    }



                }
            }
        }
        else {

            for (Card card: currentPlayer.hand) {
                if (card.suitValue == currentTrick.mainSuitValue ) {
                    possibleMoves.add(card);
                }
            }
        }

        if (possibleMoves.isEmpty()) {
            possibleMoves = currentPlayer.hand;
        }
        currentPlayer.possibleMoves = possibleMoves;
        return possibleMoves;
    }






}
