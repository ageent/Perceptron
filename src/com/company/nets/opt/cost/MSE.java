package com.company.nets.opt.cost;

import java.util.Arrays;

public class MSE extends AbstractCostFunc {
    private double coef = 0;
    private double[] coefs = new double[0];

    public MSE() {
    }

    /**
     * @param coef is coefficient for multiplying by the sum of squares
     */
    public MSE(double coef) {
        this.coef = coef;
    }

    /**
     * @param coefs is coefficient for multiplying by the sum of squares for every output feature.
     */
    public MSE(double[] coefs) {
        this.coefs = coefs;
    }

    /**
     * default coef = 1 / number of instance
     */
    @Override
    public double apply(double[] calculated, double[] correct) {
        coef = 1 / (double) calculated.length;
        return apply(calculated, correct, coef);
    }

    /**
     * default coef = 1 / number of instance
     */
    @Override
    public double apply(double[][] calculated, double[][] correct) {
        if (coefs.length == 0) {
            coefs = new double[calculated[0].length];   // number of features
            Arrays.fill(coefs, 1 / (double) calculated.length); // number of instance
        }
        if (calculated[0].length != coefs.length || correct[0].length != coefs.length)
            throw new IllegalArgumentException("Illegal size of method argument or field coefs");

        double[][] trCalculated = transposeMatrix(calculated);
        double[][] trCorrect = transposeMatrix(correct);
        double res = 0;
        for (int i = 0; i < trCalculated.length; i++) {
            res += apply(trCalculated[i], trCorrect[i], coefs[i]);
        }
        return res;
    }

    public static double apply(double[] calculated, double[] correct, double coef) {
        if (calculated.length != correct.length)
            throw new IllegalArgumentException("Difficult sizes of method arguments!");

        double sum = 0;
        for (int i = 0; i < calculated.length; i++) {
            double diff = calculated[i] - correct[i];
            sum += Math.pow(diff, 2);
        }
        return sum * coef;
    }
}
