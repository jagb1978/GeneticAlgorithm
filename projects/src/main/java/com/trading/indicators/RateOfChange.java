package com.trading.indicators;

import com.trading.beans.DataPoint;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JoseGonzalez
 */
public class RateOfChange {

    private int period;
    private Double rateOfChange;
    private double currentPrice;
    private double previousPrice;
    private int dataPointCounter = 0;
    private boolean initialized = false;
    private List<Double> priceList = new ArrayList<>();

    public RateOfChange(int period) {
        this.period = period;
    }

    public void calculateRateOfChange(DataPoint dataPoint) {

        Double price = dataPoint.getPrice();
        this.priceList.add(price);
        this.currentPrice=price;

        if (this.priceList.size() > this.period) {
            this.priceList.remove(0);

            this.rateOfChange = (this.currentPrice / this.priceList.get(0) - 1) * 100;
        }
    }

    public Double getRateOfChange() {
        return rateOfChange;
    }


}
