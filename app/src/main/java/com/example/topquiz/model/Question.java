package com.example.topquiz.model;

import java.util.List;

public class Question {
    private  String mQuestion;
    private List<String> mChoiceList;
    private int mAnswerIndex;

    public Question(String question, List<String> choiceList, int answerIndex) {
        mQuestion = question;
        mChoiceList = choiceList;
        mAnswerIndex = answerIndex;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public List<String> getChoiceList() {
        return mChoiceList;


    }

    public void setChoiceList(List<String> choiceList) {
        if (choiceList.size() > 0)
            mChoiceList = choiceList;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        if (answerIndex > 0 && answerIndex < 4)
            mAnswerIndex = answerIndex;
    }


}
