package com.geneticalgorithm.interfaces;

import com.geneticalgorithm.beans.Individual;

/**
 * Fitness Calculator Interface
 * @author Jose Gonzalez
 *
 */
public interface FitnessCalculator {
    int getFitnessValue(Individual individual);

}
