/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dice;

import java.lang.Object;
import java.util.Random;
import java.util.Date;

/**
 * Class that can create a dice object that includes an array of dies that can
 * be rolled.
 *
 * @author Aashna Narang
 */
public class Dice {

    //Instance Variables
    private int die[];
    private Random random;

    /**
     * Default constructor for dice. Automatically creates 2 die set to the
     * value to 1. A random number is initialized.
     */
    public Dice() {
        this(2);

    }

    /**
     * One argument constructor that creates as many die as the user wants, each
     * die is set to 0 and random number is initialized.
     *
     * @param numDice represents number of dice to be created
     */
    public Dice(int numDice) {
        if (numDice < 1) {
            throw new IllegalArgumentException("Number not valid,sorry");
        }
        this.random = new Random(new Date().hashCode());
        die = new int[numDice];
        for (int i = 0; i < numDice; i++) {
            this.die[i] = 1;
        }
    }

    /**
     * Private method that returns a value when die is rolled
     *
     * @return a value between 1 and 6
     */
    private int rollDie() {
        return random.nextInt(6) + 1;
    }

    //Public Methods
    /**
     * Goes through array and rolls each die, calls private method roll die and
     * saves each rolled value in array
     *
     * @return the sum of the rolls
     */
    public int roll() {
        int total = 0;
        for (int i = 0; i < this.die.length; i++) {
            die[i] = rollDie();
            total += die[i];
        }
        return total;
    }

    /**
     * Getter method that returns a defensive copy of the die array
     *
     * @return cloned copy of die array
     */
    public int[] getDieValues() {
        return this.die.clone();
    }

    /**
     * Iterates through the array and checks if are rolls with equal values
     *
     * @return true if there are two equal rolls, otherwise false.
     */
    public boolean hasDoubles() {
        for (int i = 0; i < die.length; i++) {
            for (int j = 0; j < die.length; j++) {
                if (i != j) {
                    if (die[i] == die[j]) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    /**
     * Converted dice array to a string that can be outputted
     *
     * @return string of die array values
     */
    @Override
    public String toString() {
        String answer = new String();
        for (int i = 0; i < die.length; i++) {
            answer += (this.die[i]) + " ";
        }
        return answer;
    }
}
