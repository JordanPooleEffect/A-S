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

    private boolean isLastOperationEquals = false;

    public void digitClick(Button button, EditText targetTextEdit) {
        if (isLastOperationEquals) {
            acClick();
            isLastOperationEquals = false;
        }
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

            if (isOperator(lastChar) && isOperator(button.getText().charAt(0))) {
                input.setCharAt(input.length() - 1, button.getText().charAt(0));
            } else {
                input.append(button.getText());
            }
        }
        updateTextView(targetTextView);
    }


    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private int dotCount = 0;

    public void dotClick() {
        int length = input.length();
        char lastChar = length > 0 ? input.charAt(length - 1) : 0;

        if (lastChar == '.') {
            input.deleteCharAt(length - 1);
            dotCount--;
        } else if (length == 0 || isOperator(lastChar) || dotCount < 2) {
            input.append(".");
            dotCount++;
        }

        updateTextView(textView);
    }

    public void parenthesisClick(Button button) {
        input.append(button.getText());
        updateTextView(textView);
    }

    public void acClick() {
        expressionStack.clear();
        input.setLength(0);
        updateTextView(textView);
        textView.setText("0");
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
            textView.setText("");
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
            handleEvaluationError(e);
        }
    }

    private double evaluate(String expression) {
        try {
            Expression e = new ExpressionBuilder(expression).build();
            return e.evaluate();
        } catch (ArithmeticException | IllegalArgumentException e) {
            handleEvaluationError(e);
            return Double.NaN;
        }
    }

    private void handleEvaluationError(Exception e) {
        e.printStackTrace();
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