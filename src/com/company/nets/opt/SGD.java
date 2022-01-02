package com.company.nets.opt;

import com.company.nets.AbstractAnn;
import com.company.nets.layers.AbstractLayer;

import java.util.HashMap;
import java.util.Iterator;

public class SGD extends Optimizer {
    private AbstractAnn ann;
    private CostFunc cost;
    /*
    May be, get results for every layer?
    Index 0 has input.
    */
    private double[][] resultsOfDirectPassage;

    public SGD() {
    }

    /**
     * Change weights of model.
     */
    public void apply(double[] input) {
        directPassage(input);

    }

    @Override
    public void fill(AbstractAnn ann, CostFunc cost, HashMap<String, Object> params) {
        this.ann = ann;
        this.cost = cost;
        this.resultsOfDirectPassage = new double[ann.size()][];

    }

    /*
     * Fill resultsOfDirectPassage.
     * Use AbstractLayer.parallelAffect().
     * */
    private void directPassage(double[] input) {
        Iterator<AbstractLayer> iter = ann.iterator();
        resultsOfDirectPassage[0] = input;
        iter.next();    // skip input layer
        int i = 1;
        while (iter.hasNext()) {
            AbstractLayer l = iter.next();
            resultsOfDirectPassage[i] = l.parallelAffect(resultsOfDirectPassage[i - 1]);
            assert resultsOfDirectPassage[i].length == l.size() : "Invalid array length!";
            i++;
        }
    }

    /*
     * @param params contains the keys:
     *               cost - object of cost func,
     *               ann - object of artificial neural network,
     *               activationParams - params of activation function,
     *
     *
    static void SGD(HashMap<String, Object> params) {
        AbstractAnn ann = (AbstractAnn) params.remove("ann");
        double[][][] wCurrent = ann.getWeights();
    }
    }*/
}
