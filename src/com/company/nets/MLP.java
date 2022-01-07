package com.company.nets;

import com.company.nets.layers.AbstractLayer;
import com.company.nets.opt.CostFunc;
import com.company.nets.opt.Optimizer;
import com.company.nets.opt.SGD;

import java.util.HashMap;

public class MLP extends AbstractAnn {
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

    @Override
    public double evaluate(double[][] xTest, double[][] yTest) {
        return 0;
    }

    @Override
    protected void setDefaultValues() {
        HashMap<String, Object> optParams = new HashMap<>();
        // FIXME: fill params
        Optimizer alg = new SGD();
        alg.fill(this, optParams);
        setAlgorithm(alg);
        setCost(CostFunc::MSE);
    }
}
