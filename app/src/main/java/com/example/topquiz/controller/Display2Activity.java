package com.example.topquiz.controller;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topquiz.R;
import com.example.topquiz.model.Question;
import com.example.topquiz.model.QuestionBank;

import java.util.Arrays;

public class Display2Activity extends AppCompatActivity  implements View.OnClickListener {
    private TextView mTextTitle;
    private Button mFirstButton;
    private Button mSecondButton;
    private Button mThirdButton;
    private QuestionBank mQuestions;
    private Question mCurrentQuestion;
    private int mQuestionNumber;
    private int mScore;
    private Boolean mScreenState;
    public static final String SCORE_KEY_VALUE = "com.example.topquiz.controller";
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display2);
        mTextTitle =  findViewById(R.id.activity_game_game_question_text);
        mFirstButton = findViewById(R.id.activity_game_answer1_btn);
        mSecondButton = findViewById(R.id.activity_game_answer2_btn);
        mThirdButton = findViewById(R.id.activity_game_answer3_btn);
        mQuestions = this.createQuestionBank();
        mScore =0;
        mQuestionNumber = 3;
        mScreenState =true;
        generateQuestionDisplay();

        mFirstButton.setOnClickListener(this);
        mSecondButton.setOnClickListener(this);
        mThirdButton.setOnClickListener(this);

        mFirstButton.setTag(0);
        mSecondButton.setTag(1);
        mThirdButton.setTag(3);

    }

    private void generateQuestionDisplay() {
        mScreenState = true;
        if (mQuestionNumber > 0) {
            mCurrentQuestion = mQuestions.getQuestion();
            this.setDisplayQuestions(mCurrentQuestion);
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("well done !")
                    .setMessage("Your score is: "+ mScore)
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Display2Activity.this,MainActivity.class);
                            intent.putExtra(SCORE_KEY_VALUE,mScore);
                            setResult(RESULT_OK,intent);
                            finish();
                        }
                    })
                    .create()
                    .show();

        }
    }

    public QuestionBank createQuestionBank(){
        Question question1 = new Question("Who is the creator of Android?",
                Arrays.asList("Andy Rubin",
                        "Steve Wozniak",
                        "Jake Wharton",
                        "Paul Smith"),
                0);

        Question question2 = new Question("When did the first man land on the moon?",
                Arrays.asList("1958",
                        "1962",
                        "1967",
                        "1969"),
                3);

        Question question3 = new Question("What is the house number of The Simpsons?",
                Arrays.asList("42",
                        "101",
                        "666",
                        "742"),
                3);

        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3));
    }

    public void setDisplayQuestions(Question question){
        mTextTitle.setText(question.getQuestion());
        setButtonsText(mFirstButton,question.getChoiceList().get(0));
        setButtonsText(mSecondButton,question.getChoiceList().get(1));
        setButtonsText(mThirdButton,question.getChoiceList().get(2));


    }
    public void setButtonsText(Button nthButton,String choice){
        nthButton.setText(choice);
    }

    @Override
    public void onClick(View v) {
        mScreenState = false;
        int QuestionIndex = (int) v.getTag();
            if (mCurrentQuestion.getAnswerIndex() == QuestionIndex){
                Toast.makeText(Display2Activity.this,R.string.correct,Toast.LENGTH_SHORT).show();
                mScore++ ;
            }

            else{
                Toast.makeText(Display2Activity.this,R.string.uncorrect,Toast.LENGTH_SHORT).show();
            /*if (mScore > 0)
                mScore--;*/
            }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mQuestionNumber--;
                generateQuestionDisplay();
                }
            }, 2000);


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        return mScreenState && super.dispatchTouchEvent(ev);
    }


}