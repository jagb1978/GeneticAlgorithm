package com.trading.indicators;


import com.trading.beans.DataPoint;

/**
 * @author JoseGonzalez
 */
public class KstIndicator {
    private int rocOnePeriod=10;
    private int rocTwoPeriod=15;
    private int rocThreePeriod=20;
    private int rocFourPeriod=30;
    private int emaSignalPeriod=9;

    private RateOfChange rocOne;
    private RateOfChange rocTwo;
    private RateOfChange rocThree;
    private RateOfChange rocFour;

    private int rocOneEmaPeriod = 10;
    private int rocTwoEmaPeriod = 10;
    private int rocThreeEmaPeriod = 10;
    private int rocFourEmaPeriod = 15;

    private int rocOneFactor = 1;
    private int rocTwoFactor = 2;
    private int rocThreeFactor = 3;
    private int rocFourFactor = 4;

    private Ema rocOneEma;
    private Ema rocTwoEma;
    private Ema rocThreeEma;
    private Ema rocFourEma;

    private Ema signal;
    private Double kstIndicator;



    public KstIndicator(int emaSignalPeriod) {
        this.rocOneEma =new Ema(rocOneEmaPeriod);
        this.rocTwoEma=new Ema(rocTwoEmaPeriod);
        this.rocThreeEma=new Ema(rocThreeEmaPeriod);
        this.rocFourEma=new Ema(rocFourEmaPeriod);

        this.emaSignalPeriod=emaSignalPeriod;
        this.signal=new Ema(this.emaSignalPeriod);

        this.rocOne= new RateOfChange(rocOnePeriod);
        this.rocTwo= new RateOfChange(rocTwoPeriod);
        this.rocThree= new RateOfChange(rocThreePeriod);
        this.rocFour= new RateOfChange(rocFourPeriod);
    }

    public void calculateKstIndicator(DataPoint dataPoint){

        this.rocOne.calculateRateOfChange(dataPoint);
        this.rocTwo.calculateRateOfChange(dataPoint);
        this.rocThree.calculateRateOfChange(dataPoint);
        this.rocFour.calculateRateOfChange(dataPoint);

        this.rocOneEma.calculateEma(this.rocOne.getRateOfChange());
        this.rocTwoEma.calculateEma(this.rocTwo.getRateOfChange());
        this.rocThreeEma.calculateEma(this.rocThree.getRateOfChange());
        this.rocFourEma.calculateEma(this.rocFour.getRateOfChange());

        try {
            this.kstIndicator = rocOneFactor * this.rocOneEma.getEma() +
                    rocTwoFactor * this.rocTwoEma.getEma() +
                    rocThreeFactor * this.rocThreeEma.getEma() +
                    rocFourFactor * this.rocFourEma.getEma();

            this.signal.calculateEma(this.kstIndicator);
        }catch(java.lang.NullPointerException e){
            this.kstIndicator=null;
        }
    }

    public Double getKstIndicator() {
        return this.kstIndicator;
    }

    public Double getSignal(){
        return this.signal.getEma();
    }

}
