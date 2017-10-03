package com.ga.simpleGA.beans;

import com.ga.simpleGA.utils.FitnessCalc;

/**
 *  Class holding the information of individuals
 *
 *
 * @author Jose Gonzalez
 */
public class Individual {

    private static int defaultGeneLength = 64;
    private byte[] genes = new byte[defaultGeneLength];
    private int fitness = 0;

    public Individual(){
        FitnessCalc fitnessCalc = new FitnessCalc();
    }

    // Create a random individual
    public void generateIndividual() {
        for (int i = 0; i < size(); i++) {
            byte gene = (byte) Math.round(Math.random());
            this.genes[i] = gene;
        }
    }

    /* Getters and setters */
    // Use this if you want to create individuals with different gene lengths
    public static void setDefaultGeneLength(int length) {
        defaultGeneLength = length;
    }

    public byte getGene(int index) {
        return this.genes[index];
    }

    public void setGene(int index, byte value) {
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

    public byte[] getGenes() {
        return genes;
    }

}