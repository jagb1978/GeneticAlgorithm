package com.geneticalgorithm.beans;

import com.geneticalgorithm.Enums.ValueType;
import java.util.List;

/**
 * GeneType Bean
 *
 * @author Jose Gonzalez
 */
public class GeneType<T> {
    private String name;
    private T minValue;
    private T maxValue;
    private ValueType valueType;
    private List<T> rangeList;

    public GeneType (String name, ValueType valueType, T minValue, T maxValue){
        this.name =  name;
        this.valueType = valueType;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
    public GeneType (String name, ValueType valueType, List<T> rangeList){
        this.name =  name;
        this.valueType = valueType;
        this.rangeList = rangeList;
    }
    public GeneType (String name, ValueType valueType){
        this.name =  name;
        this.valueType = valueType;
    }

    public String getName() {
        return name;
    }

    public T getMinValue() {
        return minValue;
    }

    public T getMaxValue() {
        return maxValue;
    }

    public ValueType getValueType() {
        return valueType;
    }

    public List<T> getRangeList() {
        return rangeList;
    }
}
