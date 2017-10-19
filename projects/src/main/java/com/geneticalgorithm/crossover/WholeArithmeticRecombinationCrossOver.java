package com.geneticalgorithm.crossover;

import com.geneticalgorithm.beans.Individual;
import com.geneticalgorithm.interfaces.CrossOver;

/**
 * Implements whole arithmetic recombination Cross Over
 * The genes of the new individual are weighted sum of
 * parents genes.
 *
 * @author Jose Gonzalez
 */
public class WholeArithmeticRecombinationCrossOver implements CrossOver {
    private double weight;

    public WholeArithmeticRecombinationCrossOver(double weight){
        this.weight = weight;
    }

    @Override
    public Individual crossOver(Individual father, Individual mother) {
        Individual offspring = new Individual(father.size(),father.getFitnessCalc());

        for (int i = 0; i < offspring.size(); i++) {
            offspring.setGene(i, (int)(this.weight * Double.valueOf(father.getGene(i).getValue().toString()) + (1-this.weight) * Double.valueOf(mother.getGene(i).getValue().toString())));
        }
        return offspring;
    }

}
