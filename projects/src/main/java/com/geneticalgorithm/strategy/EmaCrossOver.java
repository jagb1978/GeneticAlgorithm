package com.geneticalgorithm.strategy;

import com.geneticalgorithm.Enums.MarketState;
import com.geneticalgorithm.beans.DataPoint;
import com.geneticalgorithm.indicators.Ema;
import com.geneticalgorithm.interfaces.Strategy;

public class EmaCrossOver implements Strategy{
    private Ema shortEma;
    private Ema longEma;
    private MarketState marketState;

    public EmaCrossOver(int shortEmaPeriod, int longEmaPeriod){
        this.longEma = new Ema(longEmaPeriod);
        this.shortEma = new Ema(shortEmaPeriod);
    }

    @Override
    public void updateStrategy(DataPoint dataPoint){
        this.longEma.calculateEma(dataPoint.getPrice());
        this.shortEma.calculateEma(dataPoint.getPrice());
    }

    public MarketState getMarketState(){
        if(this.shortEma.getEma() > this.longEma.getEma()){
            this.marketState = MarketState.UPWARD_TRENDING;
        } else if(this.shortEma.getEma() < this.longEma.getEma()){
            this.marketState = MarketState.DOWNWARD_TRENDING;
        } else{
            this.marketState = MarketState.UNKNOWN;
        }
        return this.marketState;
    }

}
