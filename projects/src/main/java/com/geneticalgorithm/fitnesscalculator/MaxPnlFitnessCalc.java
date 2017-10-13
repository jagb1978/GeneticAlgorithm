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

    public int getFitnessValue(Individual individual) {
        Strategy strategy = new EmaCrossOver((Integer)individual.getGene(0).getValue(),
                (Integer)individual.getGene(1).getValue(), 1);
        this.simulation.setStrategy(strategy);
        simulation.simulate();

        return (int)Math.round(simulation.getTotalPnl()*100);
    }
}
