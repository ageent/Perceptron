package com.company.nets.layers.neurons;

public interface CombinationFunc {
    double apply(double[] inputs, double[] weight);

    /**
     * The length of weights must be more by 1 than the length of inputs.
     * weight[0] is shift.
     */
    static double linComb(double[] inputs, double[] weights) {
        assert inputs.length == weights.length + 1;

        double res = weights[0];    // Shift
        for (int i = 0; i < inputs.length; i++) {
            res += weights[i + 1] * inputs[i];
        }
        return res;
    }

    /**
     * Just sums up all the inputs.
     */
    static double sum(double[] inputs, double[] weights) {
        double res = 0;
        for (double e : inputs) {
            res += e;
        }
        return res;
    }
}
