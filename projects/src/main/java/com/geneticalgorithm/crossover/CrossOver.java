package com.geneticalgorithm.crossover;

import com.geneticalgorithm.beans.Individual;

/**
 * CrossOver Interface
 *
 * @author Jose Gonzalez
 */
public interface CrossOver {

    public Individual crossOver(Individual father, Individual mother);
}
