package com.company.nets.layers.neurons;

public interface ActivationFunc {
    double apply(double combinationResult);

    /**
     * Heaviside step function.
     */
    static byte stepFunc(double signalsCombination) {
        return (byte) (signalsCombination < 0 ? 0 : 1);
    }

    static double sigmoid(double signalsCombination) {
        return 1 / (1 + Math.exp(-signalsCombination));
    }
}
