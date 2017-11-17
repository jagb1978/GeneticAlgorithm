package com.geneticprogrammingx.beans;

import com.geneticprogrammingx.exceptions.NoIndividualsException;
import com.geneticprogrammingx.interfaces.FitnessFunction;
import com.geneticprogrammingx.utils.NodeManager;

import java.util.Random;

/**
 *
 * @author Jose Gonzalez
 */
public class PopulationManager {
    private NodeManager nodeManager;
    private Random random = new Random();
    private FitnessFunction fitnessFunction;
    private int maximumLength;
    private int populationSize;
    private double fitnessBestPopulation = 0.0;

    public PopulationManager(NodeManager nodeManager, int maximumLength, FitnessFunction fitnessFunction, int populationSize){
        this.nodeManager = nodeManager;
        this.maximumLength =  maximumLength;
        this.fitnessFunction = fitnessFunction;
        this.populationSize = populationSize;
    }

    //    --------  new ----------
    private int growIndividual(Individual individual, int position, int max, int depth) {
        char primitive = (char) this.random.nextInt(2);

        if (position >= max) {return -1;}

        if (position == 0) {primitive = 1;}

        if (primitive == 0 || depth == 0) {
            primitive = (char) this.nodeManager.getRandomPositionOfVariableOrConstant();
            individual.getGenes()[position] = primitive;

            return position + 1;
        } else {
            primitive = (char) this.nodeManager.getRandomFunction();
            individual.getGenes()[position] = primitive;
            int oneChild = grow(individual.getGenes(), position + 1, max, depth - 1);

            return oneChild < 0 ? -1 : grow(individual.getGenes(), oneChild, max, depth - 1);
        }
    }
    //// --------------------------------
    ///  --------    new  -----------------
    private Individual createRandomIndividualObject(int depth) {
        Individual individual = new Individual(this.maximumLength);
        int nodeLength = this.growIndividual(individual, 0, this.maximumLength, depth);

        while (nodeLength < 0) {
            nodeLength = this.growIndividual(individual, 0, this.maximumLength, depth);
        }
        return individual;
    }
    ///
    //--------    new  --------
    public Population createRandomPopulation(int populationSize, int depth){
        Population randomPopulation = new Population();
        for (int i = 0; i < populationSize; i++) {
            randomPopulation.add(this.createRandomIndividualObject(depth));
            randomPopulation.getIndividual(i).calculateIndividualFitness(this.fitnessFunction);
        }
        return randomPopulation;
    }
    //


    private int grow(char[] individual, int position, int max, int depth) {
        char primitive = (char) this.random.nextInt(2);

        if (position >= max) {return -1;}

        if (position == 0) {primitive = 1;}

        if (primitive == 0 || depth == 0) {
            primitive = (char) this.nodeManager.getRandomPositionOfVariableOrConstant();
            individual[position] = primitive;

            return position + 1;
        } else {
            primitive = (char) this.nodeManager.getRandomFunction();
            individual[position] = primitive;
            int oneChild = grow(individual, position + 1, max, depth - 1);

            return oneChild < 0 ? -1 : grow(individual, oneChild, max, depth - 1);
        }
    }
    /**
     * Creates a Random Individual from scratch with a pre-defined depth
     *
     * @param depth
     * @return
     */
    private char[] createRandomIndividual(int depth) {
        char[] individual = new char[this.maximumLength];
        int nodeLength = this.grow(individual, 0, this.maximumLength, depth);

        while (nodeLength < 0) {
            nodeLength = this.grow(individual, 0, this.maximumLength, depth);
        }
        return individual;
    }

    public char[][] createRandomPopulation(int populationSize, int depth, double[] fitness) {
        char[][] randomPopulation = new char[populationSize][];
        for (int i = 0; i < populationSize; i++) {
            randomPopulation[i] = this.createRandomIndividual(depth);
            fitness[i] = this.fitnessFunction.getFitness(randomPopulation[i]);
        }
        return randomPopulation;
    }

    public void stats(Population population, int generation) throws NoIndividualsException{
        population.generatePopulationStats();
        Individual fittestIndividual = population.getFittestIndividual();
        double avgNodeLength = population.avgNodeLength(this.nodeManager);

        System.out.print("Generation =" + generation + " Avg Fitness =" + (-population.getAveragePopulationFitness()) + " Best Fitness =" + (-fittestIndividual.getFitness()) + " Avg Size =" + avgNodeLength + "\nBest Individual: ");
        this.fitnessFunction.printIndividual(population.getFittestIndividual(), 0);
        this.fitnessBestPopulation = population.getFittestIndividual().getFitness();
        System.out.print("\n");
    }

    public void stats(double[] fitness, char[][] population, int gen) {
        int bestIndividual = this.random.nextInt(this.populationSize);
        int nodeCount = 0;
        this.fitnessBestPopulation = fitness[bestIndividual];
        double fitnessAveragePopulation = 0.0;

        for (int i = 0; i < this.populationSize; i++) {
            nodeCount += this.nodeManager.getNodeLength(population[i], 0);
            fitnessAveragePopulation += fitness[i];
            if (fitness[i] > fitnessBestPopulation) {
                bestIndividual = i;
                fitnessBestPopulation = fitness[i];
            }
        }

        double avgNodeLength = (double) nodeCount / this.populationSize;
        fitnessAveragePopulation /= this.populationSize;

        System.out.print("Generation=" + gen + " Avg Fitness=" + (-fitnessAveragePopulation) + " Best Fitness=" + (-this.fitnessBestPopulation)
                + " Avg Size=" + avgNodeLength + "\nBest Individual: ");
        this.fitnessFunction.printIndividual(population[bestIndividual], 0);
        System.out.print("\n");
    }

    public double getFitnessBestPopulation() {
        return fitnessBestPopulation;
    }


}
