package com.johnlazzarini.countingsheep;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.LightingColorFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.R.attr.button;
import static android.R.attr.filter;
import static java.net.Proxy.Type.HTTP;

/**
 * Created by Axiom on 2/2/2017.
 */

abstract public class MathActivity extends AppCompatActivity implements MathActivityInterface {


    public void displayStatistics(User theUser, TextView contextDisplay) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        params.weight = 1.0f;
        params.gravity = Gravity.RIGHT;

        contextDisplay.setLayoutParams(params);
        contextDisplay.setGravity(Gravity.RIGHT);

        String theText = "Highest level: " + theUser.getLevel() + "\nSolutions per second: "
                + theUser.getSolutionsPerSecond()
                + "\nNumber of sheep counted: "
                + theUser.getPoints();

        contextDisplay.setText(theText);
    }

    public void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    abstract public void addSheep(LinearLayout root, User theUser);

    abstract public boolean userAnswerIsCorrect(EditText theEntry, RandomQuestion theQuestion);

    abstract public void generateNewQuestion(TextView thePrompt, RandomQuestion theQuestion);

    public void displayToast() {
        Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
    }

    public void removeCountdownActivityViews(LinearLayout inputOutput, LinearLayout progressRoot) {
        inputOutput.removeAllViews();
        progressRoot.removeAllViews();
    }

    public void changeEmailButtonColor(Button sendEmail) {
        LightingColorFilter newColor = new LightingColorFilter(filter, 0x27ae60); //Android doesn't seem to have a "nice" way to adjust button colors for multiple buttons yet
        sendEmail.getBackground().setColorFilter(newColor);
        sendEmail.setVisibility(View.VISIBLE);
    }

    public void makeHiddenViewsAppear(TextView dataDisplay, EditText recipient) {
        dataDisplay.setVisibility(View.VISIBLE);
        recipient.setVisibility(View.VISIBLE);
    }

    public void turnSubmitButtonIntoTryAgainButton(Button submit, User theUser) {
        submit.setText("Try Again");
        theUser.setTimerIsFinished(true);
    }

    public String generateEmailMessage(User theUser) {
        String pluralityQuestions = "questions";
        String pluralitySeconds = "seconds";

        if (theUser.getPoints() == 1) {
            pluralityQuestions = "question";
            pluralitySeconds = "second";
        }

        String message = "I reached level " + theUser.getLevel() + " in the Counting Sheep \""
                + theUser.getCategory() + " with " + theUser.getNumericType()
                + "\" activity!\n\nI correctly answered " + theUser.getPoints() + " "
                + pluralityQuestions + " in " + theUser.getTimerLengthInSeconds() + " "
                + pluralitySeconds + ", at an average rate of " + theUser.getSolutionsPerSecond()
                + " solutions per second.";

        return message;
    }

    public void startEmailSendingActivity(User theUser, EditText recipientNameArea) {

        String subject = "My Counting Sheep Activity Achievement";
        String message = generateEmailMessage(theUser);
        String theRecipient = recipientNameArea.getText().toString();

        String uriText =
                "mailto:" + theRecipient +
                        "?subject=" + Uri.encode(subject) +
                        "&body=" + Uri.encode(message);

        Uri uri = Uri.parse(uriText);

        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setData(uri);
        startActivity(Intent.createChooser(sendIntent, "Send email"));
    }

    public void executeCorrectResponseActions(User theUser, RandomQuestion question, TextView prompt,
                                              EditText entry, MediaPlayer successSound,
                                              LinearLayout sheepGraphicDisplay) {
        addSheep(sheepGraphicDisplay, theUser);
        theUser.incrementPoints();
        successSound.start();
        entry.setText("");
        generateNewQuestion(prompt, question);
    }

    public void executeNextLevelActions(User theUser, LinearLayout sheepGraphicDisplay) {
        theUser.incrementLevel();
        sheepGraphicDisplay.removeAllViews();
    }

    public void executeIncorrectResponseActions(EditText entry, MediaPlayer incorrectSound) {
        entry.setText("");
        incorrectSound.start();
    }

    public void evaluateUserResponse(User theUser, EditText entry, RandomQuestion question,
                                     TextView prompt, TextView points, MediaPlayer successSound,
                                     MediaPlayer incorrectSound, LinearLayout sheepGraphicDisplay) {
        try {
            Integer.parseInt(entry.getText().toString());

            if (userAnswerIsCorrect(entry, question)) {
                executeCorrectResponseActions(theUser, question, prompt, entry, successSound, sheepGraphicDisplay);

                if (theUser.getPoints() % 5 == 0 && theUser.getPoints() != 0) {
                    executeNextLevelActions(theUser, sheepGraphicDisplay);
                }

                points.setText("Level " + theUser.getLevel());
            } else {
                executeIncorrectResponseActions(entry, incorrectSound);
            }
        } catch (NumberFormatException e) {
            displayToast();
        }
    }

    public void executeEndOfTimerActions(LinearLayout inputOutput, LinearLayout progressRoot,
                                         Button sendEmail, Button submit, Activity theActivity,
                                         TextView contextDisplay, TextView dataDisplay,
                                         EditText recipientNameArea, User theUser) {
        removeCountdownActivityViews(inputOutput, progressRoot);
        changeEmailButtonColor(sendEmail);
        hideKeyboard(theActivity);
        makeHiddenViewsAppear(dataDisplay, recipientNameArea);
        turnSubmitButtonIntoTryAgainButton(submit, theUser);
        displayStatistics(theUser, contextDisplay);
    }

    public void assignOnClickListenerForSubmitButton(final User theUser, final EditText entry,
                                                     final RandomQuestion question, final TextView prompt,
                                                     final TextView points, final MediaPlayer successSound,
                                                     final MediaPlayer incorrectSound,
                                                     final LinearLayout sheepGraphicDisplay, final Button submit) {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (theUser.getTimerIsFinished()) {
                    recreate();
                } else {
                    evaluateUserResponse(theUser, entry, question, prompt, points, successSound,
                            incorrectSound, sheepGraphicDisplay);
                }
            }
        });
    }

    public void assignOnClickListenerForEmailButton(final User theUser, final EditText recipientNameArea, Button sendEmail) {
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startEmailSendingActivity(theUser, recipientNameArea);
            }
        });
    }


    public void createAndActivateTimer(final int timerLength, final TextView contextDisplay,
                                       final TextView dataDisplay, final EditText recipientNameArea,
                                       final LinearLayout inputOutput, final LinearLayout progressRoot,
                                       final Button sendEmail, final Button submit, final Activity theActivity,
                                       final User theUser) {
        new CountDownTimer(timerLength, 1000) {
            public void onTick(long millisUntilFinished) {
                if ((millisUntilFinished / 1000) == 1) {
                    contextDisplay.setText("" + millisUntilFinished / 1000 + " second remaining!");
                } else {
                    contextDisplay.setText("" + millisUntilFinished / 1000 + " seconds remaining!");
                }
            }

            public void onFinish() {
                executeEndOfTimerActions(inputOutput, progressRoot,
                        sendEmail, submit, theActivity,
                        contextDisplay, dataDisplay,
                        recipientNameArea, theUser);
            }

        }.start();

    }
}