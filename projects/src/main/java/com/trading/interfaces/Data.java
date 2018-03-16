package com.trading.interfaces;

import com.trading.beans.DataPoint;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Data Data
 * @author Jose Gonzalez
 */
public interface Data {
    LocalDateTime getLocalDateTime();
    default List<DataPoint> getData(){return null;}
}
