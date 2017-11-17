package com.geneticprogrammingx.utils;

import com.geneticprogrammingx.beans.Individual;

import java.util.Random;

/**
 * This class is used to manage operations that
 * assist the nodes mutation and crossover process
 *
 * @author Jose Gonzalez
 */
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
     *  If the point is a terminal it returns the node number +1
     *  otherwise it continues looping until it finds a terminal
     *
     *  This logic means that it returns the length of the node, understanding that the node
     *  finishes when it finds a terminal.
     * */
    public int getNodeLength(char[] individual, int begginingNodeNumber) {
        return isThePointATerminal(individual, begginingNodeNumber) ? ++begginingNodeNumber : getNodeLength(individual, getNodeLength(individual, ++begginingNodeNumber));
    }

    public int getNodeLength(Individual individual, int begginingNodeNumber) {
        return isThePointATerminal(individual, begginingNodeNumber) ? ++begginingNodeNumber : getNodeLength(individual, getNodeLength(individual, ++begginingNodeNumber));
    }


    public boolean isThePointATerminal(char[] individual, int nodeNumber){
        return individual[nodeNumber] < this.functionSetStartNumber;
    }

    public boolean  isThePointATerminal(Individual individual, int nodeNumber){
        return individual.getGenes()[nodeNumber] < this.functionSetStartNumber;
    }



    public int getRandomFunction(){
        return this.random.nextInt(functionSetEndNumber - functionSetStartNumber + 1) + functionSetStartNumber;
    }

    public int getRandomPositionOfVariableOrConstant(){
        return this.random.nextInt(this.totalNumberOfPrimitives);
    }


}
