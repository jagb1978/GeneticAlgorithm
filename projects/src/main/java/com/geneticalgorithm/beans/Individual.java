package com.geneticalgorithm.beans;

import com.geneticalgorithm.interfaces.FitnessCalculator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *  Class holding the information of individuals
 *
 *
 * @author Jose Gonzalez
 */
public class Individual {
    private int geneLength;
    private Map<Integer,Gene> genes = new HashMap<>();
    private Double fitness = null;
    private FitnessCalculator fitnessCalc;

    public Individual(int genesLength,FitnessCalculator fitnessCalculator ){
        this.geneLength = genesLength;
        this.fitnessCalc = fitnessCalculator;
        generateIndividual();
    }


    public void generateIndividual() {
        Random random = new Random();
        for (int i = 0; i < this.geneLength; i++) {
            Gene gene = new Gene<Integer>();
            gene.setValue(random.nextInt(100));
            this.genes.put(i,gene) ;
        }
    }

    /** Getters and setters */
    public  void setGeneLength(int length) {
        this.geneLength = length;
    }

    public Gene getGene(int index) {
        return this.genes.get(index);
    }

    public void setGene(int index, double value) {
      //  this.genes[index] = value;
        Gene gene = new Gene<Double>();
        gene.setValue(value);
        this.genes.put(index,gene);
        this.fitness = null;
    }

    public int size() {
        return this.genes.size();
    }

    public Double getFitness() {
        if (this.fitness == null) {
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

    public Map<Integer,Gene> getGenes() {
        return this.genes;
    }

    public FitnessCalculator getFitnessCalc() {
        return fitnessCalc;
    }

}