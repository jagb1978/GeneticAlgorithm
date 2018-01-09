package com.geneticprogramming.beans;

import com.geneticprogramming.exceptions.NoIndividualsException;
import com.geneticprogramming.utils.NodeManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jose Gonzalez
 */
public class Population {
    private List<Individual> individualsList = new ArrayList<>();
    private Individual fittestIndividual;
    private double averagePopulationFitness;

    public void add(Individual individual) {
        this.individualsList.add(individual);
        //this.individualsList.get(this.individualsList.size() - 1).setPositionInPopulation(this.individualsList.size() - 1);
    }

    public void generatePopulationStats() throws NoIndividualsException {
        if (!this.individualsList.isEmpty()) {

            Individual fittest = this.individualsList.get(0);
            double maxFitness = 0;
            double fitnessSum = 0;

            for (Individual individual : this.individualsList) {
                fitnessSum += individual.getFitness();
                if (individual.getFitness() > maxFitness) {
                    fittest = individual;
                }
            }
            this.fittestIndividual = fittest;
            this.averagePopulationFitness = fitnessSum / this.size();

        } else {
            throw new NoIndividualsException("There are no individuals in the population");
        }
    }

    public int size() {
        return this.individualsList.size();
    }

    public Individual getFittestIndividual() {
        return fittestIndividual;
    }

    public double getAveragePopulationFitness() {
        return averagePopulationFitness;
    }

    public Individual getIndividual(int index){
        return this.individualsList.get(index);
    }

    public Individual setIndividual(int index, Individual individual){
        return  this.individualsList.set(index, individual);
    }

    public double avgNodeLength(NodeManager nodeManager){
        return this.individualsList.stream().mapToDouble(individual ->
            nodeManager.getNodeLength(individual,0)
        ).average().orElse(0.0);
    }

}
