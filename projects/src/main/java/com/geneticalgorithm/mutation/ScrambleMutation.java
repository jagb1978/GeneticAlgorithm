package com.geneticalgorithm.mutation;

import com.geneticalgorithm.beans.Individual;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Implements Scramble Permutation.
 * A subset of genes is chosen and their values are scrambled or shuffled randomly
 *
 * @author Jose Gonzalez
 */
public class ScrambleMutation implements Mutation {
    private int minLimit;
    private int maxLimit;
    private int begginingRange;
    private int endRange;

    public ScrambleMutation( int minLimit, int maxLimit, int beginningRange, int endRange){
        this.maxLimit = maxLimit;
        this.minLimit = minLimit;
        this.begginingRange = beginningRange;
        this.endRange = endRange;
    }

    @Override
    public void mutate(Individual individual) {
        for(int i= this.begginingRange; i< this.endRange +1; i++) {
            int geneValue =   ThreadLocalRandom.current().nextInt(this.minLimit, this.maxLimit );
            individual.setGene(i , geneValue);
        }
    }

}
