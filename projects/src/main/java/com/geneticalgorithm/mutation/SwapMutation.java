package com.geneticalgorithm.mutation;

import com.geneticalgorithm.beans.Gene;
import com.geneticalgorithm.beans.Individual;
import com.geneticalgorithm.interfaces.Mutation;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jose Gonzalez
 */
public class SwapMutation<T> implements Mutation {
    private int minLimit;
    private int maxLimit;
    private int numberOfGenesToMutate;

    public SwapMutation(int numberOfGenesToMutate, int minLimit, int maxLimit){
        this.numberOfGenesToMutate = numberOfGenesToMutate;
        this.maxLimit = maxLimit;
        this.minLimit = minLimit;
    }

    /**
     * Method that mutates individuals
     * @param individual
     */
    @Override
    public void mutate(Individual individual) {
        for(int i =0 ; i< this.numberOfGenesToMutate; i++) {
            int position = ThreadLocalRandom.current().nextInt(0, individual.size());
            individual.setRandomGene(position , individual.getGene(position).getGeneType());
        }
    }

}
