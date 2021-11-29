package com.example.assignment3_tenzintselha.model;

public class QuestionClassModel {
    int question;
    Boolean answer;
    int color;

    public QuestionClassModel(int question,Boolean answer,int color){
        this.question = question;
        this.answer = answer;
        this.color = color;
    }

    public int getQuestion() {
        return question;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public int getColor() {
        return color;
    }
}
