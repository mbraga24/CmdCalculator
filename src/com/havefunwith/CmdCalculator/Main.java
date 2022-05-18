package com.havefunwith.CmdCalculator;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n===================================================");
        System.out.println("Hey, you! It's the command line calculator project.");
        System.out.println("===================================================\n");

        /*
         * Parallel arrays -
         * Elements in each array are meant to be used with the corresponding element
         * in each of the other arrays.
         */
        double[] leftVals = {100.0, 25.0, 225.0, 11.0};
        double[] rightVals = {50.0, 92.0, 17.0, 3.0};
        char[] opCodes = {'d', 'a', 's', 'm'};
        double[] results = new double[opCodes.length];

        /*
        * Traditional For loop -
        * Allows to increment index and access each individual
        * elements across each of the arrays.
         */

        for (int i = 0; i < opCodes.length; i++) {
            switch (opCodes[i]) {
                case 'a':
                    results[i] = leftVals[i] + rightVals[i];
                    break;
                case 's':
                    results[i] = leftVals[i] - rightVals[i];
                    break;
                case 'm':
                    results[i] = leftVals[i] * rightVals[i];
                    break;
                case 'd':
                    results[i] = rightVals[i] != 0 ? leftVals[i] / rightVals[i] : 0.0;
                    break;
                default:
                    System.out.println("Invalid opCode: " + opCodes[i]);
                    results[i] = 0.0;
                    break;
            }
        }

        /*
        * For each loop -
        * The results are all in one array, thus For each loop can be used
        * to loop over each array element.
        *
        * For each loop will still need to have the variable initialized with
        * the proper type to return each array element.
         */
        for (double currentResult : results) {
            System.out.println(currentResult);
        }
    }
}
