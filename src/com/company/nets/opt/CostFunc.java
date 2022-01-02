package com.company.nets.opt;

import java.util.HashMap;

public interface CostFunc {
    double apply(double[] calculated, double[] correct, HashMap<String, Double> params);

    /**
     * @param params contains the keys:
     *               coef - coefficient for multiplying by the sum of squares
     */
    static double MSE(double[] calculated, double[] correct, HashMap<String, Double> params) {
        double sum = 0;
        for (int i = 0; i < calculated.length; i++) {
            double diff = calculated[i] - correct[i];
            sum += Math.pow(diff, 2);
        }
        return sum * params.get("coef");
    }
}
