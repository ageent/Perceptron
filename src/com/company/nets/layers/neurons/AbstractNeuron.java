package com.company.nets.layers.neurons;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;
import java.util.random.RandomGenerator;

public abstract class AbstractNeuron {
    private double[] weights;

    public abstract double affect(double... inputs);

    protected static double[] getInitialWeights(int inputCount) {
        double[] res = new double[inputCount];
        RandomGenerator gen = new Random();
        Arrays.setAll(res, x -> gen.nextGaussian(0.1, 0.02));
        Logger.getGlobal().info("Weights: " + Arrays.toString(res));
        return res;
    }

    public double[] getWeights() {
        return weights;
    }

    protected void setWeights(double[] weights) {
        this.weights = weights;
    }

    @Override
    @SuppressWarnings("all")
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractNeuron)) return false;
        AbstractNeuron that = (AbstractNeuron) o;
        return Arrays.equals(weights, that.weights);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(weights);
    }

    @Override
    public String toString() {
        return "Neuron{" +
                "weights=" + Arrays.toString(weights) +
                '}';
    }
}
