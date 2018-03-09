package com.trading.utils;

import com.trading.beans.DataPoint;
import java.util.Comparator;

/**
 * DataPoint Comparator
 *
 * @author Jose Gonzalez
 */
public class DataPointComparator implements Comparator<DataPoint> {
    @Override
    public int compare(DataPoint o1, DataPoint o2) {
        return Double.compare(o1.getPrice(), o2.getPrice());
    }
}
