package com.geneticalgorithm.beans;

import com.geneticalgorithm.utils.FitnessCalc;

import java.util.Random;

/**
 *  Class holding the information of individuals
 *
 *
 * @author Jose Gonzalez
 */
public class Individual {

    private static int defaultGeneLength = 64;
    private int[] genes;
    private int fitness = 0;

    public Individual(){
        FitnessCalc fitnessCalc = new FitnessCalc();
        this.genes = new int[defaultGeneLength];
    }
    public Individual(int genesLength){
        this.genes = new int[genesLength];
        FitnessCalc fitnessCalc = new FitnessCalc();
    }

    // Create a random individual
    public void generateIndividual() {
        Random random = new Random();
        for (int i = 0; i < size(); i++) {
            int gene = random.nextInt(100);
            this.genes[i] = gene;
        }
    }

    /* Getters and setters */
    // Use this if you want to create individuals with different gene lengths
    public static void setDefaultGeneLength(int length) {
        defaultGeneLength = length;
    }

    public int getGene(int index) {
        return this.genes[index];
    }

    public void setGene(int index, int value) {
        this.genes[index] = value;
        fitness = 0;
    }

    /* Public methods */
    public int size() {
        return genes.length;
    }

    public int getFitness() {
        if (fitness == 0) {
            this.fitness = FitnessCalc.getFitness(this);
        }
        return this.fitness;
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < size(); i++) {
            geneString += getGene(i);
        }
        return geneString;
    }

    public int[] getGenes() {
        return this.genes;
    }

}