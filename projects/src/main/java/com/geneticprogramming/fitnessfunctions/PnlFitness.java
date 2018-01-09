package com.geneticprogramming.fitnessfunctions;

import com.geneticprogramming.interfaces.FitnessFunction;
import com.geneticprogramming.utils.MathUtils;
import com.trading.beans.DataPoint;
import com.trading.indicators.Ema;
import com.trading.interfaces.Indicator;
import java.util.List;

/**
 * Fitness function based on Pnl
 *
 * @author Jose Gonzalez
 */
public class PnlFitness implements FitnessFunction {
    private static final int HIGHER_THAN = 7;
    private static final int LESS_THAN = 8;
    private static final int EQUAL = 9;
    private static final int AND = 10;
    private static final int OR = 11;
    private static final int FUNCTION_SET_START = HIGHER_THAN;
    private double[] indicatorValues;
    private int primitiveCounter;
    private int numberOfDataPoints;
    private char[] individualProgram;
    private List<DataPoint> dataPointList;
    private Indicator ema0;
    private Indicator ema1;
    private Indicator ema2;
    private Indicator ema3;
    private Indicator ema4;
    private Indicator ema5;

    public PnlFitness(List<DataPoint> dataPointList) {
        this.dataPointList = dataPointList;
        this.numberOfDataPoints = this.dataPointList.size();
        this.ema0 = new Ema(10);
        this.ema1 = new Ema(20);
        this.ema2 = new Ema(30);
        this.ema3 = new Ema(100);
        this.ema4 = new Ema(200);
        this.ema5 = new Ema(400);
        this.indicatorValues = new double[6];
    }

    private void updateIndicatorValues(DataPoint dataPoint) {
        this.indicatorValues[0] = this.ema0.getValue(dataPoint);
        this.indicatorValues[1] = this.ema1.getValue(dataPoint);
        this.indicatorValues[2] = this.ema2.getValue(dataPoint);
        this.indicatorValues[3] = this.ema3.getValue(dataPoint);
        this.indicatorValues[4] = this.ema4.getValue(dataPoint);
        this.indicatorValues[5] = this.ema5.getValue(dataPoint);
    }

    @Override
    public double getFitness(char[] individual) {
        this.primitiveCounter = 0;
        double money = 10000;
        double position = 0;
        double signal;
        this.individualProgram = individual;
        for (int i = 0; i < this.numberOfDataPoints; i++) {
            this.updateIndicatorValues(this.dataPointList.get(i));
            signal = evaluate();
            if (signal > 0 && MathUtils.almostZero(position)) {
                position = Math.round(money / this.dataPointList.get(i).getPrice());
                money = money - position * this.dataPointList.get(i).getPrice();
            } else if (signal < 0 && position > 0) {
                money = money + position * this.dataPointList.get(i).getPrice();
                position = 0;
            }

            this.primitiveCounter = 0;
        }

        if(individual.length > 14){
            return -100000.0;
        }

        return (money - 10000) / 10000;
    }

    private double evaluate() {
        return MathUtils.floatsAlmostEqual(run(), 1.0) ? 1.0 : -1.0;
    }

    /**
     * This the interpreter. The interpreter goes gene by gene
     * reads the values. The value are integers that maps to a position
     * in the "program" vector. Then it retrieves the value from the vector and
     * performs an operation. The interpreter identifies whether it is a terminal
     * value or a function. Then runs the operation.
     *
     * @return value that will be used to determine action signal
     */
    private Double run() {
        char primitive = this.individualProgram[primitiveCounter++] ;
        if (primitive < FUNCTION_SET_START) {
            return this.indicatorValues[primitive];
        } else {
            switch (primitive) {
                case HIGHER_THAN:
                    return run() > run() ? 1.0 : 0.0;
                case LESS_THAN:
                    return run() < run() ? 1.0 : 0.0;
                case EQUAL:
                    return MathUtils.floatsAlmostEqual(run(), run()) ? 1.0 : 0.0;
                case AND:
                    return MathUtils.floatsAlmostEqual(run(), 1.0) && MathUtils.floatsAlmostEqual(run(), 1.0) ? 1.0 : 0.0;
                case OR:
                    return MathUtils.floatsAlmostEqual(run(), 1.0) || MathUtils.floatsAlmostEqual(run(), 1.0) ? 1.0 : 0.0;
                default:
                    return null;
            }
        }
    }

    @Override
    public int printIndividual(char[] individual, int nodeCount) {
        StringBuilder genes= new StringBuilder();
        for (char c:individual) {
            genes.append(Integer.valueOf(c)+" ");
        }

 //       System.out.print(genes.toString());

        int a1 = 0;
        int a2;
        if (individual[nodeCount] < FUNCTION_SET_START) {
            System.out.print("EMA" + (Integer.valueOf(individual[nodeCount]) + " "));
            return ++nodeCount;
        }
        switch (individual[nodeCount]) {
            case HIGHER_THAN:
                System.out.print("(");
                a1 = printIndividual(individual, ++nodeCount);
                System.out.print(" > ");
                break;
            case LESS_THAN:
                System.out.print("(");
                a1 = printIndividual(individual, ++nodeCount);
                System.out.print(" < ");
                break;
            case EQUAL:
                System.out.print("(");
                a1 = printIndividual(individual, ++nodeCount);
                System.out.print(" = ");
                break;
            case AND:
                System.out.print("(");
                a1 = printIndividual(individual, ++nodeCount);
                System.out.print(" AND ");
                break;
            case OR:
                System.out.print("(");
                a1 = printIndividual(individual, ++nodeCount);
                System.out.print(" OR ");
                break;
            default:
        }
        a2 = printIndividual(individual, a1);
        System.out.print(")");
        return a2;
    }

}
