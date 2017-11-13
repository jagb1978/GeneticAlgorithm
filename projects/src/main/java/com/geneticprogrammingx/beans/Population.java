package com.geneticprogrammingx.beans;

import com.geneticprogrammingx.exceptions.NoIndividualsException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jose Gonzalez
 */
public class Population {
    private List<Individual> individualsList = new ArrayList<>();

    public void add(Individual individual) {
        this.individualsList.add(individual);
        this.individualsList.get(this.individualsList.size()-1).setPositionInPopulation(this.individualsList.size()-1);
    }

    public Individual fittestIndividual() throws NoIndividualsException {
        if (!this.individualsList.isEmpty()) {
            Individual fittestIndividual = this.individualsList.get(0);
            double maxFitness = 0;
            for (Individual individual : this.individualsList) {
                if (individual.getIndividualFitness() > maxFitness) {
                    fittestIndividual = individual;
                }
            }
            return fittestIndividual;
        } else {
            throw new NoIndividualsException("There are no individuals in the population");
        }
    }

}
