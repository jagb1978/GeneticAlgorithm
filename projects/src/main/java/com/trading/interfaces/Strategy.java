package com.trading.interfaces;


import com.trading.beans.DataPoint;

/**
 * Strategy interface
 * @author Jose Gonzalez
 */
public interface Strategy {

    public void updateStrategy(DataPoint dataPoint);
    public double getTotalPnl();
    public void tradeOutRemainingPosition();
}
