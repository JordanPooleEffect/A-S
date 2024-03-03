package com.example.androidstudiocollection;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.text.DecimalFormat;

public class CalculatorExercise extends AppCompatActivity {

    private TextView textView;
    private StringBuilder input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_exercise);

        initializeButtons();
        initializeListeners();

        textView = findViewById(R.id.textView);
        input = new StringBuilder();
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

    private View.OnClickListener digitClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            input.append(button.getText());
            updateTextView();
        }
    };

    private View.OnClickListener operatorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            input.append(button.getText());
            updateTextView();
        }
    };

    private View.OnClickListener dotClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!input.toString().contains(".")) {
                input.append(".");
                updateTextView();
            }
        }
    };

    private View.OnClickListener parenthesisClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button = (Button) v;
            input.append(button.getText());
            updateTextView();
        }
    };

    private View.OnClickListener acClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            input.setLength(0);
            updateTextView();
        }
    };

    private View.OnClickListener clearClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (input.length() > 0) {
                input.deleteCharAt(input.length() - 1);
                updateTextView();
            }
        }
    };

    private View.OnClickListener equalClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            evaluateExpression();
        }
    };

    private void updateTextView() {
        textView.setText(input.toString());
    }

    private void evaluateExpression() {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("#.####");
            double result = evaluate(input.toString());
            input.setLength(0);
            input.append(decimalFormat.format(result));
            updateTextView();
        } catch (Exception e) {
            handleEvaluationError();
        }
    }

    private double evaluate(String expression) {
        try {
            Expression e = new ExpressionBuilder(expression).build();
            return e.evaluate();
        } catch (ArithmeticException | IllegalArgumentException e) {
            throw new RuntimeException("Error during expression evaluation", e);
        }
    }

    private void handleEvaluationError() {
        input.setLength(0);
        input.append("Error");
        updateTextView();
    }
}
