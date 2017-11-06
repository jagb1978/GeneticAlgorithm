package com.geneticprogrammingx.beans;

import com.geneticprogrammingx.utils.NodeManager;

import java.util.Random;

/**
 *
 * @author Jose Gonzalez
 */
public class PopulationManager {
    private NodeManager nodeManager;
    private Random random = new Random();
    private int maximumLength;

    public PopulationManager(NodeManager nodeManager, int maximumLength){
        this.nodeManager = nodeManager;
        this.maximumLength =  maximumLength;
    }


    private int grow(char[] individual, int position, int max, int depth) {
        char primitive = (char) this.random.nextInt(2);
        int oneChild;

        if (position >= max) {
            return -1;
        }

        if (position == 0) {
            primitive = 1;
        }

        if (primitive == 0 || depth == 0) {
            primitive = (char) this.nodeManager.getRandomPositionOfVariableOrConstant();
            individual[position] = primitive;
            return (position + 1);
        } else {
            primitive = (char) this.nodeManager.getRandomFunction();
            individual[position] = primitive;
            oneChild = grow(individual, position + 1, max, depth - 1);

            return oneChild < 0 ? -1 : grow(individual, oneChild, max, depth - 1);
        }
    }

    /**
     * Creates a Random Individual from scratch with a pre-difined depth
     *
     * @param depth
     * @return
     */
    private char[] createRandomIndividual(int depth) {
        char[] individual = new char[this.maximumLength];
        int nodeLength = this.grow(individual, 0, this.maximumLength, depth);

        while (nodeLength < 0) {
            nodeLength = this.grow(individual, 0, this.maximumLength, depth);
        }

        return individual;
    }


//    private char[][] createRandomPopulation(int populationSize, int depth, double[] fitness) {
//        char[][] randomPopulation = new char[populationSize][];
//
//        for (int i = 0; i < populationSize; i++) {
//            randomPopulation[i] = this.createRandomIndividual(depth);
//            fitness[i] = this.fitnessFunction(randomPopulation[i]);
//        }
//
//        return randomPopulation;
//    }


}
