package com.trading.beans;

import com.trading.enums.ActionEnum;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author JoseGonzalez
 */
public class Trade {
    private LocalDateTime date;
    private double price;
    private ActionEnum action;
    private double quantity;

    public Trade(LocalDateTime date, double price , ActionEnum action, double quantity){
        this.date=date;
        this.price=price;
        this.action=action;
        this.quantity = quantity;
    }

    public ActionEnum getAction() {
        return action;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getTradeValue(){
       return this.quantity * this.price;
    }

}
