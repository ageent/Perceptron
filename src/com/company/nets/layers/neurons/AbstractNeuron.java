package com.company.nets.layers.neurons;

import java.util.Arrays;
import java.util.Objects;
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

    public abstract double affect(double... inputs);

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
        return weights;
    }

    /**
     * weights[0] is shift.
     */
    protected void setWeights(double[] weights) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractNeuron that)) return false;
        return Arrays.equals(weights, that.weights) &&
                Objects.equals(combination, that.combination) &&
                Objects.equals(activation, that.activation);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(combination, activation);
        result = 31 * result + Arrays.hashCode(weights);
        return result;
    }

    @Override
    public String toString() {
        return "AbstractNeuron{" +
                "weights=" + Arrays.toString(weights) +
                ", combination=" + combination +
                ", activation=" + activation +
                '}';
    }
}
