package com.example.user.gamequiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView questionLabel;
    private RadioButton answerBtn1;
    private RadioButton answerBtn2;
    private RadioButton answerBtn3;
    private RadioButton answerBtn4;
    private RadioButton answerBtn5;
    private RadioGroup radioGroup;
    private ImageView paises;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 5;

    ArrayList<ArrayList<String>>quizArray = new ArrayList<>();

    String quizData[][] = {
            //{Country, Certo, opcao1, opcao2}
            {"Qual a Capital do Canadá?", "Ottawa", "Santiago", "Washington, D.C.", "Buenos Aires", "Rio de Janeiro"},
            {"Qual a Capital do Chile?", "Santiago", "Paris", "Ottawa", "Berlim", "Jerusalém"},
            {"Qual a Capital dos EUA?", "Washington, D.C.", "Santiago", "Brasília", "Lisboa", "Buenos Aires"},
            {"Qual a Capital do Panamá?", "Cidade do Panamá", "Lima", "Vitória", "Dublim", "Paris"},
            {"Qual a Capital da Argentina?", "Buenos Aires", "Pequim", "Brasília", "Santiago", "Lisboa"},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionLabel = (TextView)findViewById(R.id.questionLabel);
        answerBtn1 = (RadioButton)findViewById(R.id.answerBtn1);
        answerBtn2 = (RadioButton)findViewById(R.id.answerBtn2);
        answerBtn3 = (RadioButton)findViewById(R.id.answerBtn3);
        answerBtn4 = (RadioButton)findViewById(R.id.answerBtn4);
        answerBtn5 = (RadioButton)findViewById(R.id.answerBtn5);
        radioGroup = findViewById(R.id.radioGroup);

        int quizCategory = getIntent().getIntExtra("QUIZ_CATEGORY", 0);
        Log.v("CATEGORY_TAG", quizCategory + "");


        for(int i = 0; i< quizData.length; i++){
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]);
            tmpArray.add(quizData[i][1]);
            tmpArray.add(quizData[i][2]);
            tmpArray.add(quizData[i][3]);
            tmpArray.add(quizData[i][4]);
            tmpArray.add(quizData[i][5]);

            quizArray.add(tmpArray);
        }
        showNextQuiz();
    }

    public void showNextQuiz(){

        Random random = new Random();

        int randomNum = random.nextInt(quizArray.size());

        ArrayList<String> quiz = quizArray.get(randomNum);

        questionLabel.setText(quiz.get(0));

        rightAnswer = quiz.get(1);

        quiz.remove(0);

        Collections.shuffle(quiz);

        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));
        answerBtn5.setText(quiz.get(4));

        quizArray.remove(randomNum);
    }

    public void checkAnswer (View view){

        RadioButton answerBtn = (RadioButton) findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if(btnText.equals(rightAnswer)){
            alertTitle = "Correto!";
            rightAnswerCount++;
        } else {
            alertTitle = "Errado";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Resposta: " + rightAnswer);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount == QUIZ_COUNT){
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    startActivity(intent);
                } else {
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();
    }
}