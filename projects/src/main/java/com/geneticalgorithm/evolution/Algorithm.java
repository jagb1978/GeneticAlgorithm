package com.geneticalgorithm.evolution;

import com.geneticalgorithm.beans.Individual;
import com.geneticalgorithm.beans.Population;
import com.geneticalgorithm.interfaces.CrossOver;
import com.geneticalgorithm.interfaces.Mutation;
import com.geneticalgorithm.interfaces.ParentSelection;


/**
 * Implement the GA that evolves the individuals
 *
 * @author Jose Gonzalez
 */
public class Algorithm {
    private ParentSelection parentSelection;
    private CrossOver crossOver;
    private Mutation mutation;
    private boolean elitism;

    public Algorithm(ParentSelection parentSelection, CrossOver crossOver,  Mutation mutation, boolean elitism){
        this.parentSelection = parentSelection;
        this.crossOver = crossOver;
        this.mutation = mutation;
        this.elitism = elitism;
    }

    /**
     * Evolves the population with following steps
     * 1. If Elitism is true, it makes sure to always keep the the best individual of the previous generation
     * <p>
     * 2. Performs crossOver by selecting the two winners parents from two different tournaments, it crosses them over
     * and generate a new individual for the new generation. Every individual of the new generation, up to this point,
     * is generated that way, except the fittest if elitism = true.
     * <p>
     * 3. It randomly selects individual (previously crossed over in the last step) to be mutated
     *
     * @return new evolved generation (population)
     */
    public Population evolvePopulation(Population population) {
        Population newPopulation = new Population.Builder()
                .populationSize(population.getIndividualsArray().length)
                .initialise(false)
                .fitnessCalculator(population.getFitnessCalculator())
                .geneMap(population.getGeneMap())
                .build();
        // Keep our best individual
        if (this.elitism) newPopulation.getIndividualsArray()[0] = population.getFittest();
        // the offsets excludes the fittest individual from evolving it elitism is true
        int elitismOffset = this.elitism ? 1 : 0;
        // crossover
        for (int i = elitismOffset; i < population.getIndividualsArray().length; i++) {
            Individual father = this.parentSelection.selection(population);
            Individual mother = this.parentSelection.selection(population);
            Individual offspring = this.crossOver.crossOver(father, mother);

            newPopulation.getIndividualsArray()[i] = offspring;
        }
        // Mutate population
        for (int i = elitismOffset; i < newPopulation.getIndividualsArray().length; i++) {
            this.mutation.mutate(newPopulation.getIndividualsArray()[i]);
        }
        return newPopulation;
    }
}
