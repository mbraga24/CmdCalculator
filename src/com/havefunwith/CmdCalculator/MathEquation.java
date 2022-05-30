package com.havefunwith.CmdCalculator;

public class MathEquation {

    // Field Initializers
    double leftVal;
    double rightVal;
    char opCode;
    double result;

    // Default Constructor
    public MathEquation() {}

    public MathEquation(char opCode) {
        this.opCode = opCode;
    }

    public MathEquation(double leftVal, double rightVal, char opCode) {
        // Chained Constructor
        this(opCode);
        this.leftVal = leftVal;
        this.rightVal = rightVal;
    }

    // Class Method
    void execute() {
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
    }

    // Accessors & Modifiers

    // Accessor
    public double getLeftVal() {
        return this.leftVal;
    }

    // Modifier
    public void setLeftVal(double leftVal) {
        this.leftVal = leftVal;
    }

    // Accessor
    public double getRightVal() {
        return this.rightVal;
    }

    // Modifier
    public void setRightVal(double rightVal) {
        this.rightVal = rightVal;
    }

}
