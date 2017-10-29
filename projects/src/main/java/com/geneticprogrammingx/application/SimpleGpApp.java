package com.geneticprogrammingx.application;

import com.geneticprogrammingx.SimpleGP;

public class SimpleGpApp {
    public static void main(String[] args) {
        String fileName = "/Users/josealfredogonzalez/IdeaProjects/GeneticAlgorithm/projects/src/problem.csv";

        long seed = -1;

        if (args.length == 2) {
            seed = Integer.valueOf(args[0]);
            fileName = args[1];
        } else if (args.length == 1) {
            fileName = args[0];
        }

        SimpleGP simpleGP = new SimpleGP(fileName, seed);
        simpleGP.evolve();
    }
}
