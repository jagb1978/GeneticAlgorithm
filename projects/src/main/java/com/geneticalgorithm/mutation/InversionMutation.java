package com.geneticalgorithm.mutation;

import com.geneticalgorithm.beans.Gene;
import com.geneticalgorithm.beans.Individual;
import com.geneticalgorithm.interfaces.Mutation;

/**
 * @author Jose Gonzalez
 */
public class InversionMutation implements Mutation {
    private int minLimit;
    private int maxLimit;
    private int begginingRange;
    private int endRange;

    public InversionMutation( int minLimit, int maxLimit, int beginningRange, int endRange){
        this.maxLimit = maxLimit;
        this.minLimit = minLimit;
        this.begginingRange = beginningRange;
        this.endRange = endRange;
    }

    @Override
    public void mutate(Individual individual) {
        int counter = 0;
        for(int i= this.begginingRange; i< this.endRange +1; i++) {
            individual.setGene(i , new Gene( individual.getGene(this.endRange  - counter)));
            counter ++;
        }
    }

}
