package com.edge.cardgame;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Matt on 12/31/15.
 */
public class PlayerQueue {
    public String TAG = "hepMe";
    ArrayList<Player> players = new ArrayList<Player>();
    Player firstPlayer;
    ArrayList<Player> standardPlayerList = new ArrayList<Player>();


    public PlayerQueue (ArrayList<Player> players) {
        this.players=players;
    }

    public PlayerQueue(ArrayList<Player> players, Player firstPlayer) {
        this.players = players;
        this.firstPlayer=firstPlayer;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }
    public void setFirstPlayer(Player player) {
        this.firstPlayer= player;
    }


    public void sortByOrderNumber() {
        ArrayList<Player> orderedPlayerList = new ArrayList<Player>();

        for (int orderCount = 1; orderCount<players.size()+1;orderCount++) {
            for (Player player : players) {

                if (player.orderNumber == orderCount) {
                    orderedPlayerList.add(player);
                }
            }
        }

        players = orderedPlayerList;

    }

    public ArrayList<Player> reorderQueue(Player currentPlayer) {
        ArrayList<Player> reorderedQueue = new ArrayList<Player>();
        sortByOrderNumber();

        int splitIndex = players.indexOf(currentPlayer);
        int endIndex = players.size();

        reorderedQueue.addAll(players.subList(splitIndex,endIndex));
        reorderedQueue.addAll(players.subList(0,splitIndex));

        this.players = reorderedQueue;
        return reorderedQueue;
    }

    public Player getNextPlayer(Player currentPlayer) {
        reorderQueue(currentPlayer);
        return players.get(1);
    }

}
