package com.ga.simpleGA.crossover;

import com.ga.simpleGA.beans.Individual;


/**
 * Class that implements OnePoint Crossover
 *
 * @author Jose Gonzalez
 */
public class OnePointCrossOver {

    private double crossoverFraction;

    public OnePointCrossOver(double crossoverFraction){
        this.crossoverFraction = crossoverFraction;
    }

    /**
     * Method that crosses over two individuals
     * It adds the gene of the father up to the specified crossover point
     * and after that adds the mother genes
     *
     * @param father
     * @param mother
     * @return newCrossedOverIndividual
     */
    public Individual crossOver(Individual father, Individual mother) {
        Individual offspring = new Individual(father.size());
        double crossoverPoint =  this.crossoverFraction;

        for (int i = 0; i < father.size(); i++) {
            if (i <= father.size()* this.crossoverFraction) {
                offspring.setGene(i, father.getGene(i));
            } else {
                offspring.setGene(i, mother.getGene(i));
            }
        }

        return offspring;
    }


}