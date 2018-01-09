package com.geneticprogramming.terminationcriteria;

import com.geneticprogramming.beans.PopulationManager;
import com.geneticprogramming.interfaces.TerminationCriteria;

/**
 * Terminate the simulation if the best individual fitness
 * value is zero
 *
 * @author Jose Gonzalez
 */
public class MinimumDistance implements TerminationCriteria {

    @Override
    public boolean hasTerminationCriteriaBeingMet(PopulationManager populationManager) {
        return populationManager.getFitnessBestPopulation() > -1e-5;
    }
}
