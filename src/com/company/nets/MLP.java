package com.company.nets;

import com.company.nets.layers.AbstractLayer;
import com.company.nets.opt.Optimizer;

import java.util.HashMap;

public class MLP extends AbstractAnn {
    /**
     * Fill map of params and call optimizer.
     */
    @Override
    public void fit(Optimizer algorithm, HashMap<String, Double> params) {

    }

    /**
     * It is assumed that the size of the layers decreases or remains the same.
     */
    @Override
    public double[] predict(double[] inputs) {
        double[] res = inputs;
        for (AbstractLayer l : this) {
            res = l.affect(res);
        }
        return res;
    }

    public double[] parallelPredict(double[] inputs) {
        double[] res = inputs;
        for (AbstractLayer l : this) {
            res = l.parallelAffect(res);
        }
        return res;
    }
}
