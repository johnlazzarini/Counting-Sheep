package com.johnlazzarini.countingsheep;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class AddNaturalsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_naturals);

        /*
        Focuses the 12-key numeric keyboard to appear on activity start
         */
       final EditText entry = (EditText)findViewById(R.id.add_naturals_entry_id);
        ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE))
                .showSoftInput(entry, InputMethodManager.SHOW_FORCED);
        entry.setRawInputType(Configuration.KEYBOARD_12KEY);

        /*
        Randomize an initial question
         */
        final RandomQuestion question = new RandomQuestion();
        question.randomizeOperands(question.getMin(), question.getLowMax());
        /*
        Initializes the user's streak
         */
        final User theUser = new User();
        /*
        Initializes the display field
         */
        final TextView prompt = (TextView)findViewById(R.id.add_naturals_prompt_id);
        prompt.setText(question.toString());

        /*
        Initializes correctness
         */
        final String initialPrompt = "Waiting for answer...";
        final TextView correctness = (TextView)findViewById(R.id.add_naturals_correctness_id);
        correctness.setText(initialPrompt);

        /*
        Designates the points TextView
         */
        final TextView points = (TextView)findViewById(R.id.add_naturals_current_points_id);

        /*
        Establishes button-press sounds
         */
        final MediaPlayer successSound = MediaPlayer.create(this, R.raw.jobdone);
        final MediaPlayer incorrectSound = MediaPlayer.create(this, R.raw.hiccup);

        /*
        Designates the submit button and associates it with a listener
         */
        Button submit = (Button)findViewById(R.id.add_naturals_submit);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (entry.getText().toString().trim().equals("")){
                    displayToast();
                }
            else if (checkCorrectness(entry, question)){
                    correctness.setText("Correct!");
                    successSound.start();
                    entry.setText("");
                    generateNewQuestion(prompt, question);
                    theUser.incrementCurrentStreak();
                    points.setText("Current streak: " + theUser.getStreak());
                }
                else {
                    correctness.setText("Try again...");
                    entry.setText("");
                    incorrectSound.start();
                    theUser.resetStreak();
                    points.setText("Current streak: " + theUser.getStreak());
                }
            }
        });
    }

    public boolean checkCorrectness(EditText theEntry, RandomQuestion theQuestion){
        boolean correct = Integer.parseInt(theEntry.getText().toString().trim()) == (theQuestion.getLeftOperand() + theQuestion.getRightOperand());
        return correct;
    }

    public void generateNewQuestion(TextView thePrompt, RandomQuestion theQuestion){
        theQuestion.randomizeOperands(theQuestion.getMin(),theQuestion.getLowMax());
        thePrompt.setText(theQuestion.toString());
    }

    public void displayToast(){
        Toast.makeText(this,"Enter a number!",Toast.LENGTH_SHORT).show();
    }
}
