package com.geneticprogramming.beans;


import com.geneticprogramming.interfaces.TerminationCriteria;

/**
 * @author Jose Gonzalez
 */
public class SimulationInfo {
    private  int maximumLength ;
    private  int populationSize ;
    private  int initialProgramMaxDepth ;
    private  int maxNumberOfGenerations;
    private  int tournamentSize;
    private  double mutationProbability;
    private  double crossoverProbability;
    private TerminationCriteria terminationCriteria;

    public static class Builder {
        private  int maximumLength ;
        private  int populationSize;
        private  int initialProgramMaxDepth;
        private  int maxNumberOfGenerations ;
        private  int tournamentSize;
        private  double mutationProbability;
        private  double crossoverProbability;
        private TerminationCriteria terminationCriteria;

        public Builder populationSize(int populationSize) {
            this.populationSize = populationSize;
            return this;
        }

        public Builder maximumLength(  int maximumLength) {
            this.maximumLength = maximumLength;
            return this;
        }


        public Builder initialProgramMaxDepth(int initialProgramMaxDepth) {
            this.initialProgramMaxDepth = initialProgramMaxDepth;
            return this;
        }

        public Builder maxNumberOfGenerations(int maxNumberOfGenerations) {
            this.maxNumberOfGenerations = maxNumberOfGenerations;
            return this;
        }

        public Builder tournatmentSize(int tournatmentSize) {
            this.tournamentSize = tournatmentSize;
            return this;
        }
        public Builder mutationProbability(double mutationProbability) {
            this.mutationProbability = mutationProbability;
            return this;
        }
        public Builder crossoverProbability(double crossoverProbability) {
            this.crossoverProbability = crossoverProbability;
            return this;
        }

        public Builder terminationCriteria(TerminationCriteria terminationCriteria) {
            this.terminationCriteria = terminationCriteria ;
            return this;
        }


        public SimulationInfo build() {
            return new SimulationInfo(this);
        }

    }

    private SimulationInfo(SimulationInfo.Builder builder){
        this.maximumLength = builder.maximumLength;
        this.populationSize = builder.populationSize;
        this.initialProgramMaxDepth = builder.initialProgramMaxDepth;
        this.maxNumberOfGenerations = builder.maxNumberOfGenerations;
        this.tournamentSize = builder.tournamentSize;
        this.mutationProbability = builder.mutationProbability;
        this.crossoverProbability = builder.crossoverProbability;
        this.terminationCriteria = builder.terminationCriteria;
    }


    /** Getters */

    public int getMaximumLength() {
        return maximumLength;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public int getInitialProgramMaxDepth() {
        return initialProgramMaxDepth;
    }

    public int getMaxNumberOfGenerations() {
        return maxNumberOfGenerations;
    }

    public int getTournamentSize() {
        return tournamentSize;
    }

    public double getMutationProbability() {
        return mutationProbability;
    }

    public double getCrossoverProbability() {
        return crossoverProbability;
    }

    public TerminationCriteria getTerminationCriteria() {
        return terminationCriteria;
    }

}
