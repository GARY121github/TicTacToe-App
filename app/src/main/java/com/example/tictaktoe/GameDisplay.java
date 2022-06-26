package com.example.tictaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameDisplay extends AppCompatActivity {

    private TicTacToeBoard ticTacToeBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_display);

        ticTacToeBoard = findViewById(R.id.ticTacToeBoard);

        Button playAgainBTN = findViewById(R.id.play_Again);
        Button homeBTN = findViewById(R.id.go_home);

        playAgainBTN.setVisibility(View.GONE);
        homeBTN.setVisibility(View.GONE);


        TextView playersTurn = findViewById(R.id.player_display);
        String[] playerNames = getIntent().getStringArrayExtra("PLAYER NAMES");

        if(playerNames != null)
        {
            playersTurn.setText((playerNames[0]) + "'s Turn");
        }

        ticTacToeBoard.setUpGame(playAgainBTN,homeBTN,playersTurn,playerNames);
    }

    public void playAgain(View view){
        ticTacToeBoard.resetGame();
        ticTacToeBoard.invalidate();



    }

    public void home(View view){
        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);
        finish();
    }
}