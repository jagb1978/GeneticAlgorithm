package com.trading.fixedincome;

import com.trading.interfaces.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class holds the time series of the curves
 *
 * @author Jose Gonzalez
 */
public class TimeSeries {
    private Map<LocalDateTime, Data> curveTimeSeries = new HashMap<>();

    public TimeSeries(){}

    public TimeSeries(List<Data> curveList){
        curveList.forEach(this::addCurve);
    }

    public void addCurve(Data curve){
        this.curveTimeSeries.put(curve.getLocalDateTime(), curve);
    }

    public void addAllCurves(List<Data> curveList){
        curveList.forEach(this::addCurve);
    }

    /** Getter */

    public Map<LocalDateTime, Data> getCurveTimeSeries() {
        return curveTimeSeries;
    }

}
