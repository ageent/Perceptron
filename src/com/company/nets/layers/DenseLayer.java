package com.company.nets.layers;

import com.company.nets.layers.neurons.AbstractNeuron;
import com.company.nets.layers.neurons.ActivationFunc;
import com.company.nets.layers.neurons.CombinationFunc;
import com.company.nets.layers.neurons.Neuron;

public class DenseLayer extends AbstractLayer {
    public DenseLayer(int layerSize, int sizeOfPreviousLayer,
                      CombinationFunc comb,
                      ActivationFunc act,
                      boolean shift) {
        super(layerSize);
        for (int i = 0; i < layerSize; i++) {
            this.add(new Neuron(sizeOfPreviousLayer, comb, act, shift));
        }
    }

    @Override
    public double[] affect(double... inputs) {
        double[] out = new double[this.size()];
        int i = 0;
        for (AbstractNeuron n : this) {
            out[i] = n.affect(inputs);
            i++;
        }
        return out;
    }

    /*@Override
    public double[] parallelAffect(double... inputs) {
        double[] out = new double[this.size()];

        int i = 0;
        for (AbstractNeuron n : this) {
            out[i] = n.affect(inputs);
            i++;
        }

        return out;
    }*/
}
