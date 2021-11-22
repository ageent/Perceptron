package com.company.nets.layers.neurons;

import java.util.Arrays;
import java.util.Random;
import java.util.random.RandomGenerator;

public abstract class Neuron {
    private double[] weights;
    private double combination;
    private double activation;

    /*
     * Factory method.
     * */
    public abstract Neuron getNeuron(int inputCounts);

    public abstract double affect(double[] inputs);

    protected static double[] getInitialWeights(int inputCount) {
        double[] res = new double[inputCount];
        RandomGenerator gen = new Random();
        Arrays.setAll(res, x -> gen.nextGaussian(0.1, 0.02));
        System.out.println("Weights: " + Arrays.toString(res));
        return res;
    }
}
