package com.geneticalgorithm.randomgenerators;

import com.geneticalgorithm.interfaces.RandomGenerator;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Double RandomGenerator
 *
 * @author Jose Gonzalez
 */
public class DoubleRandomGenerator implements RandomGenerator{
    private Double minLimit;
    private Double maxLimit;

    public DoubleRandomGenerator(Double minLimit, Double maxLimit){
        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
    }

    @Override
    public Double generateRandom() {
        return  ThreadLocalRandom.current().nextDouble(this.minLimit, this.maxLimit );
    }
}
