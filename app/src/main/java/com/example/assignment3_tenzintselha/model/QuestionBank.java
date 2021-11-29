package com.example.assignment3_tenzintselha.model;

import android.content.res.Resources;
import android.widget.Toast;

import com.example.assignment3_tenzintselha.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class QuestionBank {
    ArrayList<QuestionClassModel> questionList = new ArrayList<QuestionClassModel>(10);
    ArrayList<Integer> colorList = new ArrayList<Integer>(10);

    private QuestionClassModel question1;
    private QuestionClassModel question2;
    private QuestionClassModel question3;
    private QuestionClassModel question4;
    private QuestionClassModel question5;
    private QuestionClassModel question6;
    private QuestionClassModel question7;
    private QuestionClassModel question8;
    private QuestionClassModel question9;
    private QuestionClassModel question10;


    public QuestionBank() {
        colorList.add(R.color.purple_200);
        colorList.add(R.color.teal_200);
        colorList.add(R.color.Ivory);
        colorList.add(R.color.Magenta);
        colorList.add(R.color.Bisque);
        colorList.add(R.color.Orange);
        colorList.add(R.color.FloralWhite);
        colorList.add(R.color.GhostWhite);
        colorList.add(R.color.MistyRose);
        colorList.add(R.color.BlanchedAlmond);

        this.question1 = new QuestionClassModel(R.string.question_one, false, colorList.get(0));
        this.question2 = new QuestionClassModel(R.string.question_two,false, colorList.get(1));
        this.question3 = new QuestionClassModel(R.string.question_three,true,colorList.get(2));
        this.question4 = new QuestionClassModel(R.string.question_four,false,colorList.get(3));
        this.question5 = new QuestionClassModel(R.string.question_five,true,colorList.get(4));
        this.question6 = new QuestionClassModel(R.string.question_six,true,colorList.get(5));
        this.question7 = new QuestionClassModel(R.string.question_seven,false,colorList.get(6));
        this.question8 = new QuestionClassModel(R.string.question_eight,true,colorList.get(7));
        this.question9 = new QuestionClassModel(R.string.question_nine,false,colorList.get(8));
        this.question10 = new QuestionClassModel(R.string.question_ten,true,colorList.get(9));
        questionList.add(this.question1);
        questionList.add(this.question2);
        questionList.add(this.question3);
        questionList.add(this.question4);
        questionList.add(this.question5);
        questionList.add(this.question6);
        questionList.add(this.question7);
        questionList.add(this.question8);
        questionList.add(this.question9);
        questionList.add(this.question10);

    }


    public ArrayList<QuestionClassModel> getQuestionList() {

        return questionList;
    }

    public ArrayList<Integer> getColorList() {

        return colorList;
    }

    public Boolean checkAnswer(int i, String answer){
        String actualAnswer = questionList.get(i).getAnswer().toString();
        if (answer == actualAnswer){
            return true;
        }else {
            return false;
        }
    }


    public ArrayList<QuestionClassModel> shuffleQuestions(){
        Collections.shuffle(questionList);
        return questionList;
    }
}
