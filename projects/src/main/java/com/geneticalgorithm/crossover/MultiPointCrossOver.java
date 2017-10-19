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
        Individual offspring = new Individual(father.size(), father.getFitnessCalc());

        for (int i = 0; i < offspring.size(); i++) {
            int position = i;
            Double gene = this.fatherCrossOverPoints.stream().anyMatch(value -> value == position)? Double.valueOf(father.getGene(i).getValue().toString()):
                    Double.valueOf(mother.getGene(i).getValue().toString());
            offspring.setGene(i, gene);
        }
        return offspring;
    }
}
