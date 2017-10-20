package com.geneticalgorithm.randomgenerators;

import com.geneticalgorithm.interfaces.RandomGenerator;

/**
 * Bit Random Generator
 * @author Jose Gonzalez
 */
public class BitRandomGenerator implements RandomGenerator {
    @Override
    public Integer generateRandom() {
            return (int) Math.round(Math.random());
    }
}
