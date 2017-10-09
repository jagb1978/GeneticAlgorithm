package com.geneticalgorithm.application;

import com.geneticalgorithm.beans.Population;
import com.geneticalgorithm.crossover.*;
import com.geneticalgorithm.evolution.Algorithm;
import com.geneticalgorithm.interfaces.CrossOver;
import com.geneticalgorithm.interfaces.Mutation;
import com.geneticalgorithm.interfaces.ParentSelection;
import com.geneticalgorithm.mutation.RandomSettingMutation;
import com.geneticalgorithm.parentselection.*;
import com.geneticalgorithm.utils.FitnessCalc;

/**
 * Simple GA application
 *
 * @author Jose Gonzalez
 */
public class ApplicationSimpleGA {
    public static void main(String[] args) {

        double uniformRate = 0.5;
        double mutationRate = 0.015;
        int tournamentSize = 5;
        boolean elitism = true;

        ParentSelection parentSelection = new TournamentSelection(tournamentSize);
        CrossOver crossOver = new UniformCrossOver(uniformRate);
        Mutation mutation = new RandomSettingMutation(0, 100,mutationRate);

        Algorithm algorithm = new Algorithm(parentSelection, crossOver, mutation, elitism);
        // Set a candidate solution
        FitnessCalc.setSolution("35,2,1,15,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0," +
                "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,9,34,35");

        // Create an initial population
        Population population = new Population(100, true);


        // Evolve our population until we reach an optimum solution
        int generationCount = 0;
        while (population.getFittest().getFitness() < FitnessCalc.getMaxFitness()) {
            generationCount++;
            System.out.println("Generation: " + generationCount + " Fittest: " + population.getFittest().getFitness());
            population = algorithm.evolvePopulation(population);
        }

        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        System.out.println(population.getFittest());

    }
}
