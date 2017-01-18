package com.johnlazzarini.countingsheep;

/**
 * Created by Axiom on 1/12/2017.
 */
public class User {
    private int currentStreak;

    User(){
        resetStreak();
    }

    public void incrementCurrentStreak(){
        ++currentStreak;
    }

    public void resetStreak(){
        currentStreak = 0;
    }

    public int getStreak(){
        return currentStreak;
    }
}
