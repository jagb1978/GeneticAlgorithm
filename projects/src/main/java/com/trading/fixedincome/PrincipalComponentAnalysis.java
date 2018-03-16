package com.trading.fixedincome;

import com.trading.beans.DataPoint;
import com.trading.interfaces.Data;
import smile.projection.PCA;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Jose Gonzalez
 */
public class PrincipalComponentAnalysis {
    private PCA pca;

    public PrincipalComponentAnalysis(TimeSeries curveTimeSeries) {
        this.pca = new PCA(this.transformTimeSeriesToMatrix(curveTimeSeries));
    }

    public void setNumberOfPrincipalComponentsToUseInProjection(int numberOfPrincipalComponents) {
        this.pca.setProjection(numberOfPrincipalComponents);
    }

    public Curve getTheoreticalCurve(Data currentCurve) {
        List<DataPoint> dataPointList = new ArrayList<>();
        double[] curveVector = currentCurve.getData().stream().mapToDouble(DataPoint::getPrice).toArray();
        double[] theoreticalCurveVector = this.pca.project(curveVector);

        currentCurve.getData().stream()
                .filter(dataPoint -> dataPoint.getLocalDateTime().isBefore(currentCurve.getLocalDateTime()))
                .forEach(dataPoint ->
                Arrays.stream(theoreticalCurveVector)
                                .forEach(theoreticalPrice -> dataPointList.add(new DataPoint(dataPoint.getInstrument(), theoreticalPrice, dataPoint.getLocalDateTime()))));

        return new Curve(currentCurve.getLocalDateTime(), dataPointList);
    }

    private double[][] transformTimeSeriesToMatrix(TimeSeries curveTimeSeries) {
        int numberOfCurves = curveTimeSeries.getCurveTimeSeries().size();
        int numberOfVariables = curveTimeSeries.getCurveTimeSeries().values().stream().findFirst().get().getData().size();

        double[][] dataMatrix = new double[numberOfCurves][numberOfVariables];

        for (int rows = 0; rows < numberOfCurves; rows++) {
            for (Data curve : curveTimeSeries.getCurveTimeSeries().values()) {
                dataMatrix[rows] = curve.getData().stream().mapToDouble(DataPoint::getPrice).toArray();
            }
        }
        return dataMatrix;
    }

}
