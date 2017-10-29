package com.geneticalgorithm.parentselection;

import com.geneticalgorithm.beans.Individual;
import com.geneticalgorithm.beans.Population;
import com.geneticalgorithm.interfaces.ParentSelection;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Implements Tournament Selection
 * @author Jose Gonzalez
 */
public class TournamentSelection implements ParentSelection {
    private int tournamentSize;  //Number of individuals from the population competing in the selection

    public TournamentSelection(int tournamentSize){
        this.tournamentSize = tournamentSize;
    }

    /**
     * Randomly selects an individual from the population
     * for a place in the selection. Afterwards it selects a winner.
     *
     * @param population
     * @return fittest Individual in the Tournament
     */
    @Override
    public Individual selection(Population population) {
        Population tournament = new Population.Builder()
                .populationSize(this.tournamentSize)
                .initialise(false)
                .geneMap(population.getGeneMap())
                .fitnessCalculator(population.getFitnessCalculator())
                .build();

        for (int i = 0; i < this.tournamentSize; i++) {
            int randomId = ThreadLocalRandom.current().nextInt(0, population.getIndividualsArray().length);
            tournament.getIndividualsArray()[i] = population.getIndividualsArray()[randomId];
        }
        return tournament.getFittest();
    }

}
