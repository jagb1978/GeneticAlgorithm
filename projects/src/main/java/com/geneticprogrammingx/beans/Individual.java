package com.geneticprogrammingx.beans;

import com.geneticalgorithm.interfaces.FitnessCalculator;
import com.geneticprogrammingx.interfaces.FitnessFunction;
import org.omg.PortableInterceptor.INACTIVE;

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

    public void calculateIndividualFitness(FitnessFunction fitnessFunction){
        this.individualFitness = fitnessFunction.getFitness(this);
    }


    public char[] getGenes() {
        return individualGenes;
    }

    public void setIndividualGenes(char[] individualGenes) {
        this.individualGenes = individualGenes;
    }

    public double getFitness() {
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

    public int length(){
        return this.individualGenes.length;
    }
}
