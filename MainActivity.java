package com.example.android.myapplication8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    boolean turn = true; // true = X & false = O
    int turn_count = 0;
    Button[] bArray = null;
    Button a1, a2, a3, b1, b2, b3, c1, c2, c3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a1 = (Button) findViewById(R.id.A1);
        b1 = (Button) findViewById(R.id.B1);
        c1 = (Button) findViewById(R.id.C1);
        a2 = (Button) findViewById(R.id.A2);
        b2 = (Button) findViewById(R.id.B2);
        c2 = (Button) findViewById(R.id.C2);
        a3 = (Button) findViewById(R.id.A3);
        b3 = (Button) findViewById(R.id.B3);
        c3 = (Button) findViewById(R.id.C3);
        bArray = new Button[] { a1, a2, a3, b1, b2, b3, c1, c2, c3 };



        Button bnew = (Button) findViewById(R.id.button1);
        bnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turn = true;
                turn_count = 0;
                enableOrDisable(true);
            }
        });
        for (Button b : bArray)
            b.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        // just to be clear. as no other views are registered YET,
        // it is safe to assume that only
        // the 9 buttons call this on click
        buttonClicked(v);
    }

    private void buttonClicked(View v) {
        Button b = (Button) v;
        if (turn) {
            // X's turn
            b.setText("X");
        } else {
            // O's turn
            b.setText("O");
        }
        turn_count++;
        b.setClickable(false);
        b.setBackgroundColor(Color.WHITE);
        turn = !turn;
        checkForWinner();
    }

    private void checkForWinner() {

        boolean there_is_a_winner = false;

        // horizontal:
        if (a1.getText() == a2.getText() && a2.getText() == a3.getText()
                && !a1.isClickable())
            there_is_a_winner = true;
        else if (b1.getText() == b2.getText() && b2.getText() == b3.getText()
                && !b1.isClickable())
            there_is_a_winner = true;
        else if (c1.getText() == c2.getText() && c2.getText() == c3.getText()
                && !c1.isClickable())
            there_is_a_winner = true;
            // vertical:
        else if (a1.getText() == b1.getText() && b1.getText() == c1.getText()
                && !a1.isClickable())
            there_is_a_winner = true;
        else if (a2.getText() == b2.getText() && b2.getText() == c2.getText()
                && !b2.isClickable())
            there_is_a_winner = true;
        else if (a3.getText() == b3.getText() && b3.getText() == c3.getText()
                && !c3.isClickable())
            there_is_a_winner = true;
            // diagonal:
        else if (a1.getText() == b2.getText() && b2.getText() == c3.getText()
                && !a1.isClickable())
            there_is_a_winner = true;
        else if (a3.getText() == b2.getText() && b2.getText() == c1.getText()
                && !b2.isClickable())
            there_is_a_winner = true;

        if (there_is_a_winner) {
            if (!turn)
                message("X wins");
            else
                message("O wins");
            //enableOrDisable(false);
        } else if (turn_count == 9)
            message("Draw!");
    }

    private void message(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT)
                .show();
    }

    private void enableOrDisable(boolean enable) {
        for (Button b : bArray) {
            b.setText("");
            b.setClickable(enable);
            if (enable) {
                b.setBackgroundColor(Color.parseColor("#33b5e5"));
            } else {
                b.setBackgroundColor(Color.LTGRAY);
            }
        }
    }
}
