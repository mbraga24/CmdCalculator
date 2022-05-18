package com.havefunwith.CmdCalculator;

public class Main {

    public static void main(String[] args) {
       /*
           When a user passes command-line arguments they come as parameter to
           the Main method, they come in as args parameter.

            => If user provides no command-line arguments the app will process the default operation.

            => If user provides the correct number of command-line arguments:
                - opCode - leftVal - rightVal
               The application will do a calculation using the command-line arguments.
         */
        System.out.println("\n===================================================");
        System.out.println("It's the command line calculator project.");
        System.out.println("===================================================\n");
        /*
         * Parallel arrays -
         * Elements in each array are meant to be used with the corresponding element
         * in each of the other arrays.
         */
        double[] leftValues = {100.0, 25.0, 225.0, 11.0};
        double[] rightValues = {50.0, 92.0, 17.0, 3.0};
        char[] opCodes = {'d', 'a', 's', 'm'};
        double[] results = new double[opCodes.length];

        if (args.length == 0) { // if the length is 0, there is no command-line arguments.
            /*
             * Traditional For loop -
             * Allows incrementing index and access each individual
             * elements across each of the arrays.
             */
            for (int i = 0; i < opCodes.length; i++) {
                results[i] = execute(opCodes[i], leftValues[i], rightValues[i]); // Pass the appropriate members of each of the arrays as arguments
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
        } else if (args.length == 3) {  // If the length is 3, the user passed the correct number of arguments in the command-line
            handleCommandLineArgs(args);
        } else {  // If it's anything else a message will be displayed to the user.
            System.out.println("Please provide an operation code and 2 numeric values");
        }
    }

    static double execute(char opCode, double leftVal, double rightVal) {
        System.out.println(" Start of execute method :: ");
        double result;
        switch (opCode) {
            case 'a':
                result = leftVal + rightVal;
                break;
            case 's':
                result = leftVal - rightVal;
                break;
            case 'm':
                result = leftVal * rightVal;
                break;
            case 'd':
                result = rightVal != 0 ? leftVal / rightVal : 0.0;
                break;
            default:
                System.out.println("Invalid opCode: " + opCode);
                result = 0.0;
                break;
        }
        System.out.println(" End of execute method :: ");
        return result;
    }

    private static void handleCommandLineArgs(String[] args) {
        System.out.println(" Start of execute method :: ");
            /*
                Getting the data from the command-line will require a few steps.
                Remember: Java is a strongly typed language. The arguments are coming
                in as Strings. Each individual String is considered a sequence of characters.
             */

            /*
                args[0].charAt(0) -
                    - Retrieve the first argument of the String array.
                    - Because a String is considered a sequence of characters, extract the
                        first character from it.

                This line is converting a String representation of a character, into a char
                representation of a character.
             */
        char opCode = args[0].charAt(0);
            /*
                Double.parseDouble(args[1]) -
                Java provides a class named Double that has a utility method that named .parseDouble
                that converts a sequence of character into a double (pass args[1] & args[2] - args sub 1 - into method)
             */
        double leftVal = Double.parseDouble(args[1]);
        double rightVal = Double.parseDouble(args[2]);
        double result = execute(opCode, leftVal, rightVal);
        System.out.println(" End of handleCommandLine method ::");
        System.out.println("Result: " + result) ;
    }
}
