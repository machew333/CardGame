package com.edge.cardgame;

import java.util.ArrayList;

/**
 * Created by Matt on 12/21/15.
 */
public class GameState {

    String playerTurn;
    ArrayList<Player> playerStates;
    HeartsTrick currentTrick;

    public void setGameState(String playerTurn, ArrayList<Player> playerStates, HeartsTrick currentTrick) {
        this.playerTurn = playerTurn;
        this.playerStates = playerStates;
        this.currentTrick = currentTrick;
    }
}
