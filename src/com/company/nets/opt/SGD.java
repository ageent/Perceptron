package com.company.nets.opt;

import com.company.nets.AbstractAnn;

import java.util.HashMap;

public class SGD extends Optimizer {
    private AbstractAnn ann;
    private CostFunc cost;
    private double[][] resultsOfDirectPassage;  // May be, get results for every layer?

    public SGD() {
    }

    @Override
    public void fill(AbstractAnn ann, CostFunc cost, HashMap<String, Object> params) {
        this.ann = ann;
        this.cost = cost;

    }

    private void directPassage() {

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
