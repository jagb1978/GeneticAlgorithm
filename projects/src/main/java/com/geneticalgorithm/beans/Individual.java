package com.geneticalgorithm.beans;


import com.geneticalgorithm.interfaces.FitnessCalculator;

import java.util.Random;

/**
 *  Class holding the information of individuals
 *
 *
 * @author Jose Gonzalez
 */
public class Individual {
    private int geneLength;
    private int[] genes;
    private int fitness = 0;
    private FitnessCalculator fitnessCalc;

    public Individual(int genesLength,FitnessCalculator fitnessCalculator ){
        this.genes = new int[genesLength];
        this.fitnessCalc = fitnessCalculator;
        generateIndividual();
    }

    public void generateIndividual() {
        Random random = new Random();
        for (int i = 0; i < size(); i++) {
            int gene = random.nextInt(100);
            this.genes[i] = gene;
        }
    }

    /** Getters and setters */
    public  void setGeneLength(int length) {
        this.geneLength = length;
    }

    public int getGene(int index) {
        return this.genes[index];
    }

    public void setGene(int index, int value) {
        this.genes[index] = value;
        fitness = 0;
    }

    public int size() {
        return genes.length;
    }

    public int getFitness() {
        if (fitness == 0) {
            this.fitness = this.fitnessCalc.getFitnessValue(this);
        }
        return this.fitness;
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < size(); i++) {
            geneString += getGene(i) +",";
        }
        return geneString;
    }

    public int[] getGenes() {
        return this.genes;
    }

    public FitnessCalculator getFitnessCalc() {
        return fitnessCalc;
    }

}