package com.example.samsungproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button game,work,goNext;
    EditText summa;

    FirebaseAuth mAuth;

    int i = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        game = findViewById(R.id.game);
        work = findViewById(R.id.work);
        goNext = findViewById(R.id.goNext);
        summa = findViewById(R.id.Summa);

        final Intent goWork = new Intent(MainActivity.this, WorkActivity.class);
        final Intent goGame = new Intent(MainActivity.this, GameActivity.class);

        final TransitionDrawable transition = (TransitionDrawable) game.getBackground();
        final TransitionDrawable transition1 = (TransitionDrawable) work.getBackground();

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i!=1) {
                    transition.startTransition(500);
                    transition1.startTransition(500);
                }

                i = 1;
            }
        });

        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i!=2) {
                    transition1.reverseTransition(500);
                    transition.reverseTransition(500);
                }

                i = 2;
            }
        });

        goNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int sum = Integer.parseInt(summa.getText().toString());

                if(i == 1) {
                    goGame.putExtra("SUM", sum);
                    startActivity(goGame);
                }
                else if(i == 2) {
                    goWork.putExtra("SUM", sum);
                    startActivity(goWork);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }
    }
}