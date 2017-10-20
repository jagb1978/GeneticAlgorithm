package com.geneticalgorithm.randomgenerators;

import com.geneticalgorithm.interfaces.RandomGenerator;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Integer Random Generator
 *
 * @author Jose Gonzalez
 */
public class IntRandomGenerator implements RandomGenerator {
    private Integer minLimit;
    private Integer maxLimit;

    public IntRandomGenerator(Integer minLimit, Integer maxLimit){
        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
    }

    @Override
    public Integer generateRandom() {
        return  ThreadLocalRandom.current().nextInt(this.minLimit, this.maxLimit );
    }
}
