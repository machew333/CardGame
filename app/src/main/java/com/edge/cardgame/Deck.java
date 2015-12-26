package com.edge.cardgame;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Matt on 12/21/15.
 */
public class Deck {
    String TAG = "hepMe";


    String title;
    ArrayList<Card> cards = new ArrayList<Card>();
    int deckCount;
    int deckNumber;

    Player startPlayer;
    public Player getStartPlayer() {
        return startPlayer;
    }




    ArrayList<Player> players = new ArrayList<Player>();
    int playerCount;


    public Deck() {
        fillDeck();
    }

    public void shuffle() {
        Random rand = new Random();
        int originalDeckSize = cards.size();

        ArrayList<Card> temporaryCards = new ArrayList<Card>();

        if (originalDeckSize ==0) {
            fillDeck();
        }

        for (int i = 1;i<originalDeckSize+1;i++) {
            int deckSize = cards.size();

            int pick = rand.nextInt(deckSize);
            Card addingCard =this.cards.remove(pick);
            temporaryCards.add(addingCard);

        }

        this.cards = temporaryCards;

    }

    private void fillDeck() {
        for (int suitValue=1; suitValue<5;suitValue++) {
            for (int faceValue=2;faceValue<15;faceValue++) {
                this.cards.add(new Card(faceValue,suitValue));
            }
        }
    }

    public void dealOutAll() {
        playerCount = players.size();
        //In case you are playing with an odd number. You don't want people to have leftover cards. This takes out low diamonds.

        while (cards.size() <40 && cards.size() %playerCount !=0) {
            cards.remove(40);
        }
        Player currentPlayer;
        int count =0;

        //Run through all cards and deal out
        for (Card card: cards) {
            count++;
            int personNumber = (count%playerCount);

            if (personNumber == 0) {
                currentPlayer = players.get(0);
            }
            else if (personNumber == 1) {
                currentPlayer = players.get(1);
            }
            else if (personNumber == 2) {
                currentPlayer = players.get(2);
            }
            else if (personNumber ==3) {
                currentPlayer = players.get(3);
            }
            else if (personNumber ==4) {
                currentPlayer = players.get(4);
            }
            else currentPlayer = players.get(5);

            if (card.imageName.equals("C2")) {
                startPlayer = currentPlayer;
            }
            currentPlayer.hand.cards.add(card);
        }
    }


    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }


}
