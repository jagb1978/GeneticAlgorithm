package com.ga.simpleGA.interfaces;

import com.ga.simpleGA.beans.Individual;

/**
 * Fitness Calculator Interface
 * @author Jose Gonzalez
 *
 */
public interface FitnessCalculator {
    int getFitnessValue(Individual individual);

}
