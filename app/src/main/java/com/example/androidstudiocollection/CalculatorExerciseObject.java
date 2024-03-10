package com.example.androidstudiocollection;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.text.DecimalFormat;
import java.util.Stack;

public class CalculatorExerciseObject {

    private StringBuilder input;
    private Stack<String> expressionStack;
    private TextView textView;
    private EditText textEdit;
    private double previousResult = 0;

    public CalculatorExerciseObject(TextView textView, EditText textEdit) {
        input = new StringBuilder();
        expressionStack = new Stack<>();
        this.textView = textView;
        this.textEdit = textEdit;
    }

    private void updateTextView(TextView targetTextView) {
        if (targetTextView != null) {
            targetTextView.setText(input.toString());
        }
    }

    private void updateTextEdit(EditText targetTextEdit) {
        if (targetTextEdit != null) {
            targetTextEdit.setText(input.toString());
        }
    }

    public void digitClick(Button button, EditText targetTextEdit) {
        input.append(button.getText());
        updateTextEdit(targetTextEdit);
    }

    public void operatorClick(Button button, TextView targetTextView, EditText targetTextEdit) {
        if (targetTextEdit.getText().length() > 0) {
            targetTextView.setText(targetTextEdit.getText());
            targetTextEdit.setText("");
        }

        if (input.length() > 0) {
            char lastChar = input.charAt(input.length() - 1);
            if (isOperator(lastChar)) {
                input.append(previousResult);
            }

            if (isOperator(lastChar) && isOperator(button.getText().charAt(0))) {
                input.setCharAt(input.length() - 1, button.getText().charAt(0));
                input.append(previousResult);
            } else {
                input.append(button.getText());
            }
        }

        updateTextView(targetTextView);
    }


    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public void dotClick() {
        if (!input.toString().contains(".")) {
            input.append(".");
            updateTextView(textView);
        }
    }

    public void parenthesisClick(Button button) {
        input.append(button.getText());
        updateTextView(textView);
    }

    public void acClick() {
        expressionStack.clear();
        input.setLength(0);
        updateTextView(textView);
        updateTextEdit(textEdit);
    }

    public void clearClick() {
        if (input.length() > 0) {
            input.deleteCharAt(input.length() - 1);
            updateTextView(textView);
        } else {
            String lastExpression = popFromStack();
            input.append(lastExpression);
            updateTextView(textView);
            updateTextEdit(textEdit);
        }
    }

    public void equalClick() {
        updateTextView(textView);

        if (input.length() == 0 || isOperator(input.charAt(input.length() - 1))) {
            evaluateExpression();
            updateStackAndEditText();
            previousResult = evaluate(input.toString());
        } else {
            previousResult = evaluate(input.toString());
            evaluateExpression();
            updateStackAndEditText();
            input.append(previousResult);
            updateTextView(textView);
        }

    }

    private void evaluateExpression() {
        try {
            DecimalFormat decimalFormat = new DecimalFormat("#.####");
            double result = evaluate(input.toString());
            input.setLength(0);
            input.append(decimalFormat.format(result));
            updateTextEdit(textEdit);
        } catch (Exception e) {
            handleEvaluationError();
        }
    }

    private double evaluate(String expression) {
        Expression e = new ExpressionBuilder(expression).build();
        return e.evaluate();
    }

    private void handleEvaluationError() {
        input.setLength(0);
        input.append("Error");
        updateTextView(textView);
    }

    private void pushToStack(String expression) {
        expressionStack.push(expression);
    }

    private String popFromStack() {
        if (!expressionStack.isEmpty()) {
            return expressionStack.pop();
        }
        return "";
    }

    private void updateStackAndEditText() {
        if (!input.toString().isEmpty()) {
            pushToStack(input.toString());
            input.setLength(0);
        }
    }
}