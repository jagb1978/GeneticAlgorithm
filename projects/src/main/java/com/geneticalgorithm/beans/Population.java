package com.geneticalgorithm.beans;

import com.geneticalgorithm.interfaces.FitnessCalculator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

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
    private Map<Integer, GeneType> geneMap;

    public static class Builder {
        private int populationSize;
        private FitnessCalculator fitnessCalculator;
        private int individualsNumberOfGenes;
        private boolean initialise;
        private Map<Integer, GeneType> geneMap;

        public Builder populationSize(int populationSize) {
            this.populationSize = populationSize;
            return this;
        }

        public Builder geneMap( Map<Integer, GeneType> geneMap) {
            this.geneMap = geneMap;
            return this;
        }


        public Builder fitnessCalculator(FitnessCalculator fitnessCalculator) {
            this.fitnessCalculator = fitnessCalculator;
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
        this.fitnessCalculator = builder.fitnessCalculator;
        this.geneMap = builder.geneMap;
        this.individualsNumberOfGenes = this.geneMap.size();
        int individualNumber = 0;

        if(builder.initialise) {
            for (Individual individual : this.individualsArray) {
                individual = new Individual(this.individualsNumberOfGenes, this.fitnessCalculator, this.geneMap);
                individual.generateIndividual(this.geneMap);
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

    public Map<Integer, GeneType> getGeneMap() {
        return geneMap;
    }

}
