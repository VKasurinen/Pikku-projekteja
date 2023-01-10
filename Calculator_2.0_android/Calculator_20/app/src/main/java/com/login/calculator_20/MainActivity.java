package com.login.calculator_20;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    boolean isnewOp = true;
    String oldNumber = "";
    String op = "+";
    TextView resultTv, solutionTv;


    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

    }

    public void numberEvent(View view) {
        if (isnewOp){
            solutionTv.setText("");
        }
        isnewOp = false;

        String data = solutionTv.getText().toString();

        switch (view.getId()) {

            case (R.id.btn1):
                data += "1";
                resultTv.setText("1");
                break;
            case (R.id.btn2):
                data += "2";
                resultTv.setText("2");
                break;
            case (R.id.btn3):
                data += "3";
                resultTv.setText("3");
                break;
            case (R.id.btn4):
                data += "4";
                resultTv.setText("4");
                break;
            case (R.id.btn5):
                data += "5";
                resultTv.setText("5");
                break;
            case (R.id.btn6):
                data += "6";
                resultTv.setText("6");
                break;
            case (R.id.btn7):
                data += "7";
                resultTv.setText("7");
                break;
            case (R.id.btn8):
                data += "8";
                resultTv.setText("8");
                break;
            case (R.id.btn9):
                data += "9";
                resultTv.setText("9");
                break;
            case (R.id.btn0):
                data += "0";
                resultTv.setText("0");
                break;
            case (R.id.btnDot):
                data += ".";
                resultTv.setText(".");
                break;
            case (R.id.btnMinus):
                data += "-"+data;
                resultTv.setText("-");
                break;

        }
        solutionTv.setText(data);
    }

    public void  operatorEvent(View view){
        isnewOp = true;
        oldNumber = solutionTv.getText().toString();
        switch (view.getId()){
            case (R.id.btnDiv):
                op = "/";
                resultTv.setText("/");
                break;
            case (R.id.btnMulti):
                op = "*";
                resultTv.setText("*");
                break;
            case (R.id.btnPlus):
                op = "+";
                resultTv.setText("+");
                break;
            case (R.id.btnMinus):
                op = "-";
                resultTv.setText("-");
                break;
        }

    }

    public void equal(View view){
        String newNumber = solutionTv.getText().toString();
        double result = 0.0;

        switch (op){
            case "+":
                result = Double.parseDouble(oldNumber) + Double.parseDouble(newNumber);
                break;
            case "-":
                result = Double.parseDouble(oldNumber) - Double.parseDouble(newNumber);
                break;
            case "*":
                result = Double.parseDouble(oldNumber) * Double.parseDouble(newNumber);
                break;
            case "/":
                result = Double.parseDouble(oldNumber) / Double.parseDouble(newNumber);
                break;
        }
        solutionTv.setText(result + "");

    }

    public void cEvent(View view){
        solutionTv.setText("0");
        resultTv.setText("0");
        isnewOp = true;
    }
}