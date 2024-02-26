package com.example.androidstudiocollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ButtonExercise extends AppCompatActivity {

    Button btn1,btn2, btn3, btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_exercise);

        btn1 = (Button) findViewById(R.id.btnReturn);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ButtonExercise.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn2 = (Button) findViewById(R.id.btnToast);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(ButtonExercise.this, "Toasted, Sunog, WOWOWOWOWOW", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        btn3 = (Button) findViewById(R.id.btnBGchange);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ButtonExercise.this, LayoutExercise.class);
                startActivity(intent);
            }
        });


        btn4 = (Button) findViewById(R.id.btnInvisible);

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn4.setVisibility(View.GONE);
            }
        });

        Button btn5 = (Button) findViewById(R.id.btnBTNchange);

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn5.setBackgroundColor(Color.RED);
            }
        });

        final ConstraintLayout mainLayout = findViewById(R.id.btnOpenInstagram);

        Button changeColorButton = findViewById(R.id.btnBGchange);

        changeColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainLayout.setBackgroundColor(Color.YELLOW);
            }
        });


    }
}