/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dice;

/**
 *
 * @author Aashna Narang
 */
public class DiceClient {

    /**
     * Creates a dice object with a pair of die that are rolled 2000 each. Each
     * value is saved in an array and the sum, average, and standard deviation
     * was found and outputted. A histogram is created where each * represents
     * 10 rolls for that number.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        double total = 0.0;
        double average = 0.0;
        double sq_differences = 0.0;
        double sq_sum = 0.0;
        double stdvar = 0.0;
        int dieVal[] = new int[4000];
        int hist[] = new int[6];

        //Creating a dice object 
        Dice die = new Dice();

        /* Saving the die array with the rolls into a new array and calculating 
        the sum
         */
        for (int i = 0; i < 2000; i++) {
            total += die.roll();
            dieVal[i * 2] = die.getDieValues()[0];
            dieVal[i * 2 + 1] = die.getDieValues()[1];
        }

        //Dividing sum of rolls by total rolls to get average
        average = total / 4000;

        //Finding standard deviation of rolls
        for (int i = 0; i < 2000; ++i) {
            sq_differences = dieVal[i] - average;
            sq_sum += sq_differences * sq_differences;
        }
        stdvar = Math.sqrt(sq_sum / dieVal.length);

        /*Adding to the histogram. If element equals 2 --> add 1 in the element 
        of array associated with 2 
         */
        for (int i = 0; i < dieVal.length; i++) {
            hist[dieVal[i] - 1] += 1;
        }

        //Output Info 
        System.out.println("Average: " + average);
        System.out.println("Standard deviation: " + stdvar);
        System.out.println("Histogram:");

        //Outputting histogram with values and *s
        for (int i = 0; i < 6; i++) {
            System.out.print(i + 1 + "(" + hist[i] + ")  :");
            for (int j = 0; j < hist[i] / 10.0; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
