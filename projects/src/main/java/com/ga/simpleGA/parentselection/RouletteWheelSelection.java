package com.ga.simpleGA.parentselection;
import com.ga.simpleGA.beans.Individual;
import com.ga.simpleGA.beans.Population;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jose Gonzalez
 */
public class RouletteWheelSelection {

    /**
     * Selects a parent based on Roulette Wheel Selection
     * the higher the fitness the larger the "bucket" likelihood of
     * being selected. The
     *
     * @param population
     * @return Selected Individual
     */
    private Individual selectParent(Population population) {
        double totalPopulationFitness = Arrays.stream(population.getIndividuals()).mapToDouble(Individual::getFitness).sum();
        double randomNumber = ThreadLocalRandom.current().nextDouble(0, totalPopulationFitness);

        double cumulativeSumOfFitness = 0;
        int index = 0;

        while (cumulativeSumOfFitness < randomNumber) {
            cumulativeSumOfFitness += population.getIndividuals()[index].getFitness();
            if (cumulativeSumOfFitness < randomNumber) index++;
        }

        return population.getIndividuals()[index];
    }


}
