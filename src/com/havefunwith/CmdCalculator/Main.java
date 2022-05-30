package com.havefunwith.CmdCalculator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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

        /*
            Encapsulating the details of establishing the initial state of the class within the class itself.
         */
        equations[0]  = new MathEquation(100.0, 50.0, 'd');
        equations[1] = new MathEquation(25.0, 92.0, 'a');
        equations[2] = new MathEquation(225.0, 17.0, 's');
        equations[3] = new MathEquation(11.0, 3.0, 'm');

        for (MathEquation equation : equations) {
            equation.execute();
            System.out.println("Result = " + equation.result);
        }
    }
}
