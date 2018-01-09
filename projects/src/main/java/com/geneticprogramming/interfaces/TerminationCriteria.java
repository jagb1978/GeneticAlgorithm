package com.geneticprogramming.interfaces;

import com.geneticprogramming.beans.PopulationManager;

/**
 * Termination Criteria Interface
 *
 * @author Jose Gonzalez
 */
public interface TerminationCriteria {
    boolean hasTerminationCriteriaBeingMet(PopulationManager populationManager);
}
