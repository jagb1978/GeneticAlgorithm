package com.trading.fixedincome;

import com.trading.beans.DataPoint;
import com.trading.beans.Instrument;
import com.trading.interfaces.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * This class calculates the Errors or the difference between the
 * theoretical curve and the actual curve
 *
 * @author Jose Gonzalez
 */
public class ErrorsManager {
    private TimeSeries theoSpreadsCurveTimeSeries;
    private TimeSeries actualSpreadsCurveTimeSeries;
    private TimeSeries errorsTimeSeries;

    public ErrorsManager(TimeSeries dataPointsTimeSeries, List<Spread> spreadsList) {
        this.transformDataPointsSeriesToSpreadSeries(dataPointsTimeSeries, spreadsList);
    }


    private void transformDataPointsSeriesToSpreadSeries(TimeSeries actualCurveTimeSeries, List<Spread> spreadsList) {
        this.actualSpreadsCurveTimeSeries = this.getSpreadsTimeSeriesFromRegularPointsInTheCurve(actualCurveTimeSeries,spreadsList);
        this.theoSpreadsCurveTimeSeries = getTheoSpreadsTimeSeriesFromPCA(actualCurveTimeSeries, spreadsList);
    }

//    private TimeSeries getErrorsTimeSeries( List<Spread> spreadsList){
//
//        this.actualSpreadsCurveTimeSeries.getCurveTimeSeries().keySet().forEach(localDateTime -> {
//            Curve curve = new Curve(localDateTime);
//
//        });
//
//    }



    /**
     * Creates a series of spreads in accordance to the list of spread definitions provided
     *
     * @param actualCurveTimeSeries
     * @param spreadsList
     * @return
     */
    private TimeSeries getSpreadsTimeSeriesFromRegularPointsInTheCurve(TimeSeries actualCurveTimeSeries, List<Spread> spreadsList){
        TimeSeries spreadsCurveTimeSeries = new TimeSeries();

        actualCurveTimeSeries.getCurveTimeSeries().values().forEach(regularInstrumentsCurve -> {

            Curve spreadsCurve = this.getSpreadCurveFromRegularInstrumentsCurve(regularInstrumentsCurve, spreadsList);

            spreadsCurveTimeSeries.getCurveTimeSeries().put(spreadsCurve.getLocalDateTime(), spreadsCurve);

        });

        return spreadsCurveTimeSeries;
    }

    /**
     * Performs principal component analysis based on the time series the actual curve time series.
     * It is a rolling analysis as it takes into account the only the previous data to calculate the current theo
     * curve.
     * After gettint the theoretical curve it transforms it into an "spread" curve.
     * @param actualCurveTimeSeries
     * @param spreadsList
     * @return
     */
    private TimeSeries getTheoSpreadsTimeSeriesFromPCA(TimeSeries actualCurveTimeSeries, List<Spread> spreadsList) {
        // pca analysis
        PrincipalComponentAnalysis principalComponentAnalysis = new PrincipalComponentAnalysis(actualCurveTimeSeries);
        principalComponentAnalysis.setNumberOfPrincipalComponentsToUseInProjection(3);
        TimeSeries theoreticalCurveTimeSeries = new TimeSeries();

        actualCurveTimeSeries.getCurveTimeSeries().values()
                .forEach(regularInstrumentsCurve -> theoreticalCurveTimeSeries.addCurve(principalComponentAnalysis.getTheoreticalCurve(regularInstrumentsCurve)));

        // end of Pca analysis. We now have the theoretical curve time series
        // at this point the theoretical rates curve is transformed to an spread curve
        return this.getSpreadsTimeSeriesFromRegularPointsInTheCurve(theoreticalCurveTimeSeries, spreadsList);
    }

    /** Helper Methods **/

    /**
     *  Returns and Calculate the price of a particular spread given the spread definition
     *  and curve containing the data point data of the instruments that are leg components
     *  of the spread.
     * @param curve
     * @param spread
     * @return
     */
    private double getSpreadPriceFromCurve(Data curve, Spread spread) {
        return curve.getData().stream()
                .filter(dataPoint -> spread.getLegMap().containsKey(dataPoint.getInstrument()))
                .mapToDouble(dataPoint -> dataPoint.getPrice() * spread.getLegMap().get(dataPoint.getInstrument()).getQuantity())
                .sum();
    }

    /**
     *  Transforms the curve regularInstrumentsCurve into a Curve or a Spread data point Series for a particular localDateTime
     *
     * @param regularInstrumentsCurve
     * @param spreadsList
     * @return
     */
    private Curve getSpreadCurveFromRegularInstrumentsCurve(Data regularInstrumentsCurve, List<Spread> spreadsList){
        List<DataPoint> spreadsDataPointsList = new ArrayList<>();

        spreadsList.forEach(spread -> {
            DataPoint spreadDataPoint = new DataPoint(spread, getSpreadPriceFromCurve(regularInstrumentsCurve, spread), regularInstrumentsCurve.getLocalDateTime());
            spreadsDataPointsList.add(spreadDataPoint);
        });

        return new Curve(regularInstrumentsCurve.getLocalDateTime(), spreadsDataPointsList);
    }

}
