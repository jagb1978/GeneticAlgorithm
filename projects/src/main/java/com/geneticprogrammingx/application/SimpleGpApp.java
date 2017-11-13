package com.geneticprogrammingx.application;

import com.geneticprogrammingx.algorithm.SimpleGP;
import com.geneticprogrammingx.beans.SimulationInfo;


public class SimpleGpApp {
    private static final int MAXIMUM_LENGTH = 10000;
    private static final int POPULATION_SIZE = 1000;
    private static final int INITIAL_PROGRAMS_MAXIMUM_DEPTH = 5;
    private static final int MAXIMUM_NUMBER_OF_GENERATIONS = 100;
    private static final int TOURNAMENT_SIZE = 2;
    private static final double MUTATION_PROBABILITY_PER_NODE = 0.05;
    private static final double CROSSOVER_PROBABILITY = 0.9;
    private static final String FILE_PATH="/Users/aft/IdeaProjects/GeneticAlgorithm/projects/src/problem.csv";

    public static void main(String[] args) {
        SimulationInfo simulationInfo = new SimulationInfo.Builder()
                .maximumLength(MAXIMUM_LENGTH)
                .populationSize(POPULATION_SIZE)
                .maxNumberOfGenerations(MAXIMUM_NUMBER_OF_GENERATIONS)
                .tournatmentSize(TOURNAMENT_SIZE)
                .mutationProbability(MUTATION_PROBABILITY_PER_NODE)
                .crossoverProbability(CROSSOVER_PROBABILITY)
                .initialProgramMaxDepth(INITIAL_PROGRAMS_MAXIMUM_DEPTH)
                .build();

        SimpleGP simpleGP = new SimpleGP(FILE_PATH, simulationInfo);
        simpleGP.evolve();
    }
}
