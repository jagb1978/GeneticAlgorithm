package com.geneticalgorithm.mutation;

import com.geneticalgorithm.beans.Individual;
import com.geneticalgorithm.interfaces.Mutation;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jose Gonzalez
 */
public class RandomSettingMutation implements Mutation {
    private int minLimit;
    private int maxLimit;
    private double mutationRate;

    public RandomSettingMutation(int minLimit, int maxLimit, double mutationRate){
        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
        this.mutationRate = mutationRate;
    }

    /**
     * Method that mutates individuals
     * 1. It firstly decides whether to mutate given the mutation rate parameter or probability
     * 2. It then generates a gene value
     * 3. Sets the new gene value in the individual
     *
     * @param individual
     */
    @Override
    public void mutate(Individual individual) {
        for (int i = 0; i < individual.size(); i++) {
            if (Math.random() <= this.mutationRate) {
                int gene =   ThreadLocalRandom.current().nextInt(this.minLimit, this.maxLimit );
                individual.setGene(i, gene);
            }
        }
    }
}
