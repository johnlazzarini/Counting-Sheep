package com.johnlazzarini.countingsheep;

import android.icu.text.DecimalFormat;

/**
 * Created by Axiom on 1/12/2017.
 */
public class User {
    private int level = 1, points = 0, timerLength = 0; //timerLength in milliseconds
    private String category, numericType;
    private boolean timerIsFinished = false;

    public User(){
    }

    public User(String newCategory, String newNumericType, int newTimerLength){
        setCategory(newCategory);
        setNumericType(newNumericType);
        setTimerLength(newTimerLength);
    }

    public void setCategory(String newCategory){
        category = newCategory;
    }

    public void setNumericType(String newNumericType){
        numericType = newNumericType;
    }

    public void setTimerIsFinished(boolean state) { timerIsFinished = state; }

    public void setTimerLength(int length) { timerLength = length; }

    public void incrementPoints() { ++points; }

    public void incrementLevel() { ++level; }

    public void resetPoints() { points = 0; }

    public void resetLevel() { level = 1; }

    public int getPoints() { return points; }

    public int getLevel() { return level; }

    public String getCategory() { return category; }

    public String getNumericType() { return numericType; }

    public boolean getTimerIsFinished() { return timerIsFinished; }

    public double getSolutionsPerSecond() {
        double solutionsPerSecond = getPoints()/getTimerLengthInSeconds();
        String str = String.format("%1.2f", solutionsPerSecond);
        solutionsPerSecond = Double.valueOf(str);
        return solutionsPerSecond;
    }

    public double getTimerLengthInSeconds() { return (double)(timerLength / 1000); }


}
