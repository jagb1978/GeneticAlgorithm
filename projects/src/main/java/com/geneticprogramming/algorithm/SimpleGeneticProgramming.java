package com.geneticprogramming.algorithm;

import com.geneticprogramming.beans.*;
import com.geneticprogramming.fitnessfunctions.DistanceFitness;
import com.geneticprogramming.mutation.Mutation;
import com.geneticprogramming.utils.NodeManager;
import com.geneticprogramming.crossover.Crossover;

import java.io.*;

public class SimpleGeneticProgramming extends GeneticProgrammingBase {

    public SimpleGeneticProgramming(String fileName, SimulationInfo simulationInfo) {
        this.simulationInfo = simulationInfo;
        this.fitnessVector = new double[this.simulationInfo.getPopulationSize()];
        this.setup(fileName);
        this.nodeManager = new NodeManager(FUNCTION_SET_START, FUNCTION_SET_END, this.trainData.getNumberOfPrimitives());
        this.crossover = new Crossover(this.nodeManager);
        this.mutation = new Mutation(this.nodeManager, this.simulationInfo.getMutationProbability());
        double[] preSetConstantValues = getPreSetConstantValues(); // Generates a set of random numbers that are stored and that are used in the creation of terminals

        this.fitnessFunction = new DistanceFitness(preSetConstantValues, this.trainData.getVarNumber(), this.trainData.getNumberOfDataPoints(), this.trainData.getTargets());
        this.populationManager = new PopulationManager(this.nodeManager, this.simulationInfo.getMaximumLength(), this.fitnessFunction, this.simulationInfo.getPopulationSize());
        this.population = this.populationManager.createRandomPopulation(this.simulationInfo.getPopulationSize(), simulationInfo.getInitialProgramMaxDepth(), this.fitnessVector);
        this.terminationCriteria = simulationInfo.getTerminationCriteria();
        //this.population = this.populationManager.createRandomPopulation(this.simulationInfo.getPopulationSize(), simulationInfo.getInitialProgramMaxDepth());
        //
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

            this.trainData = new TrainData.Builder()
                    .varNumber(Integer.parseInt(splitLine[0]))
                    .randomNumber(Integer.parseInt(splitLine[1]))
                    .minRandom(Double.parseDouble(splitLine[2]))
                    .maxRandom(Double.parseDouble(splitLine[3]))
                    .numberOfDataPoints(Integer.parseInt(splitLine[4]))
                    .build();

            if (this.trainData.getVarNumber() + this.trainData.getRandomNumber() >= FUNCTION_SET_START) {
                System.out.println("too many variables and constants");
            }

            for (int i = 0; i < this.trainData.getNumberOfDataPoints(); i++) {
                line = bufferedReader.readLine();
                splitLine = line.split(",");
                for (int j = 0; j <= this.trainData.getVarNumber(); j++) {
                    this.trainData.getTargets()[i][j] = Double.parseDouble(splitLine[j]);
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

    private double[] getPreSetConstantValues() {
        double[] preSetConstantValues = new double[FUNCTION_SET_START];

        for (int i = 0; i < FUNCTION_SET_START; i++) {
            preSetConstantValues[i] = ((this.trainData.getMaxRandom() - this.trainData.getMinRandom()) * this.random.nextDouble()) + this.trainData.getMinRandom();
        }

        return preSetConstantValues;
    }


}
