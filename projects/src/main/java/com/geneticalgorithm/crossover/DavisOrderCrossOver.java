package com.geneticalgorithm.crossover;

import com.geneticalgorithm.beans.Individual;
import com.geneticalgorithm.interfaces.CrossOver;

/**
 * @author Jose Gonzalez
 */
public class DavisOrderCrossOver implements CrossOver {
    private int beggingPosition;
    private int endPosition;

    public DavisOrderCrossOver(int beggingPosition, int endPosition) {
        this.beggingPosition = beggingPosition;
        this.endPosition = endPosition;
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
    @Override
    public Individual crossOver(Individual father, Individual mother) {
        Individual offspring = new Individual(father.size(), father.getFitnessCalc(), father.getGeneMap());

        for (int i = 0; i < offspring.size(); i++) {
            if (i >= this.beggingPosition && i <= this.endPosition) {
                offspring.setGene(i, father.getGene(i) );
            } else {
                offspring.setGene(i, mother.getGene(i));
            }
        }
        return offspring;
    }

}
