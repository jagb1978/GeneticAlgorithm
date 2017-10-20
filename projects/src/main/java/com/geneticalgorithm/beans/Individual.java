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
public class Individual<T> {
    private int geneLength;
    private Map<Integer,Gene> genes = new HashMap<>();
    private Double fitness = null;
    private FitnessCalculator fitnessCalc;
    private Map<Integer,GeneType> geneMap;

    public Individual(int genesLength,FitnessCalculator fitnessCalculator,Map<Integer,GeneType> geneMap ){
        this.geneLength = genesLength;
        this.fitnessCalc = fitnessCalculator;
        this.geneMap = geneMap;

    }

    public void generateIndividual(Map<Integer,GeneType> geneMap) {
        for (int i = 0; i < geneMap.size(); i++) {
            Gene gene = new Gene<T>(geneMap.get(i));
            gene.setRandomValue();
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

    public void setGene(int index, Gene gene) {
        Gene newGene = new Gene<>(gene.getGeneType());

        newGene.setValue(gene.getValue());
        this.genes.put(index,newGene);
        this.fitness = null;
    }

    public void setRandomGene(int index, GeneType geneType) {
        Gene gene = new Gene<T>(geneType);
        gene.setRandomValue();
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

    public Map<Integer, GeneType> getGeneMap() {
        return geneMap;
    }

}