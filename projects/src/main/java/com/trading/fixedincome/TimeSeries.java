package com.trading.fixedincome;

import com.trading.interfaces.DatedAndTimedData;

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
    private Map<LocalDateTime, DatedAndTimedData> curveTimeSeries = new HashMap<>();

    public TimeSeries(){}

    public TimeSeries(List<DatedAndTimedData> curveList){
        curveList.forEach(this::addCurve);
    }

    public void addCurve(DatedAndTimedData curve){
        this.curveTimeSeries.put(curve.getLocalDateTime(), curve);
    }

    public void addAllCurves(List<DatedAndTimedData> curveList){
        curveList.forEach(this::addCurve);
    }

    /** Getter */

    public Map<LocalDateTime, DatedAndTimedData> getCurveTimeSeries() {
        return curveTimeSeries;
    }

}
