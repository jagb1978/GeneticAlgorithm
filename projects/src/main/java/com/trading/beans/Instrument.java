package com.trading.beans;

import java.time.LocalDateTime;

/**
 * This class contains the details of an Instrument Characteristics
 * @author Jose Gonzalez
 */
public class Instrument {
    private String feedCode;

    public Instrument (String feedCode){
        this.feedCode = feedCode;
    }

    public String getFeedCode() {
        return feedCode;
    }


}
