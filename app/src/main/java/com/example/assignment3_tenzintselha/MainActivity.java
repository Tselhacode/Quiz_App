package com.example.assignment3_tenzintselha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.assignment3_tenzintselha.model.QuestionBank;
import com.example.assignment3_tenzintselha.model.StorageManager;
import com.example.assignment3_tenzintselha.model.myApp;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button trueBtn;
    Button falseBtn;
    ProgressBar progressBar;
    int i = 0;
    int correctAns = 0;
    QuestionBank bankObj;
    int listSize ;
    StorageManager storeObj;
    String text = "score:" + correctAns + "/" + listSize + "";
    int counter;
    List<Integer> answers = new ArrayList<>();
    int x;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bankObj = ((myApp) getApplication()).getBankObj();
        storeObj = ((myApp) getApplication()).getStoreObj();
        trueBtn = findViewById(R.id.trueBtn);
        falseBtn = findViewById(R.id.falseBtn);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(bankObj.getQuestionList().size());
        progressBar.setProgress((i/bankObj.getQuestionList().size()*100));
        updateFragment(bankObj.getQuestionList().get(i).getQuestion(), bankObj.getQuestionList().get(i).getColor());


        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listSize = bankObj.getQuestionList().size();
                if (bankObj.checkAnswer(i,"true")){
                    Toast.makeText(getApplicationContext(),"Correct Answer",Toast.LENGTH_SHORT).show();
                    correctAns += 1;
                }else{
                    Toast.makeText(getApplicationContext(),"Incorrect Answer",Toast.LENGTH_SHORT).show();
                }
                    i += 1;
                    progressBar.setProgress(i+1 /bankObj.getQuestionList().size());
                    showDialog();
                }
        });

        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listSize = bankObj.getQuestionList().size();
                if (bankObj.checkAnswer(i,"false")){
                    Toast.makeText(getApplicationContext(),"Correct Answer",Toast.LENGTH_SHORT).show();
                    correctAns += 1;
                }else{
                    Toast.makeText(getApplicationContext(),"Incorrect Answer",Toast.LENGTH_SHORT).show();
                }
                    i += 1;
                    progressBar.setProgress(i +1 /bankObj.getQuestionList().size());
                    showDialog();
            }
        });
    }

    private void updateFragment(int questionId, int color) {

        FragmentManager fragmentManager = getSupportFragmentManager();//Fragment manager - which fragment to call, what fragment to display
        fragmentManager.findFragmentById(R.id.frameLayout);//connect it with the layout
        Fragment1 questionFragment = Fragment1.newInstance(questionId, color);//putting the question and color in the fragment
        fragmentManager.beginTransaction().replace(R.id.frameLayout, questionFragment).commit();//start activity
    }


    public void showDialog(){
        if (i < listSize){
            updateFragment(bankObj.getQuestionList().get(i).getQuestion(), bankObj.getQuestionList().get(i).getColor());
        }else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            //set title
            alertDialog.setTitle("Result");
            //set message
            alertDialog.setMessage("Your Score is " + correctAns + " out of " + bankObj.getQuestionList().size());
            //set positive button
            answers.add(correctAns);
            alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //set what would happen when positive button is clicked
                    counter += 1;
                    storeObj.createSaveToInternalStorage(MainActivity.this,text);
                    storeObj.readFileFromInternalStorage(MainActivity.this);
                    Toast.makeText(getApplicationContext(),"score saved",Toast.LENGTH_LONG).show();
                }
            });
            //set negative button
            alertDialog.setNegativeButton("Ignore", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //set what should happen when negative button is clicked
                    Toast.makeText(getApplicationContext(), "Nothing Happened", Toast.LENGTH_LONG).show();
                    alertDialog.setCancelable(false);

                }
            });
            alertDialog.show();
            bankObj.shuffleQuestions();
            i = 0;
            correctAns = 0;
            updateFragment(bankObj.getQuestionList().get(i).getQuestion(), bankObj.getQuestionList().get(i).getColor());
            progressBar.setProgress(i +1 /bankObj.getQuestionList().size());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){ //saving data
        super.onSaveInstanceState(outState);
        outState.putInt("index",i);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState){ //restoring data
        super.onRestoreInstanceState(savedInstanceState);
        i = savedInstanceState.getInt("index");
        updateFragment(bankObj.getQuestionList().get(i).getQuestion(), bankObj.getQuestionList().get(i).getColor());
    }
    @Override
    public void onClick(View v) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_icon,menu);
        return true;
    }
    int sum = 0;

    public int add() {
        counter = 0;
        for (int x = 0; x < answers.size(); x++) {
            sum = sum + x;
        }
        return sum;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.average: {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                //set messag

                alertDialog.setMessage("Your correct answers are 6" + " in " + counter + " attempts");
                //set positive button
                alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked
                        storeObj.createSaveToInternalStorage(MainActivity.this, text);
                        storeObj.readFileFromInternalStorage(MainActivity.this);
                        Toast.makeText(getApplicationContext(), "score saved", Toast.LENGTH_LONG).show();
                    }
                });
                //set negative button
                AlertDialog.Builder AlertDialog = alertDialog;
                alertDialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        AlertDialog.setCancelable(false);

                    }
                });
                alertDialog.show();
                break;
            }
            case R.id.setNumQuestion: {
                Toast.makeText(this, "Questions selected", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                //set message
                alertDialog.setMessage("There are " + bankObj.getQuestionList().size() + " questions");
                //set positive button
                alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked
                        Toast.makeText(getApplicationContext(), "score saved", Toast.LENGTH_LONG).show();
                    }
                });

                //set negative button
                AlertDialog.Builder AlertDialog = alertDialog;
                alertDialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        AlertDialog.setCancelable(false);

                    }
                });
                alertDialog.show();
                break;
            }
            case R.id.delete: {
                storeObj.deleteSavedItems(MainActivity.this);
                Toast.makeText(this, "Reset selected", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                //set message
                alertDialog.setMessage("Your score history is deleted.");
                //set positive button
                alertDialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what would happen when positive button is clicked
                        Toast.makeText(getApplicationContext(),"score saved",Toast.LENGTH_LONG).show();
                    }
                });
                //set negative button
                AlertDialog.Builder AlertDialog = alertDialog;
                alertDialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked
                        AlertDialog.setCancelable(false);

                    }
                });
                alertDialog.show();
                break;
            }
        }
        return true;
    }

}


