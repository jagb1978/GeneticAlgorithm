package com.geneticalgorithm.beans;


import com.geneticalgorithm.interfaces.FitnessCalculator;

/**
 * Manages the populations
 *
 * @author Jose Gonzalez
 */
public class Population {
    private Individual[] individualsArray;
    private FitnessCalculator fitnessCalculator;
    private int individualsNumberOfGenes;

    public Population(int populationSize,int individualsNumberOfGenes ,boolean initialise, FitnessCalculator fitnessCalculator) {
        this.individualsArray = new Individual[populationSize];
        this.individualsNumberOfGenes = individualsNumberOfGenes;
        this.fitnessCalculator = fitnessCalculator;
        int individualNumber = 0;

        if (initialise) {
            for (Individual individual : this.individualsArray) {
                individual = new Individual( this.individualsNumberOfGenes ,this.fitnessCalculator);
                individual.generateIndividual();
                this.individualsArray[individualNumber] = individual;
                individualNumber++;
            }
        }
    }

    public Individual getFittest() {
        Individual fittest = this.individualsArray[0];
        for (Individual individual : this.individualsArray) {
            if (fittest.getFitness() <= individual.getFitness()) {
                fittest = individual;
            }
        }
        return fittest;
    }

    public Individual[] getIndividualsArray() {
        return individualsArray;
    }

    public FitnessCalculator getFitnessCalculator() {
        return fitnessCalculator;
    }

    public int getIndividualsNumberOfGenes() {
        return individualsNumberOfGenes;
    }

}
