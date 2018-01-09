package com.trading.interfaces;

import com.trading.beans.DataPoint;

/**
 * Indicator interface
 * @author Jose Gonzalez
 */
public interface Indicator {
    double getValue(DataPoint dataPoint);
}
