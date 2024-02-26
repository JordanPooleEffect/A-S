package com.example.androidstudiocollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class CalculatorExercise extends AppCompatActivity implements View.OnClickListener {

    TextView answerTV, equationTV;
    MaterialButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,addition,subtraction,multiplication,division,open,close,equals,b00,btnDot,clear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_exercise);

        answerTV = findViewById(R.id.tvAnswer);
        equationTV = findViewById(R.id.tvEquation);

        assignId(clear, R.id.btnClear);
        assignId(b1, R.id.btnOne);
        assignId(b2, R.id.btnTwo);
        assignId(b3, R.id.btnThree);
        assignId(b4, R.id.btnFour);
        assignId(b5, R.id.btnFive);
        assignId(b6, R.id.btnSix);
        assignId(b7, R.id.btnSeven);
        assignId(b8, R.id.btnEight);
        assignId(b9, R.id.btnNine);
        assignId(b0, R.id.btnZero);
        assignId(b00, R.id.btnDoubleZero);
        assignId(addition, R.id.btnAdd);
        assignId(subtraction, R.id.btnSubtract);
        assignId(multiplication, R.id.btnMultiply);
        assignId(division, R.id.btnDivide);
        assignId(open, R.id.btnOpenPar);
        assignId(close, R.id.btnClosePar);
        assignId(equals, R.id.btnEquals);
        assignId(btnDot, R.id.btnPeriod);

    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String CalculateData = equationTV.getText().toString();

        if(buttonText.equals("=")) {
            equationTV.setText(answerTV.getText());
            return;
        }
        if(buttonText.equals("C")) {
            equationTV.setText("");
            answerTV.setText("0");
        }



    }
}