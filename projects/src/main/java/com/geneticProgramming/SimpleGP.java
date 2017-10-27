package com.geneticProgramming;

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
    private double[] x = new double[FUNCTION_SET_START];
    private double minRandom;
    private double maxRandom;
    private char[] program;
    private int primitiveCounter;
    private int varNumber;
    private int fitnessCases;
    private int randomNumber;
    private double fitnessBestPopulation = 0.0;
    private long seed;
    private static final int MAXIMUM_LENGTH = 10000;
    private static final int POPULATION_SIZE = 10000;
    private static final int INITIAL_PROGRAMS_MAXIMUM_DEPTH = 5;
    private static final int MAXIMUM_NUMBER_OF_GENERATIONS = 100;
    private static final int TOURNAMENT_SIZE = 2;
    private static final double MUTATION_PROBABILITY_PER_NODE = 0.05;
    private static final double CROSSOVER_PROBABILITY = 0.9;
    private double[][] targets;

    public static void main(String[] args) {
        String fileName = "//Users/aft/IdeaProjects/GeneticAlgorithm/projects/src/problem.csv";
        long seed = -1;

        if (args.length == 2) {
            seed = Integer.valueOf(args[0]);
            fileName = args[1];
        } else if (args.length == 1) {
            fileName = args[0];
        }

        SimpleGP simpleGP = new SimpleGP(fileName, seed);
        simpleGP.evolve();
    }

    private SimpleGP(String fileName, long seed) {
        this.fitness = new double[POPULATION_SIZE];
        this.seed = seed;
        if (this.seed >= 0) {
            this.random.setSeed(this.seed);
        }
        setupFitness(fileName);

        for (int i = 0; i < FUNCTION_SET_START; i++) {
            this.x[i] = (this.maxRandom - this.minRandom) * random.nextDouble() + minRandom;
        }
        this.population = createRandomPopulation(POPULATION_SIZE, INITIAL_PROGRAMS_MAXIMUM_DEPTH, this.fitness);
    }

    private void evolve() {
        int offspring;
        int parent1;
        int parent2;
        int parent;
        double newFitness;
        char[] newIndividuals;

        this.printParameters();
        this.stats(this.fitness, this.population, 0);

        for (int generation = 1; generation < MAXIMUM_NUMBER_OF_GENERATIONS; generation++) {

            if (this.fitnessBestPopulation > -1e-5) {
                System.out.print("PROBLEM SOLVED\n");
                System.exit(0);
            }

            for (int individuals = 0; individuals < POPULATION_SIZE; individuals++) {

                if (this.random.nextDouble() < CROSSOVER_PROBABILITY) {
                    parent1 = tournament(this.fitness, TOURNAMENT_SIZE);
                    parent2 = tournament(this.fitness, TOURNAMENT_SIZE);
                    newIndividuals = crossover(this.population[parent1], this.population[parent2]);
                } else {
                    parent = tournament(this.fitness, TOURNAMENT_SIZE);
                    newIndividuals = mutation(this.population[parent], MUTATION_PROBABILITY_PER_NODE);
                }

                newFitness = fitnessFunction(newIndividuals);
                offspring = negativeTournament(this.fitness, TOURNAMENT_SIZE);
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
            return this.x[primitive];
        }

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
            default:
        }

        return 0.0; // should never get here
    }

    private int traverse(char[] buffer, int bufferCount) {
        if (buffer[bufferCount] < FUNCTION_SET_START)
            return (++bufferCount);

        switch (buffer[bufferCount]) {
            case ADD:
            case SUB:
            case MUL:
            case DIV:
                return (traverse(buffer, traverse(buffer, ++bufferCount)));
        }
        return 0; // should never get here
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
        // int len = traverse(program, 0);

        for (int i = 0; i < this.fitnessCases; i++) {
            System.arraycopy(this.targets[i], 0, this.x, 0, this.varNumber);
            this.program = program;
            this.primitiveCounter = 0;
            result = run();
            fitnessValue += Math.abs(result - this.targets[i][this.varNumber]);
        }

        return -fitnessValue;
    }

    private int grow(char[] buffer, int position, int max, int depth) {
        char primitive = (char) this.random.nextInt(2);
        int oneChild;

        if (position >= max) {
            return -1;
        }

        if (position == 0) {
            primitive = 1;
        }

        if (primitive == 0 || depth == 0) {
            primitive = (char) this.random.nextInt(this.varNumber + this.randomNumber);
            buffer[position] = primitive;
            return (position + 1);
        } else {
            primitive = (char) (random.nextInt(FUNCTION_SET_END - FUNCTION_SET_START + 1) + FUNCTION_SET_START);
            switch (primitive) {
                case ADD:

                case SUB:

                case MUL:

                case DIV:
                    buffer[position] = primitive;
                    oneChild = grow(buffer, position + 1, max, depth - 1);
                    return oneChild < 0 ? -1 : grow(buffer, oneChild, max, depth - 1);

            }
        }
        return 0; // should never get here
    }

    private int printIndividual(char[] buffer, int bufferCounter) {
        int a1 = 0;
        int a2;
        if (buffer[bufferCounter] < FUNCTION_SET_START) {
            if (buffer[bufferCounter] < this.varNumber)
                System.out.print("X" + (buffer[bufferCounter] + 1) + " ");
            else
                System.out.print(x[buffer[bufferCounter]]);
            return (++bufferCounter);
        }
        switch (buffer[bufferCounter]) {
            case ADD:
                System.out.print("(");
                a1 = printIndividual(buffer, ++bufferCounter);
                System.out.print(" + ");
                break;
            case SUB:
                System.out.print("(");
                a1 = printIndividual(buffer, ++bufferCounter);
                System.out.print(" - ");
                break;
            case MUL:
                System.out.print("(");
                a1 = printIndividual(buffer, ++bufferCounter);
                System.out.print(" * ");
                break;
            case DIV:
                System.out.print("(");
                a1 = printIndividual(buffer, ++bufferCounter);
                System.out.print(" / ");
                break;
            default:
        }
        a2 = printIndividual(buffer, a1);
        System.out.print(")");
        return a2;
    }


    private char[] buffer = new char[MAXIMUM_LENGTH];

    private char[] createRandomIndividual(int depth) {
        int nodeLength = this.grow(this.buffer, 0, MAXIMUM_LENGTH, depth);

        while (nodeLength < 0) {
            nodeLength = this.grow(this.buffer, 0, MAXIMUM_LENGTH, depth);
        }

        char[] individual = new char[nodeLength];
        System.arraycopy(this.buffer, 0, individual, 0, nodeLength);

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
            nodeCount += traverse(population[i], 0);
            fitnessAveragePopulation += fitness[i];
            if (fitness[i] > fitnessBestPopulation) {
                bestIndividual = i;
                fitnessBestPopulation = fitness[i];
            }
        }

        double avgNodeLength = (double) nodeCount / POPULATION_SIZE;
        fitnessAveragePopulation /= POPULATION_SIZE;

        System.out.print("Generation=" + gen + " Avg Fitness=" + (-fitnessAveragePopulation) + " Best Fitness=" + (-this.fitnessBestPopulation) + " Avg Node Length=" + avgNodeLength + "\nBest Individual: ");
        printIndividual(population[bestIndividual], 0);
        System.out.print("\n");
        System.out.flush();
    }

    private int tournament(double[] fitness, int tournamentSize) {
        int bestIndividual = this.random.nextInt(POPULATION_SIZE);
        int competitor;
        double fitnessBest = -1.0e34;

        for (int i = 0; i < tournamentSize; i++) {
            competitor = this.random.nextInt(POPULATION_SIZE);
            if (fitness[competitor] > fitnessBest) {
                fitnessBest = fitness[competitor];
                bestIndividual = competitor;
            }
        }
        return bestIndividual;
    }

    private int negativeTournament(double[] fitness, int tournamentSize) {
        int worstIndividual = this.random.nextInt(POPULATION_SIZE);
        int competitor;
        double fitnessWorst = 1e34;

        for (int i = 0; i < tournamentSize; i++) {
            competitor = this.random.nextInt(POPULATION_SIZE);

            if (fitness[competitor] < fitnessWorst) {
                fitnessWorst = fitness[competitor];
                worstIndividual = competitor;
            }
        }
        return worstIndividual;
    }

    private char[] crossover(char[] parent1, char[] parent2) {
        int nodeLengthParent1 = traverse(parent1, 0);
        int nodeLengthParent2 = traverse(parent2, 0);

        int crossover1Start = this.random.nextInt(nodeLengthParent1);
        int crossover1End = traverse(parent1, crossover1Start);

        int crossover2start = this.random.nextInt(nodeLengthParent2);
        int crossover2end = traverse(parent2, crossover2start);

        int offspringNodeLength = crossover1Start + (crossover2end - crossover2start) + (nodeLengthParent1 - crossover1End);

        char[] offspring = new char[offspringNodeLength];

        System.arraycopy(parent1, 0, offspring, 0, crossover1Start);
        System.arraycopy(parent2, crossover2start, offspring, crossover1Start, (crossover2end - crossover2start));
        System.arraycopy(parent1, crossover1End, offspring, crossover1Start + (crossover2end - crossover2start), (nodeLengthParent1 - crossover1End));

        return offspring;
    }

    private char[] mutation(char[] parent, double mutationProbability) {
        int len = traverse(parent, 0);
        int mutationSite;
        char[] parentCopy = new char[len];

        System.arraycopy(parent, 0, parentCopy, 0, len);

        for (int i = 0; i < len; i++) {
            if (this.random.nextDouble() < mutationProbability) {
                mutationSite = i;

                if (parentCopy[mutationSite] < FUNCTION_SET_START) {
                    parentCopy[mutationSite] = (char) this.random.nextInt(varNumber + randomNumber);
                } else {
                    switch (parentCopy[mutationSite]) {
                        case DIV:
                            parentCopy[mutationSite] = (char) (this.random.nextInt(FUNCTION_SET_END - FUNCTION_SET_START + 1) + FUNCTION_SET_START);
                            break;
                        default:
                    }
                }

            }
        }
        return parentCopy;
    }

    private void printParameters() {
        System.out.print("-- TINY GP (Java version) --\n");
        System.out.print("SEED=" + seed + "\nMAXIMUM_LENGTH=" + MAXIMUM_LENGTH +
                "\nPOPULATION_SIZE=" + POPULATION_SIZE + "\nINITIAL_PROGRAMS_MAXIMUM_DEPTH=" + INITIAL_PROGRAMS_MAXIMUM_DEPTH +
                "\nCROSSOVER_PROBABILITY=" + CROSSOVER_PROBABILITY +
                "\nMUTATION_PROBABILITY_PER_NODE=" + MUTATION_PROBABILITY_PER_NODE +
                "\nMIN_RANDOM=" + minRandom +
                "\nMAX_RANDOM=" + maxRandom +
                "\nMAXIMUM_NUMBER_OF_GENERATIONS=" + MAXIMUM_NUMBER_OF_GENERATIONS +
                "\nTOURNAMENT_SIZE=" + TOURNAMENT_SIZE +
                "\n----------------------------------\n");
    }


}
