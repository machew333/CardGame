package com.edge.cardgame;

import java.util.ArrayList;

/**
 * Created by Matt on 12/21/15.
 */
public class GameState {

    String title;
    Player currentPlayer;
    ArrayList<Player> playerStates;
    HeartsTrick currentTrick;
    int playerCount;
    int tricksPlayed;
    PlayerQueue playerQueue;

    public GameState() {
        tricksPlayed=0;
    }

    public String getTitle() {
        return title;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public ArrayList<Player> getPlayerStates() {
        return playerStates;
    }

    public HeartsTrick getCurrentTrick() {
        return currentTrick;
    }

    public int getPlayerCount() {
        return playerCount;
    }
    public PlayerQueue getPlayerQueue() {
        return playerQueue;
    }


    public void setGameState(String title,Player currentPlayer, ArrayList<Player> playerStates, HeartsTrick currentTrick, PlayerQueue playerQueue) {
        this.title = title;
        this.currentPlayer = currentPlayer;
        this.playerStates = playerStates;
        this.currentTrick = currentTrick;
        this.playerCount = playerStates.size();
    }


}
