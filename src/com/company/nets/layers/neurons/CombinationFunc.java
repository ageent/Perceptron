package com.company.nets.layers.neurons;

public interface CombinationFunc {
    double apply(double[] inputs, double[] weight);

    /**
     * The array of the arrays must be the equal.
     */
    static double linComb(double[] inputs, double[] weights) {
        assert inputs.length == weights.length;

        double res = 0;
        for (int i = 0; i < inputs.length; i++) {
            res += weights[i] * inputs[i];
        }
        return res;
    }
}
