package com.geneticalgorithm.fitnesscalculator;

import com.geneticalgorithm.beans.Individual;
import com.geneticalgorithm.interfaces.FitnessCalculator;
import com.trading.interfaces.Strategy;
import com.trading.simulation.Simulation;
import com.trading.strategies.EmaCrossOver;

/**
 * Calculates chromosome fitness based on
 * its pnl value.
 *
 * @author Jose Gonzalez
 */
public class MaxPnlFitnessCalc implements FitnessCalculator {
    private String dataFilePath;
    private Simulation simulation;

    public MaxPnlFitnessCalc (String dataFilePath){
        this.dataFilePath = dataFilePath;
        this.simulation = new Simulation(this.dataFilePath);
    }

    public Double getFitnessValue(Individual individual) {
        Strategy strategy = new EmaCrossOver(Math.round(Double.valueOf(individual.getGene(0).getValue().toString()).longValue()),
                Math.round(Double.valueOf(individual.getGene(1).getValue().toString()).longValue()), 1);
        this.simulation.setStrategy(strategy);
        this.simulation.simulate();

        return (double)Math.round(simulation.getTotalPnl()*100);
    }
}
