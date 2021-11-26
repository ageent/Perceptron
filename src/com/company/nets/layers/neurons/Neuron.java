package com.company.nets.layers.neurons;

public class Neuron extends AbstractNeuron {
    public Neuron(int inputCount,
                  CombinationFunc combination,
                  ActivationFunc activation,
                  boolean shift) {
        super(inputCount, combination, activation, shift);
    }

    public double affect(double... inputs) {
        double comb = getCombination().apply(inputs, getWeights());
        return getActivation().apply(comb);
    }
}
