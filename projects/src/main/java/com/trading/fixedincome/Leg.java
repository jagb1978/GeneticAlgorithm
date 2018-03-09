package com.trading.fixedincome;

import com.trading.beans.Instrument;

/**
 * This class contains the information related to the leg of an spread
 */
public class Leg {
    private Instrument instrument;
    private double quantity;

    public Leg(Instrument instrument, double quantity){
        this.instrument = instrument;
        this.quantity = quantity;
    }

    /** Getters */

    public double getQuantity() {
        return quantity;
    }

    public Instrument getInstrument() {
        return instrument;
    }
}
