package com.edge.cardgame;

import java.util.ArrayList;

/**
 * Created by Matt on 12/21/15.
 */
public class Hand {
    String owner;
    ArrayList<Card> cards = new ArrayList<Card>();

    public Hand(String owner) {
        this.owner = owner;
    }

    public void ownCards() {
        for (Card c: cards) {
            c.setOwner(owner);
        }
    }
}
