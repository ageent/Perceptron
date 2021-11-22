package com.company.nets.layers.neurons;

public class Neuron extends AbstractNeuron {
    public Neuron(int inputCount) {
        this.setWeights(getInitialWeights(inputCount));
    }

    /**
     * Factory method.
     */
    public static Neuron getNeuron(int inputCounts) {
        return new Neuron(inputCounts);
    }

    public final double affect(double[] inputs,
                               CombinationFunc combination,
                               ActivationFunc activation) {
        double comb = combination.apply(inputs, getWeights());
        return activation.apply(comb);
    }

    /**
     * TLU
     */
    public double affect(double... inputs) {
        return affect(inputs, CombinationFunc::linComb, ActivationFunc::stepFunc);
    }
}
