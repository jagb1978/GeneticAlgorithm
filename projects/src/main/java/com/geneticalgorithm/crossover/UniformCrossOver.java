package com.geneticalgorithm.crossover;

import com.geneticalgorithm.beans.Individual;
import com.geneticalgorithm.interfaces.CrossOver;

/**
 * Implements UniformCrossOver
 * @author Jose Gonzalez
 */
public class UniformCrossOver implements CrossOver {

    private double uniformRate;

    public UniformCrossOver (double uniformRate){
        this.uniformRate = uniformRate;
    }

    /**
     * Method that crosses over two individuals
     * It "randomly" selects one individual and adds the gene to the
     * new crossedOvered individual. Therefore the new individual
     * gets genes from both parents
     *
     * @param father
     * @param mother
     * @return newCrossedOverIndividual
     */
    @Override
    public Individual crossOver(Individual father, Individual mother) {
        Individual offspring = new Individual(father.size(), father.getFitnessCalc());

        for (int i = 0; i < father.size(); i++) {
            if (Math.random() <= this.uniformRate) {
                offspring.setGene(i, (Integer)father.getGene(i).getValue());
            } else {
                offspring.setGene(i, (Integer)mother.getGene(i).getValue());
            }
        }

        return offspring;
    }


}
