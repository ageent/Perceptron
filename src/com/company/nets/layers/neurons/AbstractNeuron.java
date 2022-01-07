package com.company.nets.layers.neurons;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;
import java.util.random.RandomGenerator;

public abstract class AbstractNeuron {
    private double[] weights;
    private CombinationFunc combination;
    private ActivationFunc activation;

    public AbstractNeuron(int inputCount,
                          CombinationFunc combination,
                          ActivationFunc activation,
                          boolean shift) {
        this.setWeights(getInitialWeights(inputCount, shift));
        this.combination = combination;
        this.activation = activation;
    }

    public abstract double affect(double[] inputs);

    /**
     * If shift is false then weight[0] is 0.
     */
    protected static double[] getInitialWeights(int inputCount, boolean shift) {
        int totalCount = inputCount + 1;
        double[] res = new double[totalCount];

        RandomGenerator gen = new Random();
        res[0] = shift ? gen.nextGaussian(0.1, 0.02) : 0;
        for (int i = 1; i < totalCount; i++) {
            res[i] = gen.nextGaussian(0.1, 0.02);
        }

        Logger.getGlobal().info("Weights: " + Arrays.toString(res));

        return res;
    }

    /**
     * weights[0] is shift.
     */
    public double[] getWeights() {
        return weights.clone();
    }

    /**
     * weights[0] is shift.
     */
    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public CombinationFunc getCombination() {
        return combination;
    }

    public void setCombination(CombinationFunc combination) {
        this.combination = combination;
    }

    public ActivationFunc getActivation() {
        return activation;
    }

    public void setActivation(ActivationFunc activation) {
        this.activation = activation;
    }

}
