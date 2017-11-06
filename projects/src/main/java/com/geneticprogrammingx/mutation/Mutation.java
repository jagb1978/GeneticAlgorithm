package com.geneticprogrammingx.mutation;

import com.geneticprogrammingx.utils.NodeManager;
import java.util.Random;

/**
 * This class handles a point random mutation process
 * @author Jose Gonzalez
 */
public class Mutation {
    private NodeManager nodeManager;
    private Random random = new Random();
    private double mutationProbability;

    public Mutation(NodeManager nodeManager, double mutationProbability){
        this.nodeManager = nodeManager;
        this.mutationProbability = mutationProbability;
    }

    /**
     * Mutates and returns a new individual selecting random mutation
     * terminals or functions
     * @param parent
     * @return
     */
    public char[] mutation(char[] parent) {
        int nodeLength = this.nodeManager.getNodeLength(parent, 0);
        int mutationSite;
        char[] parentCopy = new char[nodeLength];

        /** creates a copy of the individual */
        System.arraycopy(parent, 0, parentCopy, 0, nodeLength);

        /** Randomly mutates the individual copy. If it is a terminal it will randombly mutate the terminal
         * and if it is function it will randomly change the functions in the node*/
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
