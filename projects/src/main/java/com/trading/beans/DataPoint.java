package com.trading.beans;

import com.trading.interfaces.Data;

import java.time.LocalDateTime;

/**
 * Simple bean containing data point information
 *
 * @author JoseGonzalez
 */
public class DataPoint implements Data {
    private Double price;
    private LocalDateTime localDateTime = null;
    private Instrument instrument;

    public DataPoint() {
    }

    public DataPoint(Double price) {
        this.price = price;
    }

    public DataPoint(Instrument instrument, Double price) {
        this.instrument = instrument;
        this.price = price;
    }

    public DataPoint(Instrument instrument, Double price, LocalDateTime localDateTime) {
        this.instrument = instrument;
        this.price = price;
        this.localDateTime = localDateTime;
    }

    /**
     * Getters and Setters
     */

    public Instrument getInstrument() {
        return instrument;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public String toString() {

        return localDateTime != null ? this.localDateTime + "," + this.price.toString() : "NA," + this.price.toString();
    }


}
