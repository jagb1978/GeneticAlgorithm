package com.geneticprogrammingx.interfaces;

/**
 * Fitness Function Interface
 *
 * @author Jose Gonzalez
 */
public interface FitnessFunction {
   double getFitness(char[] program);
   int printIndividual(char[] individual, int nodeCount);
}
