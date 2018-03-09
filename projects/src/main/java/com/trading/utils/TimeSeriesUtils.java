package com.trading.utils;

import com.trading.beans.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TimeSeriesUtils {

    public static  List<DataPoint> createDataPoints(File file) throws IOException {
        String line;
        List<DataPoint> dataPointList = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file.getPath()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int bufferedReaderCounter = 0;

        while ((line = bufferedReader.readLine()) != null) {
            if (bufferedReaderCounter > 0) {
                String[] splitLine = line.split(",");

                Double closePrice = Double.parseDouble(splitLine[1]);
                LocalDate localDate = LocalDate.parse(splitLine[0], formatter);//03/01/2000

                DataPoint dataPoint = new DataPoint(closePrice);
                dataPoint.setLocalDateTime(localDate);

                dataPointList.add(dataPoint);
            }
            bufferedReaderCounter++;
        }

        bufferedReader.close();

        return dataPointList;
    }

}
