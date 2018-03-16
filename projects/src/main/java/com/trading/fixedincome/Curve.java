package com.trading.fixedincome;

import com.trading.beans.DataPoint;
import com.trading.interfaces.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *  This class holds the  price information for all the tenors of the interest rate curve
 *
 * @author Jose Gonzalez
 */
public class Curve implements Data {
    private LocalDateTime localDateTime;
    private List<DataPoint> dataPointList = new ArrayList<>();

    public Curve(LocalDateTime localDateTime){
        this.localDateTime = localDateTime;
    }

    public Curve(LocalDateTime localDateTime, List<DataPoint> dataPointList){
        this(localDateTime);
        this.dataPointList = dataPointList;
    }

    public void addDataPoint(DataPoint dataPoint){
        this.dataPointList.add(dataPoint);
    }

    /** Getters */

    @Override
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    @Override
    public List<DataPoint> getData() {
        return dataPointList;
    }


}
