package com.geneticalgorithm.crossover;

import com.geneticalgorithm.beans.Individual;


/**
 * Class that implements OnePoint Crossover
 *
 * @author Jose Gonzalez
 */
public class OnePointCrossOver {

    private double crossoverFraction;

    public OnePointCrossOver(double crossoverFraction) {
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
        double crossoverPoint = this.crossoverFraction * father.size();

        for (int i = 0; i < offspring.size(); i++) {

            offspring.setGene(i, i < crossoverPoint ? father.getGene(i) : mother.getGene(i));
        }

        return offspring;
    }


}