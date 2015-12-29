package com.edge.cardgame;

import java.util.ArrayList;

/**
 * Created by Matt on 12/21/15.
 */
public class GameState {

    String title;
    String playerTurn;
    ArrayList<Player> playerStates;
    HeartsTrick currentTrick;
    int playerCount;
    int tricksPlayed;

    public GameState() {
        tricksPlayed=0;
    }

    public void setGameState(String title,String playerTurn, ArrayList<Player> playerStates, HeartsTrick currentTrick,int tricksPlayed) {
        this.title = title;
        this.playerTurn = playerTurn;
        this.playerStates = playerStates;
        this.currentTrick = currentTrick;
        this.playerCount = playerStates.size();
        this.tricksPlayed = tricksPlayed;
    }

}
