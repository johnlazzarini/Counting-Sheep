package com.johnlazzarini.countingsheep;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Axiom on 2/2/2017.
 */

public interface MathActivityInterface {
    void displayStatistics(User theUser, TextView contextDisplay);
    void hideKeyboard(Activity activity);  //hides the numberpad that automatically displays on activity start
    void addSheep(LinearLayout root, User theUser); //adds a sheep graphic to the top right corner
    boolean userAnswerIsCorrect(EditText theEntry, RandomQuestion theQuestion);
    void generateNewQuestion(TextView thePrompt, RandomQuestion theQuestion);
    void displayToast();
    void removeCountdownActivityViews(LinearLayout inputOutput, LinearLayout progressRoot);
    void makeHiddenViewsAppear(TextView dataDisplay, EditText recipient);
    void changeEmailButtonColor(Button sendEmail);
    void turnSubmitButtonIntoTryAgainButton(Button submit, User theUser);
    void startEmailSendingActivity(User theUser, EditText recipientNameArea);
}
