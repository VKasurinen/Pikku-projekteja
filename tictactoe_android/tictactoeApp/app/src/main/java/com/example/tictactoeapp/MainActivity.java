package com.example.tictactoeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView playerOne, playerTwo;
    private final Button [] buttons = new Button[9];
    private  Button reset;

    private  int playerOneScore, playerTwoScore, roundNum;
    private  boolean activePlayer;

    int [] gameState = {2,2,2,2,2,2,2,2,2};

    int [][] winningPos = {
            {0,1,2},{3,4,5},{6,7,8}, //rivit
            {0,3,6},{1,4,7},{2,5,8}, // sarakkeet
            {0,4,8}, {2,4,6} //risti
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOne = findViewById(R.id.playerOneScore);  //scroe
        playerTwo = findViewById(R.id.playerTwoScore);

        reset = findViewById(R.id.reset);

        for (int i = 0; i < buttons.length; i++){
            String allButtons = "btn_" + i;
            int resource = getResources().getIdentifier(allButtons, "id",getPackageName());
            buttons[i] = findViewById(resource);
            buttons[i].setOnClickListener(this);
        }

        roundNum = 0;
        playerOneScore = 0;
        playerTwoScore = 0;
        activePlayer = true;

    }

    @Override
    public void onClick(View view) {
        if (!((Button)view).getText().toString().equals("")){
            return;
        }

        //buttonNum
        String allButtons = view.getResources().getResourceEntryName(view.getId());
        int gameStateP = Integer.parseInt(allButtons.substring(allButtons.length()-1)); //**********************************

        if(activePlayer){
            ((Button)view).setText("X");
            ((Button) view).setTextColor(Color.parseColor("#ff850a")); //ff8000  #FFC34A
            gameState[gameStateP] = 0;
            activePlayer = false;

        } else {
            ((Button)view).setText("O");    //miksi ei toimi ?
            ((Button) view).setTextColor(Color.parseColor("#09e8dd"));
            gameState[gameStateP] = 1;
            activePlayer = true;
        }
        roundNum++;

        if(winner()){
            if(activePlayer){
                playerOneScore++;
                updateScore();
                Toast.makeText(this,"Player One Won!",Toast.LENGTH_SHORT).show();
                setReset();
            }else {
                playerTwoScore++;
                updateScore();
                Toast.makeText(this,"Player Two Won!",Toast.LENGTH_SHORT).show();
                setReset();
            }
        } else if(roundNum == 9){
            Toast.makeText(this,"Tie!",Toast.LENGTH_SHORT).show();
            setReset();
        }

        reset.setOnClickListener(view1 -> {
            setReset();
            playerOneScore = 0;
            playerTwoScore = 0;
            updateScore();
        });

    }

    public boolean winner(){
        boolean trueWinner = false;

        for (int [] wPosition : winningPos){
            if (gameState[wPosition[0]] == gameState[wPosition[1]]
                    && gameState[wPosition[1]] == gameState[wPosition[2]] &&
                    gameState[wPosition[0]] != 2) {
                trueWinner = true;
                break;
            }
        }
        return trueWinner;
    }

    @SuppressLint("SetTextI18n")
    public void updateScore(){
        playerOne.setText(Integer.toString(playerOneScore));
        playerTwo.setText(Integer.toString(playerTwoScore));
    }

    public void setReset(){
        roundNum = 0;
        activePlayer = true;
        for (int i = 0; i < buttons.length; i++){
            gameState[i] = 2;
            buttons[i].setText("");
        }
    }


}