package com.company.nets.layers.neurons;

public class InputNeuron extends AbstractNeuron {
    private InputNeuron(int inputCount,
                        CombinationFunc combination,
                        ActivationFunc activation,
                        boolean shift) {
        super(inputCount, combination, activation, shift);
    }

    public InputNeuron() {
        this(0, CombinationFunc::sum, ActivationFunc::identicalFunc, false);
        this.setWeights(getInitialWeights(0, false));
    }

    /**
     * Return inputs[0].
     */
    @Override
    public double affect(double... inputs) {
        return inputs[0];
    }
}
