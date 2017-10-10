package com.trading.simulation;

import com.trading.beans.DataPoint;
import com.trading.interfaces.Strategy;
import com.trading.utils.TimeSeriesUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Jose Gonzalez
 */
public class Simulation {
    List<DataPoint> dataPointList;
    Strategy strategy;

    public Simulation(Strategy strategy) {
        try {
            this.dataPointList = TimeSeriesUtils.createDataPoints(
                    new File("/Users/josealfredogonzalez/IdeaProjects/GeneticAlgorithm/projects/src/SecuritiesDataTable.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.strategy = strategy;
    }

    public void simulate(){
        for(DataPoint dataPoint: this.dataPointList){
            this.strategy.updateStrategy(dataPoint);
        }
        this.strategy.tradeOutRemainingPosition();
    }

    public double getTotalPnl(){
        return  this.strategy.getTotalPnl();
    }

}
