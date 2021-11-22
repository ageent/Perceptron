package com.company.nets.layers.neurons;

public interface ActivationFunc {
    /**
     * Heaviside step function.
     */
    static byte stepFunc(double signalsCombination) {
        return (byte) (signalsCombination < 0 ? 0 : 1);
    }
}
