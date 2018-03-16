package com.trading.utils;

import com.trading.beans.*;
import com.trading.fixedincome.Curve;
import com.trading.fixedincome.TimeSeries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TimeSeriesUtils {

    public static List<DataPoint> createDataPoints(File file) throws IOException {
        String line;
        List<DataPoint> dataPointList = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getPath()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        int bufferedReaderCounter = 0;

        while ((line = bufferedReader.readLine()) != null) {

            if (bufferedReaderCounter > 0) {
                String[] splitLine = line.split(",");

                Double closePrice = Double.parseDouble(splitLine[1]);
                LocalDateTime localDate = LocalDateTime.parse(splitLine[0], formatter);//03/01/2000

                DataPoint dataPoint = new DataPoint(closePrice);
                dataPoint.setLocalDateTime(localDate);

                dataPointList.add(dataPoint);
            }
            bufferedReaderCounter++;
        }

        bufferedReader.close();

        return dataPointList;
    }

    public static TimeSeries createCurveTimeSeries(File file) throws IOException {
        String line;
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getPath()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        int bufferedReaderCounter = 0;
        List<String> feedCodesList = new ArrayList<>();
        TimeSeries timeSeries = new TimeSeries();


        while ((line = bufferedReader.readLine()) != null) {
            String[] splitLine = line.split(",");

            if (bufferedReaderCounter == 0) {
                feedCodesList.addAll(Arrays.asList(splitLine));
            } else if (bufferedReaderCounter > 0) {

                List<DataPoint> dataPointList = new ArrayList<>();
                LocalDateTime localDate = LocalDateTime.parse(splitLine[0], formatter);

                for (int i =1; i<  splitLine.length; i++){
                    DataPoint dataPoint = new DataPoint(new Instrument(feedCodesList.get(i)), Double.parseDouble(splitLine[i]),localDate);
                    dataPointList.add(dataPoint);
                }

                Curve curve = new Curve(localDate,dataPointList);

                timeSeries.addCurve(curve);
            }
            bufferedReaderCounter++;
        }

        bufferedReader.close();

        return timeSeries;
    }


}
