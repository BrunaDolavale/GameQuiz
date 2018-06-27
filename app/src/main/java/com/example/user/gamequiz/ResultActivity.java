package com.example.user.gamequiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView resultLabel = (TextView) findViewById(R.id.resultLabel);
        MediaPlayer win;
        MediaPlayer fail;

        win = MediaPlayer.create(this, R.raw.ganhou);
        fail = MediaPlayer.create(this, R.raw.perdeu);
        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);

        SharedPreferences settings = getSharedPreferences("quizApp", Context.MODE_PRIVATE);
        resultLabel.setText(score + " / 5");

        if (score >=3 ) {
            win.start();
        } else {
            fail.start();
        }
    }

    public void returnTop(View view){
        Intent intent = new Intent(getApplicationContext(), StartActivity.class);
        startActivity(intent);
    }
}