package com.geneticprogrammingx.crossover;

import com.geneticprogrammingx.utils.NodeManager;
import java.util.Random;


/**
 * Crossover class that creates new offspring
 * by diving the parents chromosomes in two different sets
 * at a random point and the combines them.
 *
 * @author Jose Gonzalez
 */
public class Crossover {
    private NodeManager nodeManager;
    private Random random = new Random();

    public Crossover(NodeManager nodeManager){
        this.nodeManager = nodeManager;
    }

    /**
     * It populates the new chromosome or individual combining the parents gene
     *
     * @param parent1
     * @param parent2
     * @return
     */
    public char[] crossover(char[] parent1, char[] parent2) {
        int nodeLengthParent1 = this.nodeManager.getNodeLength(parent1, 0);
        int nodeLengthParent2 = this.nodeManager.getNodeLength(parent2, 0);

        int crossover1Start = this.random.nextInt(nodeLengthParent1);
        int crossover1End = this.nodeManager.getNodeLength(parent1, crossover1Start);

        int crossover2start = this.random.nextInt(nodeLengthParent2);
        int crossover2end = this.nodeManager.getNodeLength(parent2, crossover2start);

        int offspringNodeLength = crossover1Start + (crossover2end - crossover2start) + (nodeLengthParent1 - crossover1End);

        char[] offspring = new char[offspringNodeLength];
        /** Copies the parent 1 genes up to crossover1Start*/
        System.arraycopy(parent1, 0, offspring, 0, crossover1Start);
        /** Copies the parent 2 genes starting in crossover1Start and ending in crossover2end  */
        System.arraycopy(parent2, crossover2start, offspring, crossover1Start, (crossover2end - crossover2start));
        /** Copies the parent 1  genes starting in Crossover1End and places it after Crossover2end to fill all the genes in the chromosome */
        System.arraycopy(parent1, crossover1End, offspring, crossover1Start + (crossover2end - crossover2start), (nodeLengthParent1 - crossover1End));

        return offspring;
    }
}
