package com.geneticprogrammingx.beans;

/**
 * Individual Gene. Contains gene sequence and fitness of the individual
 *
 * @author Jose Gonzalez
 */
public class Individual {
    private char[] individualGenes;
    private double individualFitness;
    private int positionInPopulation;

    public Individual(int numberOfGenes){
        this.individualGenes = new char[numberOfGenes];
    }

    public char[] getIndividualGenes() {
        return individualGenes;
    }

    public void setIndividualGenes(char[] individualGenes) {
        this.individualGenes = individualGenes;
    }

    public double getIndividualFitness() {
        return individualFitness;
    }

    public void setIndividualFitness(double individualFitness) {
        this.individualFitness = individualFitness;
    }

    public int getPositionInPopulation() {
        return positionInPopulation;
    }

    public void setPositionInPopulation(int positionInPopulation) {
        this.positionInPopulation = positionInPopulation;
    }



}
