package com.geneticprogramming.algorithm;

import com.geneticprogramming.beans.PopulationManager;
import com.geneticprogramming.beans.SimulationInfo;
import com.geneticprogramming.beans.TrainData;
import com.geneticprogramming.crossover.Crossover;
import com.geneticprogramming.interfaces.FitnessFunction;
import com.geneticprogramming.interfaces.TerminationCriteria;
import com.geneticprogramming.mutation.Mutation;
import com.geneticprogramming.parentselection.TournamentSelection;
import com.geneticprogramming.utils.NodeManager;

import java.util.Random;

/**
 * Genetic Programing Simulation Base Class
 *
 * @author Jose Gonzalez
 */
public abstract class GeneticProgrammingBase {
    double[] fitnessVector;
    char[][] population;
    // private Population population;
    protected Random random = new Random();
    static int FUNCTION_SET_START = 110;
    static int FUNCTION_SET_END = 113;
    TrainData trainData;
    NodeManager nodeManager;
    protected Crossover crossover;
    protected Mutation mutation;
    PopulationManager populationManager;
    FitnessFunction fitnessFunction;
    SimulationInfo simulationInfo;
    TerminationCriteria terminationCriteria;

    /**
     * The  method firstly selects whether the individual is going to be crossed over
     * or mutate crossover is done with tournament selection
     * mutation is done by randomly choosing a gene and mutating it
     * the new individual substitute the worst individual in the population
     */
    public void evolve() {
        TournamentSelection tournamentSelection = new TournamentSelection(this.simulationInfo.getPopulationSize());
        this.printParameters();
        this.populationManager.stats(this.fitnessVector, this.population, 0);
        // this.populationManager.stats( this.population, 0);
        //
        for (int generation = 1; generation < this.simulationInfo.getMaxNumberOfGenerations(); generation++) {
            if (this.terminationCriteria.hasTerminationCriteriaBeingMet(this.populationManager)) { //Criteria to finalize evolution
                System.out.print("Problem Solved\n");
                System.exit(0);
            }
            for (int individuals = 0; individuals < this.simulationInfo.getPopulationSize(); individuals++) {
                char[] newIndividual;
                //      Individual newIndividual;
                //
                if (this.random.nextDouble() < this.simulationInfo.getCrossoverProbability()) {
                    int parent1 = tournamentSelection.selection(this.fitnessVector, this.simulationInfo.getTournamentSize());
                    int parent2 = tournamentSelection.selection(this.fitnessVector, this.simulationInfo.getTournamentSize());
                    newIndividual = this.crossover.crossover(this.population[parent1], this.population[parent2]);
                    //  int parent1 = tournamentSelection.selection(this.population, this.simulationInfo.getTournamentSize());
                    // int parent2 = tournamentSelection.selection(this.population, this.simulationInfo.getTournamentSize());
                    // newIndividual = this.crossover.crossover(this.population.getIndividual(parent1), this.population.getIndividual(parent2));
                } else {
                    int parent = tournamentSelection.selection(this.fitnessVector, this.simulationInfo.getTournamentSize());
                    newIndividual = this.mutation.mutation(this.population[parent]);
                    // int parent = tournamentSelection.selection(this.population, this.simulationInfo.getTournamentSize());
                    // newIndividual = this.mutation.mutation(this.population.getIndividual(parent));
                }
                //   newIndividual.calculateIndividualFitness(this.fitnessFunction);
                //
                double newFitness = this.fitnessFunction.getFitness(newIndividual);
                int individualToSelect = tournamentSelection.negativeSelection(this.fitnessVector, this.simulationInfo.getTournamentSize());
                this.population[individualToSelect] = newIndividual;
                this.fitnessVector[individualToSelect] = newFitness;
                // finds worst individual in the population to be substituted
                //int individualToSelect = tournamentSelection.negativeSelection(this.population, this.simulationInfo.getTournamentSize());
                // this.population.setIndividual(individualToSelect,newIndividual) ;
            }
            this.populationManager.stats(this.fitnessVector, this.population, generation);
            //    this.populationManager.stats(  this.population, generation);
            //
        }
        System.out.print("Problem *Not* Solved\n");
        System.exit(1);
    }

    private void printParameters() {
        System.out.print("-- Tiny GP  --\n");
        System.out.print(
                //"seed :" + this.seed +
                "\nMaximum Length : " + this.simulationInfo.getMaximumLength() +
                        "\nPopulation Size : " + this.simulationInfo.getPopulationSize() +
                        "\nInitial Program Max Depth : " + this.simulationInfo.getInitialProgramMaxDepth() +
                        "\nCrossover Probability : " + this.simulationInfo.getCrossoverProbability() +
                        "\nMutations Probability : " + this.simulationInfo.getMutationProbability() +
                        "\nMinimum Random Value : " + this.trainData.getMinRandom() +
                        "\nMaximum Random Value : " + this.trainData.getMaxRandom() +
                        "\nMax Number Of Generations : " + this.simulationInfo.getMaxNumberOfGenerations() +
                        "\nTournament Size : " + this.simulationInfo.getTournamentSize() +
                        "\n----------------------------------\n");
    }
}
