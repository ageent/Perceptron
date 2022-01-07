package com.company.nets.opt;

import com.company.nets.AbstractAnn;
import com.company.nets.layers.AbstractLayer;

import java.util.HashMap;
import java.util.Iterator;

public class SGD extends Optimizer {
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

    /**
     * @param optimizerParams contain keys:
     */
    @Override
    public void fill(AbstractAnn ann, HashMap<String, Object> optimizerParams) {
        super.fill(ann, optimizerParams);
        this.resultsOfDirectPassage = new double[ann.size()][];
    }

    /*
     * Fill resultsOfDirectPassage.
     * Use AbstractLayer.parallelAffect().
     * */
    private void directPassage(double[] input) {
        Iterator<AbstractLayer> iter = getAnn().iterator();
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
}
