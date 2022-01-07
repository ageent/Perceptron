package com.company.nets;

import com.company.nets.layers.AbstractLayer;
import com.company.nets.opt.Optimizer;
import com.company.nets.opt.SGD;
import com.company.nets.opt.cost.MSE;

import java.util.Collection;
import java.util.HashMap;

public class MLP extends AbstractAnn {
    /**
     * Constructs an empty list with an initial capacity of ten.
     * Create input layer
     */
    public MLP() {
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public MLP(Collection<? extends AbstractLayer> c) {
        super(c);
    }

    /**
     * Fill map of params and call optimizer.
     *
     * @param trainingParams contains keys:
     *                       opt - empty object of Optimizer,
     *                       batchSize - size of mini-package,
     */
    @Override
    public void fit(double[][] xTrain, double[][] yTrain,
                    double[][] xValid, double[][] yValid,
                    int epochs, HashMap<String, Object> trainingParams) {

    }

    /**
     * It is assumed that the size of the layers decreases or remains the same.
     */
    @Override
    public double[][] predict(double[][] xNew) {
        double[][] res = new double[xNew.length][];
        for (int i = 0; i < xNew.length; i++) {
            double[] singleRes = xNew[i];
            for (AbstractLayer l : this) {
                singleRes = l.affect(singleRes);
            }
            res[i] = singleRes;
        }
        return res;
    }

    public double[][] parallelPredict(double[][] xNew) {
        double[][] res = new double[xNew.length][];
        for (int i = 0; i < xNew.length; i++) {
            double[] singleRes = xNew[i];
            for (AbstractLayer l : this) {
                singleRes = l.parallelAffect(singleRes);
            }
            res[i] = singleRes;
        }
        return res;
    }

    /**
     * Cost function is same as for fit().
     *
     * @return the result of calculating the cost function for each output neuron (columns of the matrix yTrain).
     */
    @Override
    public double evaluate(double[][] xTest, double[][] yTest) {
        double[][] pred = predict(xTest);
        return getCost().apply(pred, yTest);
    }

    @Override
    protected void setDefaultValues() {
        HashMap<String, Object> optParams = new HashMap<>();
        // FIXME: fill params
        Optimizer alg = new SGD();
        alg.fill(this, optParams);
        setAlgorithm(alg);
        setCost(new MSE());
    }



}
