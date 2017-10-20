package com.geneticalgorithm.factories;

import com.geneticalgorithm.Enums.ValueType;
import com.geneticalgorithm.beans.GeneType;
import com.geneticalgorithm.interfaces.RandomGenerator;
import com.geneticalgorithm.randomgenerators.BitRandomGenerator;
import com.geneticalgorithm.randomgenerators.DoubleRandomGenerator;
import com.geneticalgorithm.randomgenerators.IntRandomGenerator;

/**
 * Factory Class used to create RandomGenerators
 */
public class RandomGeneratorFactory {
    private RandomGeneratorFactory(){};

    public static RandomGenerator factory(GeneType geneType){
        if(geneType.getValueType().equals(ValueType.VALUE_INT)){
            return new IntRandomGenerator((Integer)geneType.getMinValue(),(Integer)geneType.getMaxValue());
        } else if(geneType.getValueType().equals(ValueType.VALUE_DOUBLE)){
            return new DoubleRandomGenerator((Double) geneType.getMinValue(),(Double)geneType.getMaxValue());
        } else if(geneType.getValueType().equals(ValueType.VALUE_BIT)) {
            return new BitRandomGenerator();
        }
        else {
            return null;
        }
    }

}
