package com.geneticalgorithm.parentselection;

import com.geneticalgorithm.beans.Individual;
import com.geneticalgorithm.beans.Population;
import com.geneticalgorithm.interfaces.ParentSelection;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jose Gonzalez
 */
public class RandomSelection implements ParentSelection {

    @Override
    public Individual selection(Population population) {
        int randomNumber = ThreadLocalRandom.current().nextInt(0, population.getIndividuals().length);
        return population.getIndividuals()[randomNumber];
    }


}



