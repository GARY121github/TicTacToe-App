package com.example.tictaktoe;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TicTacToeBoard extends View {

    private final int boardColour;
    private final int Xcolour;
    private final int Ocolour;
    private final int winningLineColour;

    private boolean winnigline = false;

    private final Paint paint = new Paint();

    private final GameLogic game;

    private int cellSize = getWidth()/3 ;


    public TicTacToeBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        game = new GameLogic();

        TypedArray a = context.getTheme().obtainStyledAttributes( attrs,R.styleable.TicTacToeBoard,
                0,0);

        try {
           boardColour =  a.getInteger(R.styleable.TicTacToeBoard_boardColour,0);
           Xcolour =  a.getInteger(R.styleable.TicTacToeBoard_Xcolour,0);
           Ocolour =  a.getInteger(R.styleable.TicTacToeBoard_Ocolour,0);
           winningLineColour = a.getInteger(R.styleable.TicTacToeBoard_winningLineColour,0);

        }
        finally {
            a.recycle();
        }
    }
    @Override
    public void onMeasure(int width, int height){
        super.onMeasure(width,height);
        int dimension = Math.min(getMeasuredWidth(),getMeasuredHeight());

        cellSize = dimension/3;
        setMeasuredDimension(dimension,dimension);
    }

    @Override
    protected void onDraw ( Canvas canvas ) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawGameBoard(canvas);
        drawMarkers(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();
         int action = event.getAction();

         if(action == MotionEvent.ACTION_DOWN){
             int row = (int) Math.ceil(y/cellSize);
             int col = (int) Math.ceil(x/cellSize);

             if(!winnigline){
                 if(game.updateGameBoard(row , col)){
                     invalidate();

                     if(game.winnerCheck()){
                         winnigline = true;
                         invalidate();

                     }
                     if(game.getPlayer() % 2 == 0){
                         game.setPlayer(game.getPlayer()-1);
                     }
                     else{
                         game.setPlayer(game.getPlayer()+1);
                     }
                 }
             }

             invalidate();
             return true;
         }

         return false;

    }
    private void drawGameBoard ( Canvas canvas ) {
        paint.setColor(boardColour);
        paint.setStrokeWidth(16);

        for(int c=1; c<3 ; c++){
            canvas.drawLine(cellSize*c ,0,cellSize*c, canvas.getWidth(), paint);

        }

        for(int r=1 ; r<3 ;r++){
            canvas.drawLine(0,cellSize*r,canvas.getWidth(),cellSize*r, paint);

        }
    }

    private void drawMarkers(Canvas canvas){

        for(int r = 0; r<3 ;r++){
            for(int c=0;c<3;c++) {
                if(game.getGameBoard()[r][c] != 0){
                    if(game.getGameBoard()[r][c] == 1){
                        drawX(canvas,r,c);
                    }
                    else{
                        drawO(canvas,r,c);
                    }
                }
            }
        }

    }

    private void drawX(Canvas canvas , int row , int col) {
        paint.setColor(Xcolour);
        canvas.drawLine((col + 1)*cellSize - cellSize*0.2f,
                row * cellSize + cellSize*0.2f,
                col * cellSize + cellSize*0.2f,
                (row + 1) * cellSize - cellSize*0.2f,
                paint);
        canvas.drawLine(col * cellSize + cellSize*0.2f,
                row * cellSize + cellSize*0.2f,
                (col + 1)*cellSize - cellSize*0.2f,
                (row + 1) * cellSize - cellSize*0.2f,
                paint);
    }

    private void drawO(Canvas canvas ,int row, int col ) {
        paint.setColor(Ocolour);
        canvas.drawOval(col*cellSize + cellSize*0.2f,
                row*cellSize + cellSize*0.2f,
                (col*cellSize + cellSize) - cellSize*0.2f,
                (row*cellSize +cellSize) - cellSize*0.2f,paint);

    }

    public void setUpGame(Button playAgain, Button home , TextView playerDisplay ,String [] names){
        game.setPlayAgainBTN(playAgain);
        game.setHomeBTN(home);
        game.setPlayerTurn(playerDisplay);
        game.setPlayerName(names); 
    }
    public void resetGame(){
        game.resetGame();
        winnigline = false;
    }

}
