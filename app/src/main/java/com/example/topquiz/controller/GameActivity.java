package com.example.topquiz.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.topquiz.R;
import com.example.topquiz.model.Question;
import com.example.topquiz.model.QuestionBank;

import org.w3c.dom.Text;

import java.util.Arrays;

public abstract class GameActivity extends AppCompatActivity{
    private TextView mTextTitle;
    private Button mFirstButton;
    private Button mSecondButton;
    private Button mThirdButton;
    private Button mFourthButton;
    private QuestionBank mQuestions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_game);
        /* mTextTitle =  findViewById(R.id.activity_game_game_question_text);
        mFirstButton = findViewById(R.id.activity_game_answer1_btn);
        mSecondButton = findViewById(R.id.activity_game_answer2_btn);
        mThirdButton = findViewById(R.id.activity_game_answer3_btn);
        mFourthButton = findViewById(R.id.activity_game_answer4_btn);
        mQuestions = this.createQuestionBank();
        this.setDisplayQuestions(mQuestions.getQuestion());
        mFirstButton.setOnClickListener(this);
       */

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

}