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
    private int tournamentSize;  //Number of individuals from the population competing in the tournament

    public TournamentSelection(int tournamentSize){
        this.tournamentSize = tournamentSize;
    }

    /**
     * Randomly selects an individual from the population
     * for a place in the tournament. Afterwards it selects a winner.
     *
     * @param population
     * @return fittest Individual in the Tournament
     */
    @Override
    public Individual selection(Population population) {
        Population tournament = new Population(this.tournamentSize,population.getIndividualsNumberOfGenes() ,false, population.getFitnessCalculator());
        for (int i = 0; i < this.tournamentSize; i++) {
            int randomId = ThreadLocalRandom.current().nextInt(0, population.getIndividualsArray().length);
            tournament.getIndividualsArray()[i] = population.getIndividualsArray()[randomId];
        }
        return tournament.getFittest();
    }

}
