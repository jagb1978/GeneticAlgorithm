package com.geneticprogramming.application;

import com.geneticprogramming.algorithm.SimpleGeneticProgramming;
import com.geneticprogramming.algorithm.TradingGeneticProgramming;
import com.geneticprogramming.beans.SimulationInfo;
import com.geneticprogramming.terminationcriteria.MinimumDistance;
import com.geneticprogramming.terminationcriteria.NoEarlyTermination;

public class GpApp {
    private static final int MAXIMUM_LENGTH = 8;
    private static final int POPULATION_SIZE = 1000;
    private static final int INITIAL_PROGRAMS_MAXIMUM_DEPTH = 5;
    private static final int MAXIMUM_NUMBER_OF_GENERATIONS = 400;
    private static final int TOURNAMENT_SIZE = 2;
    private static final double MUTATION_PROBABILITY_PER_NODE = 0.05;
    private static final double CROSSOVER_PROBABILITY = 0.9;
    private static final String FILE_PATH = "/Users/aft/IdeaProjects/GeneticAlgorithm/projects/src/SecuritiesDataTable.csv";
   // private static final String FILE_PATH = "/Users/aft/IdeaProjects/GeneticAlgorithm/projects/src/problem.csv";

    public static void main(String[] args) {
        SimulationInfo simulationInfo = new SimulationInfo.Builder()
                .maximumLength(MAXIMUM_LENGTH)
                .populationSize(POPULATION_SIZE)
                .maxNumberOfGenerations(MAXIMUM_NUMBER_OF_GENERATIONS)
                .tournatmentSize(TOURNAMENT_SIZE)
                .mutationProbability(MUTATION_PROBABILITY_PER_NODE)
                .crossoverProbability(CROSSOVER_PROBABILITY)
                .initialProgramMaxDepth(INITIAL_PROGRAMS_MAXIMUM_DEPTH)
               // .terminationCriteria(new MinimumDistance())
                .terminationCriteria(new NoEarlyTermination())
                .build();

        TradingGeneticProgramming tradingGP = new TradingGeneticProgramming(FILE_PATH, simulationInfo);
        tradingGP.evolve();
        //SimpleGeneticProgramming simpleGP = new SimpleGeneticProgramming(FILE_PATH, simulationInfo);
        //simpleGP.evolve();

    }
}
