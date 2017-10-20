package com.geneticalgorithm.crossover;

import com.geneticalgorithm.beans.Individual;
import com.geneticalgorithm.interfaces.CrossOver;

import java.util.List;

/**
 * @author Jose Gonzalez
 */
public class MultiPointCrossOver implements CrossOver {
    private List<Integer> fatherCrossOverPoints;

    public MultiPointCrossOver(List<Integer> fatherCrossOverPoints) {
       this.fatherCrossOverPoints = fatherCrossOverPoints;
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
        Individual offspring = new Individual(father.size(), father.getFitnessCalc(),father.getGeneMap());

        for (int i = 0; i < offspring.size(); i++) {
            int position = i;
            offspring.setGene(i, this.fatherCrossOverPoints.stream().anyMatch(value -> value == position)? father.getGene(i):
                    mother.getGene(i));
        }
        return offspring;
    }
}
