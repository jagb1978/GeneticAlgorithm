package com.geneticalgorithm.beans;

import com.geneticalgorithm.factories.RandomGeneratorFactory;
import com.geneticalgorithm.interfaces.RandomGenerator;

/**
 * @author Jose Gonzalez
 */
public class Gene<T> {
    private T value;
    private GeneType geneType;
    private RandomGenerator randomGenerator;

    public Gene(){};

    public Gene(GeneType geneType){
        this.geneType = geneType;
        this.randomGenerator = RandomGeneratorFactory.factory(geneType);
    }

    public Gene(Gene gene){
        this.geneType = gene.getGeneType();
        this.randomGenerator = RandomGeneratorFactory.factory(geneType);
        this.value =(T) gene.getValue();
    }

    public void setRandomValue(){
        this.setValue((T) this.randomGenerator.generateRandom());
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString(){
      return   this.value.toString();
    }

    public GeneType getGeneType() {
        return this.geneType;
    }


}
