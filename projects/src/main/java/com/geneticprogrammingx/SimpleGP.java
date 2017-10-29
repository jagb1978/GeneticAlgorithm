package com.geneticprogrammingx;

import com.geneticprogrammingx.mutation.Mutation;
import com.geneticprogrammingx.utils.NodeManager;
import com.geneticprogrammingx.crossover.Crossover;
import com.geneticprogrammingx.parentselection.TournamentSelection;
import java.util.*;
import java.io.*;

public class SimpleGP {
    private double[] fitness;
    private char[][] population;
    private Random random = new Random();
    private static final int ADD = 110;
    private static final int SUB = 111;
    private static final int MUL = 112;
    private static final int DIV = 113;
    private static final int FUNCTION_SET_START = ADD;
    private static final int FUNCTION_SET_END = DIV;
    private double[] preGeneratedRandomConstantValues = new double[FUNCTION_SET_START];
    private double minRandom;
    private double maxRandom;
    private char[] program;
    private int primitiveCounter;
    private int varNumber;
    private int fitnessCases;
    private int randomNumber;
    private int totalNumberOfPrimitives;
    private double fitnessBestPopulation = 0.0;
    private long seed;
    private static final int MAXIMUM_LENGTH = 10000;
    private static final int POPULATION_SIZE = 1000;
    private static final int INITIAL_PROGRAMS_MAXIMUM_DEPTH = 5;
    private static final int MAXIMUM_NUMBER_OF_GENERATIONS = 100;
    private static final int TOURNAMENT_SIZE = 2;
    private static final double MUTATION_PROBABILITY_PER_NODE = 0.05;
    private static final double CROSSOVER_PROBABILITY = 0.9;
    private double[][] targets;
    private NodeManager nodeManager;
    private Crossover crossover;
    private Mutation mutation;


    public SimpleGP(String fileName, long seed) {
        this.fitness = new double[POPULATION_SIZE];
        this.seed = seed;

        if (this.seed >= 0) {
            this.random.setSeed(this.seed);
        }

        setupFitness(fileName);

        this.nodeManager = new NodeManager(FUNCTION_SET_START,FUNCTION_SET_END,this.totalNumberOfPrimitives);
        this.crossover = new Crossover(this.nodeManager);
        this.mutation = new Mutation(this.nodeManager,MUTATION_PROBABILITY_PER_NODE);

        for (int i = 0; i < FUNCTION_SET_START; i++) {
            this.preGeneratedRandomConstantValues[i] = (this.maxRandom - this.minRandom) * random.nextDouble() + minRandom;
        }

        this.population = createRandomPopulation(POPULATION_SIZE, INITIAL_PROGRAMS_MAXIMUM_DEPTH, this.fitness);
    }

    public void evolve() {
        int offspring;
        int parent1;
        int parent2;
        int parent;
        double newFitness;
        char[] newIndividuals;
        TournamentSelection tournamentSelection = new TournamentSelection(POPULATION_SIZE);

        this.printParameters();
        this.stats(this.fitness, this.population, 0);

        for (int generation = 1; generation < MAXIMUM_NUMBER_OF_GENERATIONS; generation++) {

            if (this.fitnessBestPopulation > -1e-5) {
                System.out.print("PROBLEM SOLVED\n");
                System.exit(0);
            }

            for (int individuals = 0; individuals < POPULATION_SIZE; individuals++) {

                if (this.random.nextDouble() < CROSSOVER_PROBABILITY) {
                    parent1 = tournamentSelection.selection(this.fitness, TOURNAMENT_SIZE);
                    parent2 = tournamentSelection.selection(this.fitness, TOURNAMENT_SIZE);
                    newIndividuals = this.crossover.crossover(this.population[parent1], this.population[parent2]);
                } else {
                    parent = tournamentSelection.selection(this.fitness, TOURNAMENT_SIZE);
                    newIndividuals = this.mutation.mutation(this.population[parent]);
                }

                newFitness = fitnessFunction(newIndividuals);
                offspring = tournamentSelection.negativeSelection(this.fitness, TOURNAMENT_SIZE);
                this.population[offspring] = newIndividuals;
                this.fitness[offspring] = newFitness;
            }
            stats(this.fitness, this.population, generation);
        }
        System.out.print("PROBLEM *NOT* SOLVED\n");
        System.exit(1);
    }

    private double run() { /* Interpreter */
        char primitive = this.program[primitiveCounter++];
        if (primitive < FUNCTION_SET_START) {
            return this.preGeneratedRandomConstantValues[primitive];
        } else {
            switch (primitive) {
                case ADD:
                    return run() + run();
                case SUB:
                    return run() - run();
                case MUL:
                    return run() * run();
                case DIV: {
                    double num = run();
                    double den = run();
                    return Math.abs(den) <= 0.001 ? num : num / den;
                }
            }
        }
        return 0.0; // should never get here
    }


