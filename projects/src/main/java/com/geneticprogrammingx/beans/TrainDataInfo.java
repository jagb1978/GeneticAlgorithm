package com.geneticprogrammingx.beans;

/**
 * Training Data Info Bean
 *
 * @author Jose Gonzalez
 */
public class TrainDataInfo {
    private double minRandom;
    private double maxRandom;
    private int varNumber;
    private int numberOfDataPoints;
    private int randomNumber;
    private int totalNumberOfPrimitives;
    private double[][] targets;

    public static class Builder {
        private double minRandom;
        private double maxRandom;
        private int varNumber;
        private int numberOfDataPoints;
        private int randomNumber;

        public Builder minRandom(double minRandom) {
            this.minRandom = minRandom;
            return this;
        }
        public Builder maxRandom(double maxRandom) {
            this.maxRandom = maxRandom;
            return this;
        }
        public Builder varNumber(int varNumber) {
            this.varNumber = varNumber;
            return this;
        }
        public Builder numberOfDataPoints(int numberOfDataPoints) {
            this.numberOfDataPoints = numberOfDataPoints;
            return this;
        }
        public Builder randomNumber(int randomNumber) {
            this.randomNumber = randomNumber;
            return this;
        }

        public TrainDataInfo build() {
            return new TrainDataInfo(this);
        }

    }

    private TrainDataInfo(Builder builder) {
        this.minRandom = builder.minRandom;
        this.maxRandom = builder.maxRandom;
        this.varNumber = builder.varNumber;
        this.numberOfDataPoints = builder.numberOfDataPoints;
        this.randomNumber = builder.randomNumber;
        this.totalNumberOfPrimitives = this.varNumber + this.randomNumber;
        this.targets = new double[this.numberOfDataPoints][this.varNumber + 1];
    }

    /** Getters */

    public double getMinRandom() {
        return minRandom;
    }

    public double getMaxRandom() {
        return maxRandom;
    }

    public int getVarNumber() {
        return varNumber;
    }

    public int getNumberOfDataPoints() {
        return numberOfDataPoints;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public int getTotalNumberOfPrimitives() {
        return totalNumberOfPrimitives;
    }

    public double[][] getTargets() {
        return targets;
    }
}
