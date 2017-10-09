package com.geneticalgorithm.parentselection;

import com.geneticalgorithm.beans.Individual;
import com.geneticalgorithm.beans.Population;
import com.geneticalgorithm.interfaces.ParentSelection;
import com.geneticalgorithm.utils.SortIndividualByFitness;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * exponentiallyOfSelectionMethod must be between 0 and 1
 * @author Jose Gonzalez
 */
public class RankSelection implements ParentSelection {
    private int selectedPopulationSize;
    private double exponentiallyOfSelectionMethod;
    private Map<Integer, Double> individualsSelectionWeights = new HashMap<>();


    public RankSelection(int selectedPopulationSize, double exponentiallyOfSelectionMethod) {
        this.selectedPopulationSize = selectedPopulationSize;
        this.exponentiallyOfSelectionMethod = exponentiallyOfSelectionMethod;
        this.assignSelectionProbabilitiesToRankPositions();
    }

    @Override
    public Individual selection(Population population) {
        //Population selectedPopulation = new Population(this.selectedPopulationSize, false);
        Arrays.sort(population.getIndividuals(), new SortIndividualByFitness());

        int index = 1;
        double cumulativeSumOfProbabilities=0;
        double randomNumber = ThreadLocalRandom.current().nextDouble(0, 1);

        while (cumulativeSumOfProbabilities < randomNumber) {
            cumulativeSumOfProbabilities += this.individualsSelectionWeights.get(index);
            if (cumulativeSumOfProbabilities< randomNumber) index++;
        }

        return population.getIndividuals()[index];
    }

    
    /**
     *  Method that assigns selection probabilities to the different ranking positions
     *
     *  This makes sure that the lowest probability is assigned to the individual  ranked the lowest
     *  and the highest probability to the individual ranked the highest.
     *  It is assumed that the lowest ranking actually maps to a high value. For instance, rank 1 is the highest rank
     *  and rank 6 is a lowest rank in ranking made of 6 individuals.
     *  The sum of all probabilities is 1.
     *  */
    private void assignSelectionProbabilitiesToRankPositions(){
        double normalizationFactor = 0;

        for (int i = 1; i <= this.selectedPopulationSize; i++) {
            normalizationFactor += selectionNumerator(i);
        }
        for (int i = 1; i <= this.selectedPopulationSize; i++) {
            this.individualsSelectionWeights.put(this.selectedPopulationSize -(i-1), selectionNumerator(i)/ normalizationFactor);
        }
    }

    private double selectionNumerator(int ranking){
        return Math.pow(this.exponentiallyOfSelectionMethod, this.selectedPopulationSize * ranking);
    }

}


