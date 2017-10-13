package com.geneticalgorithm.beans;

/**
 * @author Jose Gonzalez
 */
public class Gene<T> {
    private T value;

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

}
