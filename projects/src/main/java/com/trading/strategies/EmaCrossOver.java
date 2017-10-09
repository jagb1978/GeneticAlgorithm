package com.trading.strategies;

import com.trading.enums.MarketState;
import com.trading.indicators.Ema;
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
    public void updateStrategy(double price){
        this.longEma.calculateEma(price);
        this.shortEma.calculateEma(price);
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
