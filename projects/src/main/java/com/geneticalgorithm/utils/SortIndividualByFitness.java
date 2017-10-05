package com.geneticalgorithm.utils;

import com.geneticalgorithm.beans.Individual;
import java.util.Comparator;

/**
 *
 * @author Jose Gonzalez
 */
public class SortIndividualByFitness implements Comparator<Individual> {
    @Override
    public int compare(Individual individual1, Individual individual2) {
        long a = individual1.getFitness();
        long b = individual2.getFitness();
        return ((Long)(a -b)).intValue();
    }
}
