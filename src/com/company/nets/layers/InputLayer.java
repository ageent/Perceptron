package com.company.nets.layers;

import com.company.nets.layers.neurons.InputNeuron;

public class InputLayer extends AbstractLayer {
    public InputLayer(int layerSize) {
        super(layerSize);
        for (int i = 0; i < layerSize; i++) {
            this.add(new InputNeuron());
        }
    }

    @Override
    public double[] affect(double... inputs) {
        return inputs;
    }
}
