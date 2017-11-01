package com.geneticprogrammingx.crossover;

import com.geneticprogrammingx.utils.NodeManager;
import java.util.Random;

public class Crossover {
    private NodeManager nodeManager;
    private Random random = new Random();

    public Crossover(NodeManager nodeManager){
        this.nodeManager = nodeManager;
    }

    public char[] crossover(char[] parent1, char[] parent2) {
        int nodeLengthParent1 = this.nodeManager.getNodeLength(parent1, 0);
        int nodeLengthParent2 = this.nodeManager.getNodeLength(parent2, 0);

        int crossover1Start = this.random.nextInt(nodeLengthParent1);
        int crossover1End = this.nodeManager.getNodeLength(parent1, crossover1Start);

        int crossover2start = this.random.nextInt(nodeLengthParent2);
        int crossover2end = this.nodeManager.getNodeLength(parent2, crossover2start);

        int offspringNodeLength = crossover1Start + (crossover2end - crossover2start) + (nodeLengthParent1 - crossover1End);

        char[] offspring = new char[offspringNodeLength];

        System.arraycopy(parent1, 0, offspring, 0, crossover1Start);
        System.arraycopy(parent2, crossover2start, offspring, crossover1Start, (crossover2end - crossover2start));
        System.arraycopy(parent1, crossover1End, offspring, crossover1Start + (crossover2end - crossover2start), (nodeLengthParent1 - crossover1End));

        return offspring;
    }
}
