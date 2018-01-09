package com.geneticprogramming.algorithm;

import com.geneticprogramming.beans.PopulationManager;
import com.geneticprogramming.beans.SimulationInfo;
import com.geneticprogramming.beans.TrainData;
import com.geneticprogramming.crossover.Crossover;
import com.geneticprogramming.fitnessfunctions.PnlFitness;
import com.geneticprogramming.mutation.Mutation;
import com.geneticprogramming.utils.NodeManager;
import com.trading.beans.DataPoint;
import com.trading.utils.TimeSeriesUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Jose Gonzalez
 */
public class TradingGeneticProgramming extends GeneticProgrammingBase {
    private List<DataPoint> dataPointList;

    public TradingGeneticProgramming(String fileName, SimulationInfo simulationInfo) {
        try {
            FUNCTION_SET_START = 7;
            FUNCTION_SET_END = 11;
            this.simulationInfo = simulationInfo;
            this.fitnessVector = new double[this.simulationInfo.getPopulationSize()];
            this.dataPointList = TimeSeriesUtils.createDataPoints(new File(fileName));
            this.trainData = new TrainData.Builder()
                    .varNumber(5)
                    .randomNumber(0)
                    .minRandom(0)
                    .maxRandom(0)
                    .numberOfDataPoints(this.dataPointList.size())
                    .build();
            this.terminationCriteria = simulationInfo.getTerminationCriteria();
            this.nodeManager = new NodeManager(FUNCTION_SET_START, FUNCTION_SET_END, this.trainData.getNumberOfPrimitives());
            this.crossover = new Crossover(this.nodeManager);
            this.mutation = new Mutation(this.nodeManager, this.simulationInfo.getMutationProbability());
            this.fitnessFunction = new PnlFitness(dataPointList);
            this.populationManager = new PopulationManager(this.nodeManager, this.simulationInfo.getMaximumLength(), this.fitnessFunction, this.simulationInfo.getPopulationSize());
            this.population = this.populationManager.createRandomPopulation(this.simulationInfo.getPopulationSize(), simulationInfo.getInitialProgramMaxDepth(), this.fitnessVector);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
