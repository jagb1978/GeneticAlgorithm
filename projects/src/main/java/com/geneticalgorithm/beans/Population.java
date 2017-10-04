package com.geneticalgorithm.beans;


/**
 * Manages the populations
 *
 * @author Jose Gonzalez
 */
public class Population {
    private Individual[] individuals;


    public Population(int populationSize, boolean initialise) {
        this.individuals = new Individual[populationSize];

        int individualNumber=0;
        // Initialise population
        if (initialise) {
            for (Individual individual : this.individuals) {
                individual = new Individual();
                individual.generateIndividual();
                this.individuals[individualNumber]=individual;
                individualNumber++;
            }
        }
    }

    public Individual getFittest() {
        Individual fittest = this.individuals[0];
        for (Individual individual : this.individuals) {
            if (fittest.getFitness() <= individual.getFitness()) {
                fittest = individual;
            }
        }
        return fittest;
    }

    public Individual[] getIndividuals() {
        return individuals;
    }


}
