package com.trading.strategies;

import com.trading.beans.DataPoint;
import com.trading.beans.Trade;
import com.trading.enums.ActionEnum;
import com.trading.enums.MarketState;
import com.trading.indicators.Ema;
import com.trading.interfaces.Strategy;
import java.util.ArrayList;
import java.util.List;

public class EmaCrossOver implements Strategy {
    private Ema shortEma;
    private Ema longEma;
    private MarketState marketState;
    private List<Trade> tradeList = new ArrayList<>();
    private double quantity;
    private DataPoint currentDataPoint;

    public EmaCrossOver(int shortEmaPeriod, int longEmaPeriod, double quantity) {
        this.longEma = new Ema(longEmaPeriod);
        this.shortEma = new Ema(shortEmaPeriod);
        this.quantity = quantity;
    }
    public EmaCrossOver(double quantity) {
        this.longEma = new Ema();
        this.shortEma = new Ema();
        this.quantity = quantity;
    }

    @Override
    public void updateStrategy(DataPoint dataPoint) {
        this.currentDataPoint = dataPoint;
        this.longEma.calculateEma(this.currentDataPoint.getPrice());
        this.shortEma.calculateEma(this.currentDataPoint.getPrice());
        this.tradingDecisionMaking();
    }

    private MarketState getMarketState() {
        if (this.shortEma.getEma() > this.longEma.getEma()) {
            this.marketState = MarketState.UPWARD_TRENDING;
        } else if (this.shortEma.getEma() < this.longEma.getEma()) {
            this.marketState = MarketState.DOWNWARD_TRENDING;
        } else {
            this.marketState = MarketState.UNKNOWN;
        }
        return this.marketState;
    }

    private void tradingDecisionMaking(){
        double currentPosition = this.tradeList.stream().mapToDouble(Trade::getQuantity).sum();

        if (getMarketState() == MarketState.UPWARD_TRENDING && currentPosition <= 0) {
            Trade trade = new Trade(this.currentDataPoint.getDate(), this.currentDataPoint.getPrice(), ActionEnum.BUY, this.quantity);
            this.tradeList.add(trade);
        } else if (getMarketState() == MarketState.UPWARD_TRENDING && currentPosition >= 0){
            Trade trade = new Trade(this.currentDataPoint.getDate(), this.currentDataPoint.getPrice(), ActionEnum.SELL, -this.quantity);
            this.tradeList.add(trade);
        }
    }

    public void tradeOutRemainingPosition(){
        double currentPosition = this.tradeList.stream().mapToDouble(Trade::getQuantity).sum();

        if(currentPosition>0) {
            Trade trade = new Trade(this.currentDataPoint.getDate(), this.currentDataPoint.getPrice(), ActionEnum.BUY, -currentPosition);
            this.tradeList.add(trade);
        } else if(currentPosition<0){
            Trade trade = new Trade(this.currentDataPoint.getDate(), this.currentDataPoint.getPrice(), ActionEnum.SELL, -currentPosition);
            this.tradeList.add(trade);
        }
    }

    @Override
    public double getTotalPnl(){
        return -this.tradeList.stream().mapToDouble(Trade::getTradeValue).sum();
    }

    public Ema getShortEma() {
        return shortEma;
    }

    public Ema getLongEma() {
        return longEma;
    }

}
