package com.trading.fixedincome;

import com.trading.beans.DataPoint;
import com.trading.interfaces.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * SpreadDataPoints contains the data point information for a spread
 *
 * @author Jose Gonzalez
 */
public class SpreadDataPoint implements Data {
    private Spread spread;
    private LocalDateTime localDateTime;
    private double spreadMarketPrice;
    private double spreadTheoPrice;
    private double spreadError;
    private int spreadErrorPercentile;

    public SpreadDataPoint(Spread spread, Curve marketCurve, Curve theoCurve) {
        this.spread = spread;
        this.localDateTime = marketCurve.getLocalDateTime();
        this.spreadMarketPrice = getSpreadPriceFromCurve(marketCurve);
        this.spreadTheoPrice = getSpreadPriceFromCurve(theoCurve);
        this.spreadError = this.spreadTheoPrice - this.spreadMarketPrice;
    }

    private double getSpreadPriceFromCurve(Curve curve) {
        return curve.getData().stream()
                .filter(dataPoint -> this.spread.getLegMap().containsKey(dataPoint.getInstrument()))
                .mapToDouble(dataPoint -> this.spread.getLegMap().get(dataPoint.getInstrument()).getQuantity() * dataPoint.getPrice())
                .sum();
    }

    public void calculateErrorPercentile(List<DataPoint> spreadTimeSeries) {
        Long numberOfDataPointsBelowCurrentSpreadError = spreadTimeSeries.stream()
                .filter(dataPoint -> dataPoint.getPrice() < this.spreadError)
                .count();

        this.spreadErrorPercentile = numberOfDataPointsBelowCurrentSpreadError.intValue() / spreadTimeSeries.size();
    }

    public int getSpreadErrorPercentile() {
        return spreadErrorPercentile;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

}
