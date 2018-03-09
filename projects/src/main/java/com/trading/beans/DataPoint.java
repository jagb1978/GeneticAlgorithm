package com.trading.beans;

import com.trading.interfaces.DatedAndTimedData;

import java.time.LocalDateTime;

/**
 * Simple bean containing data point information
 *
 * @author JoseGonzalez
 */
public class DataPoint implements DatedAndTimedData {
    private Double price;
    private LocalDateTime LocalDateTime =null;
    private Instrument instrument;

    public DataPoint(){}

    public DataPoint(Double price){
        this.price=price;
    }

    public DataPoint(Instrument instrument,Double price){
        this.instrument = instrument;
        this.price=price;
    }

    /**   Getters and Setters    */

    public Instrument getInstrument() {
        return instrument;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getLocalDateTime() {
        return LocalDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.LocalDateTime = localDateTime;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public String toString(){

        return LocalDateTime !=null? this.LocalDateTime +","+ this.price.toString(): "NA,"+this.price.toString();
    }



}
