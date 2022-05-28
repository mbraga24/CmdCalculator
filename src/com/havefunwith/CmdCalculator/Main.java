package com.havefunwith.CmdCalculator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


/*
    Application Description:
    -> Can perform basic math operations.
    -> Can perform date arithmetic.
    -> Can perform all operations with numeric values, both as words (from 0-9), as well as numbers.
    -> Can accept input from users as both command line arguments, as well as interactively.
 */
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
         * Elements in each array are meant to be used with the corresponding element in each of
         * the other arrays.
         */
        double[] leftValues = {100.0, 25.0, 225.0, 11.0};
        double[] rightValues = {50.0, 92.0, 17.0, 3.0};
        char[] opCodes = {'d', 'a', 's', 'm'};
        double[] results = new double[opCodes.length];

        // if the length is 0, there is no command-line arguments.
        if (args.length == 0) {
            /*
             * Traditional For loop -
             * Allows incrementing index and access each individual elements across each of the arrays.
             */
            for (int i = 0; i < opCodes.length; i++) {
                results[i] = execute(opCodes[i], leftValues[i], rightValues[i]); // Pass the appropriate members of each of the arrays as arguments
            }

            /*
             * For each loop -
             * The results are all in one array, thus For each loop can be used to loop over each array element.
             *
             * For each loop will still need to have the variable initialized with the proper type to return each
             * array element.
             */
            for (double currentResult : results) {
                System.out.println(currentResult);
            }
        } else if (args.length == 1 && args[0].toLowerCase().equals("interactive"))
            executeInteractively(); // Date support arithmetic
        else if (args.length == 3) {  // If the length is 3, the user passed the correct number of arguments in the command-line
            handleCommandLineArgs(args);
        } else {  // If it's anything else a message will be displayed to the user.
            System.out.println("Please provide an operation code and 2 numeric values");
        }
    }

    /*
        This method will handle input from the user.
     */
    static void executeInteractively() {
        System.out.println("Enter an operation and two numbers");
        /*
            When instantiating Scanner, System.in is passed into Scanner constructor.
            This instance of Scanner will take care of the input given by the user.
         */
        Scanner scanner = new Scanner(System.in);
        /*
            .nextLine() retrieves that input stored from the user as a String.
         */
        String userInput = scanner.nextLine();
        /*
            .split() method accepts an expression to identify what needs to be used to split
            the string into parts.
         */
        String[] parts = userInput.split(" ");
        performFromOperation(parts);
    }

    private static void performFromOperation(String[] parts) {
        char opCode = opCodeFromString(parts[0]);
        if (opCode == 'w') {
            handleWhen(parts);
        } else {
            double lefVal = valueFromWord(parts[1]);
            double rightVal = valueFromWord(parts[2]);
            double result = execute(opCode, lefVal, rightVal);
    //      System.out.println(result); // Previous display result approach.
            displayResult(opCode, lefVal, rightVal, result);
        }
    }

    /*
        Will provide a date and a number of days, so the application can display the date that
        results by adding that number of days to the starting date.
     */
    private static void handleWhen(String[] parts) {
        DateTimeFormatter usDateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate startDate = LocalDate.parse(parts[1], usDateFormat);
        long daysToAdd = (long) valueFromWord(parts[2]);
        LocalDate newDate = startDate.plusDays(daysToAdd);
        String output = String.format("%s plus %d days is %s", startDate.format(usDateFormat), daysToAdd, newDate.format(usDateFormat));
        System.out.println(output);
    }

    static double execute(char opCode, double leftVal, double rightVal) {
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
        return result;
    }

    private static void handleCommandLineArgs(String[] args) {
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

                This line is converting a String representation of a character, into a char representation of a character.
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
//      System.out.println("Result: " + result) ;
        displayResult(opCode, leftVal, rightVal, result);

    }

    /*
        Displaying more informative output to user using StringBuilder & String.format().
     */
    private static void displayResult(char opCode, double leftVal, double rightVal, double result) {
        char symbol = symbolFromOpCode(opCode);
        /*
        StringBuilder builder = new StringBuilder(20);
        builder.append(leftVal);
        builder.append(" ");
        builder.append(symbol);
        builder.append(" ");
        builder.append(rightVal);
        builder.append(" = ");
        builder.append(result);
        String output = builder.toString();
        **/

        String output = String.format("%.3f %c %.3f = %.3f", leftVal, symbol, rightVal, result);
        System.out.println(output); // Keep in mind that StringBuilder it's not itself a String. It needs to be assigned to a String variable.
    }

    /*
        Translate the opCode into the appropriate mathematical symbol.
     */
    private static char symbolFromOpCode(char opCode) {
        // Parallel arrays
        char[] opCodes = {'a', 's', 'm', 'd'};
        char[] symbols = {'+', '-', '*', '/'};
        char symbol = ' ';
        for (int index = 0; index < opCodes.length; index++) {
            if (opCode == opCodes[index]) {
                symbol = symbols[index];
                break;
            }
        }
        return symbol;
    }

    /*
        User can provide the word for the operation it wants to perform, instead of being limited
        using the op-codes 'a', 's', 'm' and 'd'.
     */
    static char opCodeFromString(String operationName) {
        /*
            Retrieve the character at position 0.
         */
        char opCode = operationName.charAt(0);
        return opCode;
    }

    /*
        User can provide numeric words to perform operations.
     */
    static double valueFromWord(String word) {
        String[] numberWords = {
                "zero", "one", "two", "three", "four", "five",
                "six", "seven", "eight", "nine"
        };
        /*
            Local variable will be initially set to -1 instead of 0.
            This starts the local variable with a value that isn't valid. At the end of the for-loop, if
            the value still set to -1, that means that the application never matched any of the words the
            application understands.
         */
//        double value = 0;
        double value = -1;
        /*
            When the word is matched on the conditional statement, break exists the loop and then runs the
            next statement after the loop.
         */
        for (int index = 0; index < numberWords.length; index++) {
            if (word.equals(numberWords[index])) {
                value = index;
                break;
            }
        }
        /*
            Parse number out of the String.
            The primitive wrapper class Double will assist on this task.
         */
        if (value == -1) {
            value = Double.parseDouble(word);
        }
        return value;
    }
}
