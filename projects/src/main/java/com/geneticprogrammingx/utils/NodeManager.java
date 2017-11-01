package com.geneticprogrammingx.utils;

import java.util.Random;

public class NodeManager {
    private int functionSetStartNumber;
    private int functionSetEndNumber;
    private int totalNumberOfPrimitives;
    private Random random = new Random();

    public NodeManager(int functionSetStartNumber, int functionSetEndNumber, int totalNumberOfPrimitives){
        this.functionSetStartNumber = functionSetStartNumber;
        this.functionSetEndNumber = functionSetEndNumber;
        this.totalNumberOfPrimitives = totalNumberOfPrimitives;
    }

    /**
     *
     *  If the point is a terminal it returns the node number +1
     *  otherwise it continues looping until it finds a terminal
     *
     *  This logic means that it returns the length of the node, understanding that the node
     *  finishes when it finds a terminal.
     *
     * */
    public int getNodeLength(char[] individual, int nodeNumber) {
        return isThePointATerminal(individual, nodeNumber) ? ++nodeNumber : getNodeLength(individual, getNodeLength(individual, ++nodeNumber));
    }

    public boolean isThePointATerminal(char[] individual, int nodeNumber){
        return individual[nodeNumber] < this.functionSetStartNumber;
    }

    public int getRandomFunction(){
        return this.random.nextInt(functionSetEndNumber - functionSetStartNumber + 1) + functionSetStartNumber;
    }

    public int getRandomPositionOfVariableOrConstant(){
        return this.random.nextInt(this.totalNumberOfPrimitives);
    }




}
