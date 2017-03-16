package com.johnlazzarini.countingsheep;

import android.content.Context;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddIntegersActivity extends MathActivity implements MathActivityInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_attack);

        //Focuses on the EditText and forces the 12-key numeric keyboard to appear on activity start
        final EditText entry = (EditText) findViewById(R.id.entry_id);
        ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                .showSoftInput(entry, InputMethodManager.SHOW_FORCED);
        entry.setRawInputType(Configuration.KEYBOARD_12KEY);

        //Randomizes an initial question
        final RandomQuestion question = new RandomQuestion();
        question.randomizeOperands(question.getMin(), question.getLowMax());
        question.randomlySignOperands();

        //Set the activity duration in milliseconds (30,000 milliseconds == 3 seconds)
        final int timerLength = 30000;
        //Creates a user with information such as level, points, and whether or not he/she has run out of time for the activity
        final User theUser = new User("Addition", "Integers", timerLength);

        //Initializes the display field
        final TextView prompt = (TextView) findViewById(R.id.prompt_id);
        prompt.setText(question.toAdditionString());

        //Initializes the time display, which appears above the submit button
        final TextView contextDisplay = (TextView) findViewById(R.id.correctness_id);

        //Designates the points TextView
        final TextView points = (TextView) findViewById(R.id.current_points_id);

        //Designates the sheep face root
        final LinearLayout progressRoot = (LinearLayout) findViewById(R.id.progress_root);

        //Designates the sheep face root
        final LinearLayout sheepGraphicDisplay = (LinearLayout) findViewById(R.id.face_root);

        //Establishes button-press sounds
        final MediaPlayer successSound = MediaPlayer.create(this, R.raw.jobdone);
        final MediaPlayer incorrectSound = MediaPlayer.create(this, R.raw.hiccup);

        //Establishes email recipient editable and accompanying text title
        final TextView dataDisplay = (TextView) findViewById(R.id.data);
        final EditText recipientNameArea = (EditText) findViewById(R.id.email_recipient_name_area);

        //We clear this and replace the contents with a TextView displaying "congratulations!" or something when the time is up
        final LinearLayout inputOutput = (LinearLayout) findViewById(R.id.input_output);

        //Identifies the hidden-until-times-up "send email" button
        final Button sendEmail = (Button) findViewById(R.id.send_email);

        //Identifies the submit button, which will turn into a sort of "retry?" button when time is up to save resources
        final Button submit = (Button) findViewById(R.id.submit);

        assignOnClickListenerForEmailButton(theUser, recipientNameArea, sendEmail);

        assignOnClickListenerForSubmitButton(theUser, entry, question, prompt, points, successSound,
                incorrectSound, sheepGraphicDisplay, submit);

        createAndActivateTimer(timerLength, contextDisplay, dataDisplay, recipientNameArea,
                inputOutput, progressRoot, sendEmail, submit, AddIntegersActivity.this, theUser);
    }

    //overloaded methods
    public void addSheep(LinearLayout root, User theUser){
        ImageView newSheepFace = new ImageView(AddIntegersActivity.this, null);
        newSheepFace.setLayoutParams(new ActionBar.LayoutParams(60, 60));
        newSheepFace.setImageResource(R.drawable.boldsheep);
        root.addView(newSheepFace);
    }

    public boolean userAnswerIsCorrect(EditText theEntry, RandomQuestion theQuestion){
        boolean correct = Integer.parseInt(theEntry.getText().toString().trim()) == (theQuestion.getLeftOperand() + theQuestion.getRightOperand());
        return correct;
    }

    public void generateNewQuestion(TextView thePrompt, RandomQuestion theQuestion){
        theQuestion.randomizeOperands(theQuestion.getMin(),theQuestion.getLowMax());
        theQuestion.randomlySignOperands();
        thePrompt.setText(theQuestion.toAdditionString());
    }
}
