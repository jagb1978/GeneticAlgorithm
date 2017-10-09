package com.geneticalgorithm.fitnesscalculator;

import com.geneticalgorithm.beans.Individual;
import com.geneticalgorithm.interfaces.FitnessCalculator;
import com.trading.interfaces.Strategy;
import com.trading.simulation.Simulation;
import com.trading.strategies.EmaCrossOver;

public class MaxPnlFitnessCalc implements FitnessCalculator {

    public int getFitnessValue(Individual individual) {
        int fitness = 0;

        Strategy strategy = new EmaCrossOver(individual.getGene(0),individual.getGene(1), 1);
        Simulation simulation = new Simulation(strategy);

        return (int)Math.round(simulation.getTotalPnl()*1000000);
    }


}
