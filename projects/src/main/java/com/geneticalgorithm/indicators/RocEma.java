package com.geneticalgorithm.indicators;

/**
 * @author Jose Gonzalez
 */
public class RocEma {
    private int rocPeriod;
    private Ema rocEma;
    private int emaPeriod;
    private int dataPointCounter = 0;
    private boolean initialized = false;
    private int warmUpPeriod;

    public RocEma(int rocPeriod, int emaPeriod) {
        this.rocPeriod = rocPeriod;
        this.emaPeriod = emaPeriod;
        this.rocEma = new Ema(this.emaPeriod);
        this.warmUpPeriod = rocPeriod;
    }

    public void calculateRocEma(double rateOfChange) {

        if (this.dataPointCounter == this.rocPeriod) {
            this.rocEma.calculateEma(rateOfChange);
            this.dataPointCounter = 0;
        }
        this.dataPointCounter++;
    }

    public Double getRocEmaValue(){
        return rocEma.getEma();
    }

}
