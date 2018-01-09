package com.geneticalgorithm.application;

import com.geneticalgorithm.Enums.ValueType;
import com.geneticalgorithm.beans.GeneType;
import com.geneticalgorithm.beans.Individual;
import com.geneticalgorithm.beans.Population;
import com.geneticalgorithm.crossover.UniformCrossOver;
import com.geneticalgorithm.evolution.Algorithm;
import com.geneticalgorithm.fitnesscalculator.MaxPnlFitnessCalc;
import com.geneticalgorithm.interfaces.CrossOver;
import com.geneticalgorithm.interfaces.FitnessCalculator;
import com.geneticalgorithm.interfaces.Mutation;
import com.geneticalgorithm.interfaces.ParentSelection;
import com.geneticalgorithm.mutation.RandomSettingMutation;
import com.geneticalgorithm.parentselection.*;
import com.geneticalgorithm.utils.FitnessCalc;
import com.trading.interfaces.Strategy;
import com.trading.strategies.EmaCrossOver;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple GA application
 *
 * @author Jose Gonzalez
 */
public class ApplicationSimpleGA {
    public static void main(String[] args) {
        int populationSize = 50;
        double uniformRate = 0.5;
        double mutationRate = 0.5;
        int tournamentSize = 5;
        boolean elitism = true;
        String dataFilePath = "//Users/aft/IdeaProjects/GeneticAlgorithm/projects/src/SecuritiesDataTable.csv";

        ParentSelection parentSelection = new TournamentSelection(tournamentSize);
        CrossOver crossOver = new UniformCrossOver(uniformRate);
        Mutation mutation = new RandomSettingMutation(25, 100, mutationRate);
        FitnessCalculator fitnessCalculator = new MaxPnlFitnessCalc(dataFilePath);
        //FitnessCalculator fitnessCalculator = new FitnessCalc();

       Map<Integer, GeneType> geneMap = new HashMap<>();
//        for(int i=0; i<64; i++){
//            geneMap.put(i, new GeneType("Bit"+i, ValueType.VALUE_BIT));
//        }
        geneMap.put(0, new GeneType("ShortEma", ValueType.VALUE_INT, 25,100));
        geneMap.put(1, new GeneType("LongEma", ValueType.VALUE_INT, 25,100));

        // Set a candidate solution
    //    FitnessCalc.setSolution("1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1");
      //  FitnessCalc.setSolution("35,2,1,15,0,0,0,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,9,34,35");
        // Create an initial population
        Population population = new Population.Builder()
                .populationSize(populationSize)
                .fitnessCalculator(fitnessCalculator)
                .initialise(true)
                .geneMap(geneMap)
                .build();

        Algorithm algorithm = new Algorithm(parentSelection, crossOver, mutation, elitism);

        int generationCount = 0;
        while (generationCount < 100) {
  //          while (population.getFittest().getFitness()<63){
            generationCount++;
            System.out.println("Generation: " + generationCount + " Fittest: " + population.getFittest().getFitness() + " Genes: "
                    + population.getFittest());
            population = algorithm.evolvePopulation(population);
        }

        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        System.out.println(population.getFittest());

    }

    private static void runEvolution( Algorithm algorithm, Population population){
        int generationCount = 0;
        while (generationCount < 10) {
            generationCount++;
            System.out.println("Generation: " + generationCount + " Fittest: " + population.getFittest().getFitness() + " Genes: "
                    + population.getFittest());
            population = algorithm.evolvePopulation(population);
        }

        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        System.out.println(population.getFittest());
    }
}
