package com.example.androidstudiocollection;

/**/

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorExercise extends AppCompatActivity {

    private TextView textView;
    private EditText textEdit;
    private CalculatorExerciseObject calculatorObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_exercise);

        textView = findViewById(R.id.textView);
        textEdit = findViewById(R.id.textEdit);
        calculatorObject = new CalculatorExerciseObject(textView, textEdit);

        initializeButtons();
        initializeListeners();
    }

    private void initializeButtons() {
        int[] digitButtonIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
        int[] operatorButtonIds = {R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide};

        for (int digitButtonId : digitButtonIds) {
            findViewById(digitButtonId).setOnClickListener(digitClickListener);
        }

        for (int operatorButtonId : operatorButtonIds) {
            findViewById(operatorButtonId).setOnClickListener(operatorClickListener);
        }

        findViewById(R.id.btnDot).setOnClickListener(dotClickListener);
        findViewById(R.id.btnOpenParenthesis).setOnClickListener(parenthesisClickListener);
        findViewById(R.id.btnCloseParenthesis).setOnClickListener(parenthesisClickListener);
        findViewById(R.id.btnEqual).setOnClickListener(equalClickListener);
    }

    private void initializeListeners() {
        findViewById(R.id.btnAC).setOnClickListener(acClickListener);
        findViewById(R.id.btnClear).setOnClickListener(clearClickListener);
    }

    public View.OnClickListener digitClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            calculatorObject.digitClick(button);
        }
    };

    public View.OnClickListener operatorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            calculatorObject.operatorClick(button, textView);
        }
    };

    private View.OnClickListener dotClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calculatorObject.dotClick();
        }
    };

    private View.OnClickListener parenthesisClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            calculatorObject.parenthesisClick(button);
        }
    };

    private View.OnClickListener acClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calculatorObject.acClick();
        }
    };

    private View.OnClickListener clearClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calculatorObject.clearClick();
        }
    };

    private View.OnClickListener equalClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calculatorObject.equalClick();
        }
    };
}