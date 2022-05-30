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
        /*
            Declare an array of MathEquations.
            This array is an array of MathEquation references, we're not creating 4 instances of the
            MathEquation class, instead 4 references of type MathEquation are being created. Each element
            within this array needs to be explicitly create an instance of the MathEquation class.
         */
        MathEquation[] equations = new MathEquation[4];

        // Each of the elements in the array are referencing new instance of the MathEquations class.
        equations[0]  = new MathEquation(100.0, 50.0, 'd');
        equations[1] = new MathEquation(25.0, 92.0, 'a');
        equations[2] = new MathEquation(225.0, 17.0, 's');
        equations[3] = new MathEquation(11.0, 3.0, 'm');

        for (MathEquation equation : equations) {
            equation.execute();
            System.out.println("Result = " + equation.result);
        }
    }
    /*
        The create method will initialize each of the elements in the equations array, creating a
        new instance of the MathEquations class and then returning back a reference to that newly
        created MathEquation.
     */

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
