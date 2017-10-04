package com.geneticalgorithm.utils;

import com.geneticalgorithm.interfaces.FitnessCalculator;
import com.geneticalgorithm.beans.Individual;

/**
 * Performs the fitness Calculation
 *
 * @author Jose Gonzalez
 */
public class FitnessCalc implements FitnessCalculator{

    static byte[] solution = new byte[64];

    public static void setSolution(byte[] newSolution) {
        solution = newSolution;
    }

    /**
     * Forces the solution to be a byte array of 0s and 1s
     * @param newSolution
     */
    public static void setSolution(String newSolution) {
        solution = new byte[newSolution.length()];

        for (int i = 0; i < newSolution.length(); i++) {
            String character = newSolution.substring(i, i + 1);
            if (character.contains("0") || character.contains("1")) {
                solution[i] = Byte.parseByte(character);
            } else {
                solution[i] = 0;
            }
        }
    }

    /**
     * The fitness function increases if the individual and gene matches
     * that in type and position the solution
     * @return the fitness value of an individual
    */
    public  static int getFitness(Individual individual) {
        int fitness = 0;
        for (int i = 0; i < individual.size() && i < solution.length; i++) {
            if (individual.getGene(i) == solution[i]) {
                fitness++;
            }
        }
        return fitness;
    }

    /**
     * The fitness function increases if the individual and gene matches
     * that in type and position the solution
     * @return the fitness value of an individual
    */
    public int getFitnessValue(Individual individual) {
        int fitness = 0;
        for (int i = 0; i < individual.size() && i < solution.length; i++) {
            if (individual.getGene(i) == solution[i]) {
                fitness++;
            }
        }
        return fitness;
    }

    /**
     * The max possible fitness is the one that occurs when all the
     * genes in an individual matches the pre known solution. Given the
     * fitness function structure, this equivalent to the solution length
     *
     * @return Maximum Possible Fitness
     */
    public  static int getMaxFitness() {
        return solution.length;
    }
}
