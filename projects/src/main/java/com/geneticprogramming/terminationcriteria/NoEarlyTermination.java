package com.geneticprogramming.terminationcriteria;

import com.geneticprogramming.beans.PopulationManager;
import com.geneticprogramming.interfaces.TerminationCriteria;

/**
 * No Early Termination Criteria
 *
 * @author Jose Gonzalez
 */
public class NoEarlyTermination implements TerminationCriteria{

    @Override
    public boolean hasTerminationCriteriaBeingMet(PopulationManager populationManager) {
        return false;
    }
}
