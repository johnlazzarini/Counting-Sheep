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

    /*
    Very similar to the above function, but designed specifically for the integer subtraction
    activity such that two positive operands can be legal IFF their difference is less than zero.

    TODO rename this function to something like randomizeIntegerSubtractionOperands
     */
    public void randomlySignIntegerSubtraction(){
        Random rand = new Random();
        int chance = rand.nextInt(9) + 0;

        int temp1 = leftOperand, temp2 = rightOperand;

        //creates new operands that might be uniquely suitable for integer subtraction
        randomizeOperands(MIN, LOW_MAX);
        if (leftOperand - rightOperand < 0)
            return;

        //if the above case fails, continue as usual
        leftOperand = temp1;
        rightOperand = temp2;

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

    /*
    Necessarry because otherwise kids might have to input negative number solutions before they've
    been introduced to the concept of integers
     */
    public void randomizeNaturalSubtraction(int min, int max){
        Random rand = new Random();
        leftOperand = rand.nextInt(max) + min;
        rightOperand = rand.nextInt(max) + min;

        if (leftOperand - rightOperand < 0){
            int temp = leftOperand;
            leftOperand = rightOperand;
            rightOperand = temp;
        }
    }

    /*
    I decided to make this function so that the kids will always be able to work
    with nice, whole numbers.  Perhaps we can add double precision numbers in the future, but
    those will likely require a pen and paper, or at the very least more time per problem than
    would make this application fun to use (although I'm sure they'd learn a lot...)

    Will revisit this later.
     */
    public void randomizeDivisionOperands(int min, int max){
        do {
        Random rand = new Random();

            int multiplicand = rand.nextInt(max) + min;
            int multiplier = rand.nextInt(max) + min;
            int product = multiplicand * multiplier;
            int coinFlip = rand.nextInt(10) + 0;

        leftOperand = product;

        if (coinFlip > 5)
            rightOperand = multiplicand;
        else rightOperand = multiplier; }
        while (rightOperand == 0);
    }

    public String toAdditionString(){
        return ("" + leftOperand + " + " + rightOperand + " = ");
    }

    public String toSubtractionString(){
        return ("" + leftOperand + " - " + rightOperand + " = ");
    }

    public String toIntegerSubtractionString(){
        String leftStringOperand = "" + leftOperand, rightStringOperand = "" + rightOperand;

        if (leftOperand < 0)
            leftStringOperand = "(" + leftOperand + ")";

        if (rightOperand < 0)
            rightStringOperand = "(" + rightOperand + ")";


        return (leftStringOperand + " - " + rightStringOperand + " = ");
    }

    public String toMultiplicationString(){
        return ("" + leftOperand + " " + Character.toString((char)215) + " " + rightOperand + " = ");
    }

    public String toDivisionString(){
        return ("" + leftOperand + " / " + rightOperand + " = ");
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

    public int getSum() { return (leftOperand + rightOperand); }

    public int getDifference() { return (leftOperand - rightOperand); }

    public int getProduct() { return (leftOperand * rightOperand); }

    public int getQuotient() { return (leftOperand / rightOperand); }
}
