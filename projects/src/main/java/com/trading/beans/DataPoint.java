package com.trading.beans;

import java.time.LocalDate;

/**
 *
 * @author JoseGonzalez
 *
 */
public class DataPoint {
    private Double price;
    private LocalDate date=null;

    public DataPoint(Double price){
        this.price=price;
    }

    public DataPoint(){
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String toString(){

        return date!=null? this.date+","+ this.price.toString(): "NA,"+this.price.toString();
    }



}
