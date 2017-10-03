package com.ga.simpleGA.application;

import com.ga.simpleGA.beans.Population;
import com.ga.simpleGA.evolution.Algorithm;
import com.ga.simpleGA.utils.FitnessCalc;

/**
 * Simple GA application
 *
 * @author Jose Gonzalez
 */
public class ApplicationSimpleGA {
    public static void main(String[] args) {

        Algorithm algorithm= new Algorithm();
        // Set a candidate solution
        FitnessCalc.setSolution("1111000000000000000000000000000000000000000000000000000000001111");

        // Create an initial population
        Population population = new Population(50, true);

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
