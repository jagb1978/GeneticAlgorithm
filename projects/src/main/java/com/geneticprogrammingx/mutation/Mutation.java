package com.geneticprogrammingx.mutation;

import com.geneticprogrammingx.utils.NodeManager;

import java.util.Random;

public class Mutation {
    private NodeManager nodeManager;
    private Random random = new Random();
    private double mutationProbability;

    public Mutation(NodeManager nodeManager, double mutationProbability){
        this.nodeManager = nodeManager;
        this.mutationProbability = mutationProbability;
    }

    public char[] mutation(char[] parent) {
        int nodeLength = this.nodeManager.getNodeLength(parent, 0);
        int mutationSite;
        char[] parentCopy = new char[nodeLength];

        System.arraycopy(parent, 0, parentCopy, 0, nodeLength);

        for (int i = 0; i < nodeLength; i++) {
            if (this.random.nextDouble() < this.mutationProbability) {
                mutationSite = i;
                if (this.nodeManager.isThePointATerminal(parentCopy,mutationSite)) {
                  parentCopy[mutationSite] = (char) this.nodeManager.getRandomPositionOfVariableOrConstant();
                } else {
                    parentCopy[mutationSite] = (char) this.nodeManager.getRandomFunction();
                }
            }
        }
        return parentCopy;
    }

}
