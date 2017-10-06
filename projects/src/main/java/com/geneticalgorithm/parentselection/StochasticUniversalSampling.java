package com.geneticalgorithm.parentselection;

import com.geneticalgorithm.beans.Individual;
import com.geneticalgorithm.beans.Population;
import com.geneticalgorithm.utils.SortIndividualByFitness;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Implements Stochastic Universal Sampling. It selects an n
 * number of individuals from the population based on a single "spin wheel".
 * The individual with highest fitness is always chosen.
 * @author Jose Gonzalez
 */
public class StochasticUniversalSampling {
    private int numberOfIndividualsToChoose;

    public StochasticUniversalSampling(int numberOfIndividualsToChoose) {
        this.numberOfIndividualsToChoose = numberOfIndividualsToChoose;
    }

    private Population selectParent(Population population) {
        Population selectedIndividuals = new Population(this.numberOfIndividualsToChoose, true);
        double totalPopulationFitness = Arrays.stream(population.getIndividuals()).mapToDouble(Individual::getFitness).sum();
        Arrays.sort(population.getIndividuals(), new SortIndividualByFitness());
        double distanceBetweenPointers = totalPopulationFitness / this.numberOfIndividualsToChoose;

        int index = 0;
        double cumulativeSumOfFitness = population.getIndividuals()[index].getFitness();
        double randomNumber = ThreadLocalRandom.current().nextDouble(0, distanceBetweenPointers);


        for (int i = 0; i < this.numberOfIndividualsToChoose; i++) {
            double pointer = randomNumber + i * distanceBetweenPointers;
            if (cumulativeSumOfFitness < pointer) {
                for (++index; index < population.getIndividuals().length; index++) {
                    cumulativeSumOfFitness += population.getIndividuals()[index].getFitness();
                    if (cumulativeSumOfFitness >= pointer) {
                        selectedIndividuals.getIndividuals()[i] = population.getIndividuals()[index];
                        break;
                    }
                }
            } else {
                selectedIndividuals.getIndividuals()[i] = population.getIndividuals()[index];
            }
        }

        return selectedIndividuals;
    }
}
