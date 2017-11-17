package com.geneticprogrammingx.interfaces;

import com.geneticprogrammingx.beans.Individual;

/**
 * Fitness Function Interface
 *
 * @author Jose Gonzalez
 */
public interface FitnessFunction {

    default double getFitness(char[] program){return 0; };
    default double getFitness(Individual individual) {return 0; };
    default int printIndividual(char[] individual, int nodeCount){return 0; }
    default int printIndividual(Individual individual, int nodeCount) {return 0; }
}
