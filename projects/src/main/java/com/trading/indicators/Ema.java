package com.trading.indicators;

/**
 *
 * @author Jose Gonzalez
 */
public class Ema {
    private int emaPeriod;
    private double multiplier;
    private Double ema=null;
    private Double previousEma=null;

    public Ema(){
    }

    public Ema(int emaPeriod){
        this.emaPeriod=emaPeriod;
        this.multiplier = (2 / ((double) this.emaPeriod + 1));
    }

    public void calculateEma(Double value){
        if(value!=null) {
            if (this.ema == null) this.ema = value;
            if (this.previousEma == null) this.previousEma = value;
            this.ema = this.previousEma + this.multiplier * (value - this.previousEma);
            this.previousEma = this.ema;
        }
    }

    public Double getEma() {
        return ema;
    }

    public void setEmaPeriod(int emaPeriod) {
        this.emaPeriod = emaPeriod;
        this.multiplier = (2 / ((double) this.emaPeriod + 1));
    }


}
