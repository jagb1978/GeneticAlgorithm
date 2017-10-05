package com.geneticalgorithm.mutation;

import com.geneticalgorithm.beans.Individual;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Math.round;

/**
 * Select one or more random genes and flips them
 *
 * @author Jose Gonzalez
 */
public class BitFlipMutation {
    private double mutationRate;

    public BitFlipMutation(double mutationRate) {
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
    private void mutate(Individual individual) {

        for (int i = 0; i < individual.size(); i++) {
            if (Math.random() <= this.mutationRate) {
                int gene = (int) Math.round(Math.random());
                individual.setGene(i, gene);
            }
        }
    }


}
