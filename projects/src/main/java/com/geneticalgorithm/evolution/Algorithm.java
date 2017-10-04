package com.geneticalgorithm.evolution;

import com.geneticalgorithm.beans.Individual;
import com.geneticalgorithm.beans.Population;


/**
 * Implement the GA that evolves the individuals
 *
 * @author Jose Gonzalez
 */
public class Algorithm {

    private double uniformRate = 0.5;
    private double mutationRate = 0.015;
    private int tournamentSize = 5;
    private boolean elitism = true;

    public Algorithm(){}

    public Algorithm (double uniformRate, double mutationRate, int tournamentSize, boolean elitism){
        this.uniformRate = uniformRate;
        this.mutationRate = mutationRate;
        this.tournamentSize = tournamentSize;
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
        Population newPopulation = new Population(population.getIndividuals().length, false);

        // Keep our best individual
        if (this.elitism) newPopulation.getIndividuals()[0] = population.getFittest();

        // the offsets excludes the fittest individual from evolving it elitism is true
        int elitismOffset = this.elitism ? 1 : 0;

        // crossover
        for (int i = elitismOffset; i < population.getIndividuals().length; i++) {
            Individual father = this.tournamentSelection(population);
            Individual mother = this.tournamentSelection(population);
            Individual offspring = this.crossover(father, mother);

            newPopulation.getIndividuals()[i] = offspring;
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.getIndividuals().length; i++) {
            this.mutate(newPopulation.getIndividuals()[i]);
        }

        return newPopulation;
    }


    /**
     * Method that mutates individuals
     * 1. It firstly decides whether to mutate given the mutation rate parameter or probability
     * 2. It then generates a gene value that is either 0 or 1
     * 3. Sets the new gene value in the individual
     *
     * @param individual
     */
    private void mutate(Individual individual) {

        for (int i = 0; i < individual.size(); i++) {
            if (Math.random() <= this.mutationRate) {
                byte gene = (byte) Math.round(Math.random());
                individual.setGene(i, gene);
            }
        }
    }

    /**
     * Method that crosses over two individuals
     * It randomly selects one individual and adds the gene to the
     * new crossedOvered individual. Therefore the new individual
     * gets genes from both parents
     *
     * @param father
     * @param mother
     * @return newCrossedOverIndividual
     */
    private Individual crossover(Individual father, Individual mother) {
        Individual offspring = new Individual();

        for (int i = 0; i < father.size(); i++) {
            if (Math.random() <= this.uniformRate) {
                offspring.setGene(i, father.getGene(i));
            } else {
                offspring.setGene(i, mother.getGene(i));
            }
        }

        return offspring;
    }

    /**
     * Randomly selects an individual from the population
     * for a place in the tournament. Afterwards it selects a winner.
     *
     * @param population
     * @return fittest Individual in the Tournament
     */
    private Individual tournamentSelection(Population population) {
        Population tournament = new Population(this.tournamentSize, false);

        for (int i = 0; i < this.tournamentSize; i++) {
            int randomId = (int) (Math.random() * population.getIndividuals().length);
            tournament.getIndividuals()[i] = population.getIndividuals()[randomId];
        }

        return tournament.getFittest();
    }

}
