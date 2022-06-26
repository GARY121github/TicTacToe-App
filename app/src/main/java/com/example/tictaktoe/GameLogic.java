package com.example.tictaktoe;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameLogic {
    private int[][] gameboard;

    private String[] playerName = {"Player 1","Player 2"};



    private Button playAgainBTN;
    private Button homeBTN;
    private TextView playerTurn;

    private int player = 1;

    GameLogic(){
        gameboard = new int[3][3];
        for(int r = 0; r<3 ;r++){
            for(int c=0;c<3;c++){
                gameboard[r][c] = 0;
            }
        }
    }

    public boolean updateGameBoard(int row , int col){
        if(gameboard[row-1][col-1] == 0){
             gameboard[row-1][col-1] = player;

             if(player == 1) playerTurn.setText(playerName[1] + "'s Turn");
             else playerTurn.setText(playerName[0] + "'s Turn");

             return true;
        }
        else{
            return false;
        }
    }

    public boolean winnerCheck(){
        boolean isWinner = false;

        for(int r = 0; r<3; r++)
        {
           if( gameboard[r][0] == gameboard[r][1] && gameboard[r][0] == gameboard[r][2] && gameboard[r][0] != 0)
               isWinner = true;
        }
        for(int c = 0; c<3; c++)
        {
            if( gameboard[0][c] == gameboard[1][c] && gameboard[0][c] == gameboard[2][c] && gameboard[0][c] != 0)
                isWinner = true;
        }

        if( gameboard[0][0] == gameboard[1][1] && gameboard[0][0] == gameboard[2][2] && gameboard[0][0] != 0)
            isWinner = true;

        if( gameboard[2][0] == gameboard[1][1] && gameboard[2][0] == gameboard[0][2] && gameboard[2][0] != 0)
            isWinner = true;

        int boardFilled=0;
        for ( int r = 0 ; r < 3 ; r++ ) {
            for (int c = 0; c < 3; c++) {
                if (gameboard[r][c] != 0) {
                    boardFilled += 1;
                }
            }
        }

        if(isWinner){
            playAgainBTN.setVisibility(View.VISIBLE);
            homeBTN.setVisibility(View.VISIBLE);
            playerTurn.setText((playerName[player-1] + " WON!!!"));
            return true;
        }
        else if(boardFilled == 9){
            playAgainBTN.setVisibility(View.VISIBLE);
            homeBTN.setVisibility(View.VISIBLE);
            playerTurn.setText("Tie Game!!!");
            return true;
        }
        else {
            return false;
        }
    }

    public void resetGame(){
        gameboard = new int[3][3];
        for(int r = 0; r<3 ;r++){
            for(int c=0;c<3;c++){
                gameboard[r][c] = 0;
            }
        }
        player = 1;
        playAgainBTN.setVisibility(View.GONE);
        homeBTN.setVisibility(View.GONE);

        playerTurn.setText((playerName[0] + "'s Turn"));
    }

    public void setPlayerName(String[] playerName) {
        this.playerName = playerName;
    }

    public void setPlayAgainBTN(Button playAgainBTN) {
        this.playAgainBTN = playAgainBTN;
    }

    public void setHomeBTN(Button homeBTN) {
        this.homeBTN = homeBTN;
    }

    public void setPlayerTurn(TextView playerTurn) {
        this.playerTurn = playerTurn;
    }

    public int[][] getGameBoard(){
        return gameboard;
    }

    public void setPlayer(int player){
        this.player = player;
    }

    public int getPlayer(){
        return player;
    }
}
