package com.geneticalgorithm.crossover;

import com.geneticalgorithm.beans.Individual;

/**
 * Implements UniformCrossOver
 * @author Jose Gonzalez
 */
public class UniformCrossOver {

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
    private Individual crossOver(Individual father, Individual mother) {
        Individual offspring = new Individual();

        for (int i = 0; i < father.size(); i++) {
            if (Math.random() <= this.uniformRate) {
                offspring.setGene(i, father.getGene(i));
            } else {
                offspring.setGene(i, mother.getGene(i));
            }
        }

        return offspring;
    }


}
