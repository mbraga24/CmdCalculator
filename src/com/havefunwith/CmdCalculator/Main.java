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
        System.out.println("\n===================================================");
        System.out.println("It's the command line calculator project.");
        System.out.println("===================================================\n");
        performCalculations();
    }

    private static void performCalculations() {
        double[] leftValues = {100.0, 25.0, 225.0, 11.0};
        double[] rightValues = {50.0, 92.0, 17.0, 3.0};
        char[] opCodes = {'d', 'a', 's', 'm'};
        double[] results = new double[opCodes.length];

        /*
            Declare an array of MathEquations.
            This array is an array of MathEquation references, we're not creating 4 instances of the
            MathEquation class, instead 4 references of type MathEquation are being created. Each element
            within this array needs to be explicitly create an instance of the MathEquation class.
         */
        MathEquation[] equations = new MathEquation[4];

        // Each of the elements in the array are referencing new instance of the MathEquations class.
        equations[0] = create(100.0, 50.0, 'd');
        equations[1] = create(25.0, 92.0, 'a');
        equations[2] = create(225.0, 17.0, 's');
        equations[3] = create(11.0, 3.0, 'm');

        // Each of the fields within MathEquation have their appropriate values.
        // equations[0] = new MathEquation();
        // equations[0].leftValues = 100.0;
        // equations[0].rightValues = 50.0;
        // equations[0].opCodes = 'd';

        equations[0] = create(100.0, 50.0, 'd');

        if (args.length == 0) {

            for (int i = 0; i < opCodes.length; i++) {
                results[i] = execute(opCodes[i], leftValues[i], rightValues[i]); // Pass the appropriate members of each of the arrays as arguments
            }

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
        The create method will initialize each of the elements in the equations array, creating a
        new instance of the MathEquations class and then returning back a reference to that newly
        created MathEquation.
     */
    private static MathEquation create(double leftVal, double rightVal, char opCode) {
        MathEquation equation = new MathEquation();
        equation.leftVal = leftVal;
        equation.rightVal = rightVal;
        equation.opCodes = opCode;

        return equation;
    }

    /*
        This method will handle input from the user.
     */
    static void executeInteractively() {
        System.out.println("Enter an operation and two numbers");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
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
        char opCode = args[0].charAt(0);
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
        String output = String.format("%.3f %c %.3f = %.3f", leftVal, symbol, rightVal, result);
        System.out.println(output); // Keep in mind that StringBuilder it's not itself a String. It needs to be assigned to a String variable.
    }

    /*
        Translate the opCode into the appropriate mathematical symbol.
     */
    private static char symbolFromOpCode(char opCode) {
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
        double value = -1;
        for (int index = 0; index < numberWords.length; index++) {
            if (word.equals(numberWords[index])) {
                value = index;
                break;
            }
        }
        if (value == -1) {
            value = Double.parseDouble(word);
        }
        return value;
    }
}
