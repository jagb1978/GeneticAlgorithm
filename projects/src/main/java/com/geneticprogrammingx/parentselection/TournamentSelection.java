package com.geneticprogrammingx.parentselection;

import java.util.Random;

public class TournamentSelection {
    private Random random= new Random();
    private int populationSize;


    public TournamentSelection(int populationSize){
     this.populationSize = populationSize;
    }

    public int selection(double[] fitness, int tournamentSize) {
        int bestIndividual = this.random.nextInt(this.populationSize);
        int competitor;
        double fitnessBest = -1.0e34; // Hard Coded Value Fix!!!!!!

        for (int i = 0; i < tournamentSize; i++) {
            competitor = this.random.nextInt(this.populationSize);
            if (fitness[competitor] > fitnessBest) {
                fitnessBest = fitness[competitor];
                bestIndividual = competitor;
            }
        }
        return bestIndividual;
    }

    public int negativeSelection(double[] fitness, int tournamentSize) {
        int worstIndividual = this.random.nextInt(this.populationSize);
        int competitor;
        double fitnessWorst = 1e34;

        for (int i = 0; i < tournamentSize; i++) {
            competitor = this.random.nextInt(this.populationSize);

            if (fitness[competitor] < fitnessWorst) {
                fitnessWorst = fitness[competitor];
                worstIndividual = competitor;
            }
        }
        return worstIndividual;
    }


}
