package com.geneticprogrammingx.fitnessfunctions;

import com.geneticprogrammingx.interfaces.FitnessFunction;

/**
 * @author Jose Gonzalez
 */
public class AbsoluteDistanceFitnessFunction implements FitnessFunction {
    private static final int ADD = 110;
    private static final int SUB = 111;
    private static final int MUL = 112;
    private static final int DIV = 113;
    private static final int FUNCTION_SET_START = ADD;
    private static final int FUNCTION_SET_END = DIV;
    private double[] preGeneratedRandomConstantValues;
    private int primitiveCounter;
    private int numberOfVariables;
    private int numberOfDataPoints;
    private char[] program;
    private double[][] targets;

    public AbsoluteDistanceFitnessFunction(double[] preGeneratedRandomConstantValues, int numberOfVariables, int numberOfDataPoints, double[][] targets) {
        this.preGeneratedRandomConstantValues = preGeneratedRandomConstantValues;
        this.numberOfVariables = numberOfVariables;
        this.numberOfDataPoints = numberOfDataPoints;
        this.targets = targets;
    }

    @Override
    public double getFitness(char[] program) {
        double result;
        double fitnessValue = 0.0;

        for (int i = 0; i < this.numberOfDataPoints; i++) {
            System.arraycopy(targets[i], 0, this.preGeneratedRandomConstantValues, 0, this.numberOfVariables);
            this.program = program;
            this.primitiveCounter = 0;
            result = run();
            fitnessValue += Math.abs(result - this.targets[i][this.numberOfVariables]);
        }

        return -fitnessValue;
    }

    /**
     * This the interpreter. The interpreter goes gene by gene
     * reads the values. The value are integers that maps to a position
     * in the "program" vector. Then it retrieves the value from the vector and
     * performs an operation. The interpreter identifies whether it is a terminal
     * value or a function. Then runs the operation.
     *
     * @return
     */
    private double run() {
        char primitive = this.program[primitiveCounter++];
        if (primitive < FUNCTION_SET_START) {
            return this.preGeneratedRandomConstantValues[primitive];
        } else {
            switch (primitive) {
                case ADD:
                    return run() + run();
                case SUB:
                    return run() - run();
                case MUL:
                    return run() * run();
                case DIV: {
                    double num = run();
                    double den = run();
                    return Math.abs(den) <= 0.001 ? num : num / den;
                }
                default:
                    return 0.0;
            }
        }
    }

    public int printIndividual(char[] individual, int nodeCount) {
        int a1 = 0;
        int a2;
        if (individual[nodeCount] < FUNCTION_SET_START) {
            if (individual[nodeCount] < this.numberOfVariables) {
                System.out.print("X" + (individual[nodeCount] + 1) + " ");
            } else {
                System.out.print(preGeneratedRandomConstantValues[individual[nodeCount]]);
            }
            return ++nodeCount;
        }
        switch (individual[nodeCount]) {
            case ADD:
                System.out.print("(");
                a1 = printIndividual(individual, ++nodeCount);
                System.out.print(" + ");
                break;
            case SUB:
                System.out.print("(");
                a1 = printIndividual(individual, ++nodeCount);
                System.out.print(" - ");
                break;
            case MUL:
                System.out.print("(");
                a1 = printIndividual(individual, ++nodeCount);
                System.out.print(" * ");
                break;
            case DIV:
                System.out.print("(");
                a1 = printIndividual(individual, ++nodeCount);
                System.out.print(" / ");
                break;
            default:
        }
        a2 = printIndividual(individual, a1);
        System.out.print(")");
        return a2;
    }

}
