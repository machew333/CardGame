package com.edge.cardgame;

import java.util.ArrayList;
import java.util.Collections;

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

    public ArrayList<Player> sortByRoundScore(ArrayList<Player> players) {
        //This uses player.getRoundScore() so that if the cards get cleared then it still works as expected.

        ArrayList<Player> copyList = (ArrayList<Player>) players.clone();
        ArrayList<Integer> scoreList = new ArrayList<Integer>();
        ArrayList<Player> sortedPlayerList = new ArrayList<Player>();


        for (Player player: players) {
            player.getRoundScore();
            scoreList.add(player.roundScore);

            //Test for shoot the moon.
            if (player.roundScore == 26) {
                for (Player player1: players) {
                    player1.setRoundScore(26);
                }
                player.setRoundScore(0);
            }

        }
        Collections.sort(scoreList);


        for (Integer score: scoreList) {
            for (Player player: copyList) {
                if (player.roundScore == score) {
                    sortedPlayerList.add(player);
                    copyList.remove(player);
                    break;
                }
            }
        }
        return sortedPlayerList;
    }

    public ArrayList<Player> sortByTotalScore(ArrayList<Player> players) {
        ArrayList<Player> copyList = (ArrayList<Player>) players.clone();
        ArrayList<Integer> scoreList = new ArrayList<Integer>();
        ArrayList<Player> sortedPlayerList = new ArrayList<Player>();
        players = calculateTotalScores(players);

        for (Player player: players) {
            scoreList.add(player.totalScore);
        }
        Collections.sort(scoreList);

        for (Integer score: scoreList) {
            for (Player player: copyList) {
                if (player.totalScore == score) {
                    sortedPlayerList.add(player);
                    copyList.remove(player);
                    break;
                }
            }
        }
        return sortedPlayerList;

    }

    public ArrayList<Player> clearPlayerWonCards(ArrayList<Player> players) {
        for (Player player: players) {
            player.clearCardsWon();
        }
        return players;
    }

    public ArrayList<Player> calculateRoundScores(ArrayList<Player> players) {
        for (Player player:players) {
            player.calculateRoundScore();
        }
        return players;
    }

    public ArrayList<Player> calculateTotalScores(ArrayList<Player> players) {
        //Clears cards won so calculating round score again results in 0.
        for (Player player: players) {
            player.addRoundToTotalScore();
        }
        players = clearPlayerWonCards(players);
        return players;
    }



}
