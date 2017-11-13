package com.geneticprogrammingx.algorithm;

import com.geneticprogrammingx.beans.PopulationManager;
import com.geneticprogrammingx.beans.SimulationInfo;
import com.geneticprogrammingx.beans.TrainDataInfo;
import com.geneticprogrammingx.fitnessfunctions.AbsoluteDistanceFitnessFunction;
import com.geneticprogrammingx.interfaces.FitnessFunction;
import com.geneticprogrammingx.mutation.Mutation;
import com.geneticprogrammingx.utils.NodeManager;
import com.geneticprogrammingx.crossover.Crossover;
import com.geneticprogrammingx.parentselection.TournamentSelection;
import java.util.*;
import java.io.*;

public class SimpleGP {
    private double[] fitnessVector;
    private char[][] population;
    private Random random = new Random();
    private static final int FUNCTION_SET_START =110;
    private static final int FUNCTION_SET_END = 113;
    private TrainDataInfo trainDataInfo;
    private NodeManager nodeManager;
    private Crossover crossover;
    private Mutation mutation;
    private PopulationManager populationManager;
    private FitnessFunction fitnessFunction;
    private SimulationInfo simulationInfo;

    public SimpleGP(String fileName, SimulationInfo simulationInfo) {
        this.simulationInfo = simulationInfo;
        this.fitnessVector = new double[this.simulationInfo.getPopulationSize()];
        setup(fileName);
        this.nodeManager = new NodeManager(FUNCTION_SET_START,FUNCTION_SET_END,this.trainDataInfo.getTotalNumberOfPrimitives() );
        this.crossover = new Crossover(this.nodeManager);
        this.mutation = new Mutation(this.nodeManager,this.simulationInfo.getMutationProbability());


        /* Generates a set of random numbers that are stored and that are used in the creation of terminals*/
        double[] preGeneratedRandomConstantValues  = new double[FUNCTION_SET_START];
        for (int i = 0; i < FUNCTION_SET_START; i++) {
            preGeneratedRandomConstantValues[i] = (this.trainDataInfo.getMaxRandom()- this.trainDataInfo.getMinRandom()) * this.random.nextDouble()
                    + this.trainDataInfo.getMinRandom();
        }
        //


        this.fitnessFunction = new AbsoluteDistanceFitnessFunction(preGeneratedRandomConstantValues, this.trainDataInfo.getVarNumber(),
                this.trainDataInfo.getNumberOfDataPoints(), this.trainDataInfo.getTargets());
        this.populationManager = new PopulationManager(this.nodeManager,this.simulationInfo.getMaximumLength(), this.fitnessFunction,this.simulationInfo.getPopulationSize());
        this.population = this.populationManager.createRandomPopulation(this.simulationInfo.getPopulationSize(), simulationInfo.getInitialProgramMaxDepth(), this.fitnessVector);
    }

    public void evolve() {
        TournamentSelection tournamentSelection = new TournamentSelection(this.simulationInfo.getPopulationSize());
        this.printParameters();
        this.populationManager.stats(this.fitnessVector, this.population, 0);

        for (int generation = 1; generation < this.simulationInfo.getMaxNumberOfGenerations(); generation++) {

            //Criteria to finalize evolution
            if (this.populationManager.getFitnessBestPopulation() > -1e-5) {
                System.out.print("Problem Solved\n");
                System.exit(0);
            }

            // evolution Firstly selects whether the individual is going to be crossedOver or mutate
            // crossover is done with tournament selection
            // mutation is done by randomly choosing a gene and mutating it
            // the new individual substitute the worst individual in the population
            for (int individuals = 0; individuals < this.simulationInfo.getPopulationSize(); individuals++) {
                char[] newIndividual;
                if (this.random.nextDouble() < this.simulationInfo.getCrossoverProbability()) {
                    int parent1 = tournamentSelection.selection(this.fitnessVector, this.simulationInfo.getTournamentSize());
                    int parent2 = tournamentSelection.selection(this.fitnessVector, this.simulationInfo.getTournamentSize());
                    newIndividual = this.crossover.crossover(this.population[parent1], this.population[parent2]);
                } else {
                    int parent = tournamentSelection.selection(this.fitnessVector, this.simulationInfo.getTournamentSize());
                    newIndividual = this.mutation.mutation(this.population[parent]);
                }
                double newFitness =this.fitnessFunction.getFitness(newIndividual);

                //finds worst individual in the population to be substituted
                int offspring = tournamentSelection.negativeSelection(this.fitnessVector, this.simulationInfo.getTournamentSize());
                this.population[offspring] = newIndividual;
                this.fitnessVector[offspring] = newFitness;
            }
            this.populationManager.stats(this.fitnessVector, this.population, generation);
        }
        System.out.print("Problem *Not* Solved\n");
        System.exit(1);
    }


    private void setup(String fileName) {
        try {
            String line;
            FileReader fileReader = null;
            BufferedReader bufferedReader = null;
            try {
                fileReader = new FileReader(fileName);
                bufferedReader = new BufferedReader(fileReader);
            } catch (IOException e) {
                fileReader.close();
            }

            line = bufferedReader.readLine();
            String[] splitLine = line.split(",");

            this.trainDataInfo = new TrainDataInfo.Builder()
                    .varNumber(Integer.parseInt(splitLine[0]))
                    .randomNumber(Integer.parseInt(splitLine[1]))
                    .minRandom(Double.parseDouble(splitLine[2]))
                    .maxRandom(Double.parseDouble(splitLine[3]))
                    .numberOfDataPoints(Integer.parseInt(splitLine[4]))
                    .build();

            if (this.trainDataInfo.getVarNumber() + this.trainDataInfo.getRandomNumber() >= FUNCTION_SET_START) {
                System.out.println("too many variables and constants");
            }

            for (int i = 0; i < this.trainDataInfo.getNumberOfDataPoints(); i++) {
                line = bufferedReader.readLine();
                splitLine = line.split(",");
                for (int j = 0; j <= this.trainDataInfo.getVarNumber(); j++) {
                    this.trainDataInfo.getTargets()[i][j] = Double.parseDouble(splitLine[j]);
                }
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: Please provide a data file");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Error: Incorrect data format");
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    private void printParameters() {
        System.out.print("-- Tiny GP  --\n");
        System.out.print(
                 //"seed :" + this.seed +
                "\nMaximum Length : " + this.simulationInfo.getMaximumLength() +
                "\nPopulation Size : " +  this.simulationInfo.getPopulationSize() +
                "\nInitial Program Max Depth : " +  this.simulationInfo.getInitialProgramMaxDepth() +
                "\nCrossover Probability : " +  this.simulationInfo.getCrossoverProbability() +
                "\nMutations Probability : " +  this.simulationInfo.getMutationProbability()+
                "\nMinimum Random Value : " + this.trainDataInfo.getMinRandom() +
                "\nMaximum Random Value : " + this.trainDataInfo.getMaxRandom() +
                "\nMax Number Of Generations : " +  this.simulationInfo.getMaxNumberOfGenerations()+
                "\nTournament Size : " +  this.simulationInfo.getTournamentSize() +
                "\n----------------------------------\n");
    }
}
