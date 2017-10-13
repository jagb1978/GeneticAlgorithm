package com.geneticalgorithm.beans;

import com.geneticalgorithm.interfaces.FitnessCalculator;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Manages the populations
 *
 * @author Jose Gonzalez
 */
public class Population {
    private Individual[] individualsArray;
    private FitnessCalculator fitnessCalculator;
    private int individualsNumberOfGenes;
    private Individual fittestIndividual;

    public static class Builder {
        private int populationSize;
        private FitnessCalculator fitnessCalculator;
        private int individualsNumberOfGenes;
        private boolean initialise;

        public Builder populationSize(int populationSize) {
            this.populationSize = populationSize;
            return this;
        }

        public Builder fitnessCalculator(FitnessCalculator fitnessCalculator) {
            this.fitnessCalculator = fitnessCalculator;
            return this;
        }

        public Builder individualsNumberOfGenes(int individualsNumberOfGenes) {
            this.individualsNumberOfGenes = individualsNumberOfGenes;
            return this;
        }

        public Builder initialise(boolean initialise) {
            this.initialise = initialise;
            return this;
        }

        public Population build() {
            return new Population(this);
        }

    }

    private Population(Builder builder){
        this.individualsArray =  new Individual[builder.populationSize];
        this.individualsNumberOfGenes = builder.individualsNumberOfGenes;
        this.fitnessCalculator = builder.fitnessCalculator;
        int individualNumber = 0;

        if (builder.initialise) {
            for (Individual individual : this.individualsArray) {
                individual = new Individual( this.individualsNumberOfGenes ,this.fitnessCalculator);
                individual.generateIndividual();
                this.individualsArray[individualNumber] = individual;
                individualNumber++;
            }
        }
    }

    public Individual getFittest() {
        if(this.fittestIndividual == null) {
            this.fittestIndividual = Arrays.stream(this.individualsArray).max(Comparator.comparing(Individual::getFitness)).orElse(null);
        }
        return this.fittestIndividual;
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
