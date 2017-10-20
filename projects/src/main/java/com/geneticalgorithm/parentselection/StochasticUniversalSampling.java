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
        Population selectedIndividuals = new Population.Builder()
                .populationSize(this.numberOfIndividualsToChoose)
                .geneMap(population.getGeneMap())
                .initialise(true)
                .fitnessCalculator(population.getFitnessCalculator())
                .build();


        double totalPopulationFitness = Arrays.stream(population.getIndividualsArray()).mapToDouble(Individual::getFitness).sum();
        Arrays.sort(population.getIndividualsArray(), new SortIndividualByFitness());
        double distanceBetweenPointers = totalPopulationFitness / this.numberOfIndividualsToChoose;

        int index = 0;
        double cumulativeSumOfFitness = population.getIndividualsArray()[index].getFitness();
        double randomNumber = ThreadLocalRandom.current().nextDouble(0, distanceBetweenPointers);


        for (int i = 0; i < this.numberOfIndividualsToChoose; i++) {
            double pointer = randomNumber + i * distanceBetweenPointers;
            if (cumulativeSumOfFitness < pointer) {
                for (++index; index < population.getIndividualsArray().length; index++) {
                    cumulativeSumOfFitness += population.getIndividualsArray()[index].getFitness();
                    if (cumulativeSumOfFitness >= pointer) {
                        selectedIndividuals.getIndividualsArray()[i] = population.getIndividualsArray()[index];
                        break;
                    }
                }
            } else {
                selectedIndividuals.getIndividualsArray()[i] = population.getIndividualsArray()[index];
            }
        }

        return selectedIndividuals;
    }
}
