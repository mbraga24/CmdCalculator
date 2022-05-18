package com.havefunwith.CmdCalculator;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n===================================================");
        System.out.println("Hey, you! It's the command line calculator project.");
        System.out.println("===================================================\n");

        double value1 = 100.0;
        double value2 = 50.0;
        double result = 0.0;
        char opCode = 'd';

        switch(opCode) {

            case 'a':
                result = value1 + value2;
                break;
            case 's':
                result = value1 - value2;
                break;
            case 'm':
                result = value1 * value2;
                break;
            case 'd':
                result = value2 != 0 ? value1 / value2 : 0.0;
                break;
            default:
                System.out.println("Invalid opCode: " + opCode);
                result = 0.0;
                break;
        }
    System.out.println(result);

    }
}
