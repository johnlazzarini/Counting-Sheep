package com.johnlazzarini.countingsheep;

import android.util.Log;

import java.util.Random;

/**
 * Created by Axiom on 1/17/2017.
 */
public class RandomQuestion {
    private int leftOperand, rightOperand;
    private final int MIN = 0, LOW_MAX = 9, HIGH_MAX = 999, ARBITRARY_SEED = 999;

    public RandomQuestion() {
        randomizeOperands(MIN, ARBITRARY_SEED);
    }

    /*
    Randomly assigns a negative value to the left operand, right operand, or both.
     */
    public void randomlySignOperands(){
        Random rand = new Random();
        int chance = rand.nextInt(9) + 0;

        while (leftOperand >= 0 && rightOperand >= 0) {
            if (chance >= 5) {
                leftOperand *= -1;
            }

            chance = rand.nextInt(9) + 0;

            if (chance < 5) {
                rightOperand *= -1;
            }
        }
    }

    public void randomizeOperands(int min, int max){
        Random rand = new Random();
        leftOperand = rand.nextInt(max) + min;
        rightOperand = rand.nextInt(max) + min;
    }

    public String toString(){
        return ("" + leftOperand + " + " + rightOperand + " = ");
    }

    public int getLeftOperand(){
        return leftOperand;
    }

    public int getRightOperand(){
        return rightOperand;
    }

    public int getMin(){
        return MIN;
    }

    public int getLowMax(){
        return LOW_MAX;
    }

    public int getHighMax(){
        return HIGH_MAX;
    }
}
