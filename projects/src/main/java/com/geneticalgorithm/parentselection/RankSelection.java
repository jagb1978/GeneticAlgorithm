package com.geneticalgorithm.parentselection;

import com.geneticalgorithm.beans.Individual;
import com.geneticalgorithm.beans.Population;
import com.geneticalgorithm.utils.SortIndividualByFitness;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Jose Gonzalez
 */
public class RankSelection {

    private Individual selectIndividual(Population population) {

        // Population tournament = new Population(this.tournamentSize, false);
//        Arrays.sort(tournament.getIndividuals(), new SortIndividualByFitness());
//
//        for (int i = 0; i < this.tournamentSize; i++) {
//            int randomId = ThreadLocalRandom.current().nextInt(0, population.getIndividuals().length);
//            tournament.getIndividuals()[i] = population.getIndividuals()[randomId];
//        }
//        return tournament.getFittest();
        //  }
    }

}
