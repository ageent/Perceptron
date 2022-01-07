package com.company.nets.opt.cost;

public abstract class AbstractCostFunc {
    public abstract double apply(double[] calculated, double[] correct);

    public abstract double apply(double[][] calculated, double[][] correct);

    public static double[][] transposeMatrix(double[][] matrix) {
        double[][] res = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                res[j][i] = matrix[i][j];
            }
        }
        return res;
    }
}
