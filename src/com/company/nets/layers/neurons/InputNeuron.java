package com.company.nets.layers.neurons;

public class InputNeuron extends AbstractNeuron {
    public InputNeuron(int inputCount) {
        this.setWeights(getInitialWeights(inputCount));
    }

    /**
     * Return inputs[0].
     */
    @Override
    public double affect(double... inputs) {
        return inputs[0];
    }
}