    private void setupFitness(String fileName) {
        try {
            String line;
            FileReader fileReader = null;
            BufferedReader bufferedReader = null;
            try {
                fileReader = new FileReader(fileName);
                bufferedReader = new BufferedReader(fileReader);
            } catch (IOException e) {
                //
                fileReader.close();
            }

            line = bufferedReader.readLine();
            String[] splitLine = line.split(",");
            this.varNumber = Integer.parseInt(splitLine[0]);
            this.randomNumber = Integer.parseInt(splitLine[1]);
            this.totalNumberOfPrimitives = this.varNumber + this.randomNumber;
            this.minRandom = Double.parseDouble(splitLine[2]);
            this.maxRandom = Double.parseDouble(splitLine[3]);
            this.fitnessCases = Integer.parseInt(splitLine[4]);
            this.targets = new double[this.fitnessCases][this.varNumber + 1];

            if (this.varNumber + this.randomNumber >= FUNCTION_SET_START) {
                System.out.println("too many variables and constants");
            }

            for (int i = 0; i < this.fitnessCases; i++) {
                line = bufferedReader.readLine();
                splitLine = line.split(",");
                for (int j = 0; j <= this.varNumber; j++) {
                    this.targets[i][j] = Double.parseDouble(splitLine[j]);
                }
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Please provide a data file");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("ERROR: Incorrect data format");
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    private double fitnessFunction(char[] program) {
        double result;
        double fitnessValue = 0.0;

        for (int i = 0; i < this.fitnessCases; i++) {
            System.arraycopy(this.targets[i], 0, this.preGeneratedRandomConstantValues, 0, this.varNumber);
            this.program = program;
            this.primitiveCounter = 0;
            result = run();
            fitnessValue += Math.abs(result - this.targets[i][this.varNumber]);
        }

        return -fitnessValue;
    }

    private int grow(char[] individual, int position, int max, int depth) {
        char primitive = (char) this.random.nextInt(2);
        int oneChild;

        if (position >= max) {
            return -1;
        }

        if (position == 0) {
            primitive = 1;
        }

        if (primitive == 0 || depth == 0) {
            primitive = (char) this.nodeManager.getRandomPositionOfVariableOrConstant();
            individual[position] = primitive;
            return (position + 1);
        } else {
            primitive = (char) this.nodeManager.getRandomFunction();
            individual[position] = primitive;
            oneChild = grow(individual, position + 1, max, depth - 1);

            return oneChild < 0 ? -1 : grow(individual, oneChild, max, depth - 1);
        }
    }

    /**
     * Creates a Random Individual from scratch with a pre-difined depth
     *
     * @param depth
     * @return
     */
    private char[] createRandomIndividual(int depth) {
        char[] individual = new char[MAXIMUM_LENGTH];
        int nodeLength = this.grow(individual, 0, MAXIMUM_LENGTH, depth);

        while (nodeLength < 0) {
            nodeLength = this.grow(individual, 0, MAXIMUM_LENGTH, depth);
        }

        return individual;
    }

    private char[][] createRandomPopulation(int n, int depth, double[] fitness) {
        char[][] randomPopulation = new char[n][];

        for (int i = 0; i < n; i++) {
            randomPopulation[i] = this.createRandomIndividual(depth);
            fitness[i] = this.fitnessFunction(randomPopulation[i]);
        }

        return randomPopulation;
    }

    private void stats(double[] fitness, char[][] population, int gen) {
        int bestIndividual = this.random.nextInt(POPULATION_SIZE);
        int nodeCount = 0;
        this.fitnessBestPopulation = fitness[bestIndividual];
        double fitnessAveragePopulation = 0.0;

        for (int i = 0; i < POPULATION_SIZE; i++) {
            nodeCount += this.nodeManager.getNodeLength(population[i], 0);
            fitnessAveragePopulation += fitness[i];
            if (fitness[i] > fitnessBestPopulation) {
                bestIndividual = i;
                fitnessBestPopulation = fitness[i];
            }
        }

        double avgNodeLength = (double) nodeCount / POPULATION_SIZE;
        fitnessAveragePopulation /= POPULATION_SIZE;

        System.out.print("Generation=" + gen + " Avg Fitness=" + (-fitnessAveragePopulation) + " Best Fitness=" + (-this.fitnessBestPopulation) + " Avg Size=" + avgNodeLength + "\nBest Individual: ");
        printIndividual(population[bestIndividual], 0);
        System.out.print("\n");
        System.out.flush();
    }

    private int printIndividual(char[] individual, int nodeCount) {
        int a1 = 0;
        int a2;
        if (individual[nodeCount] < FUNCTION_SET_START) {
            if (individual[nodeCount] < this.varNumber) {
                System.out.print("X" + (individual[nodeCount] + 1) + " ");
            } else {
                System.out.print(preGeneratedRandomConstantValues[individual[nodeCount]]);
            }
            return ++nodeCount;
        }
        switch (individual[nodeCount]) {
            case ADD:
                System.out.print("(");
                a1 = printIndividual(individual, ++nodeCount);
                System.out.print(" + ");
                break;
            case SUB:
                System.out.print("(");
                a1 = printIndividual(individual, ++nodeCount);
                System.out.print(" - ");
                break;
            case MUL:
                System.out.print("(");
                a1 = printIndividual(individual, ++nodeCount);
                System.out.print(" * ");
                break;
            case DIV:
                System.out.print("(");
                a1 = printIndividual(individual, ++nodeCount);
                System.out.print(" / ");
                break;
            default:
        }
        a2 = printIndividual(individual, a1);
        System.out.print(")");
        return a2;
    }

    private void printParameters() {
        System.out.print("-- TINY GP (Java version) --\n");
        System.out.print("SEED=" + this.seed + "\nMAXIMUM_LENGTH=" + MAXIMUM_LENGTH +
                "\nPOPULATION_SIZE=" + POPULATION_SIZE + "\nINITIAL_PROGRAMS_MAXIMUM_DEPTH=" + INITIAL_PROGRAMS_MAXIMUM_DEPTH +
                "\nCROSSOVER_PROBABILITY=" + CROSSOVER_PROBABILITY +
                "\nMUTATION_PROBABILITY_PER_NODE=" + MUTATION_PROBABILITY_PER_NODE +
                "\nMIN_RANDOM=" + this.minRandom +
                "\nMAX_RANDOM=" + this.maxRandom +
                "\nMAXIMUM_NUMBER_OF_GENERATIONS=" + MAXIMUM_NUMBER_OF_GENERATIONS +
                "\nTOURNAMENT_SIZE=" + TOURNAMENT_SIZE +
                "\n----------------------------------\n");
    }


}
