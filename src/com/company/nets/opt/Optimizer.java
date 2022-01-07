package com.company.nets.opt;

import com.company.nets.AbstractAnn;

import java.util.HashMap;

public abstract class Optimizer {
    private HashMap<String, Object> params;
    private AbstractAnn ann;

    public Optimizer() {
    }

    /**
     * Change weights of model.
     */
    public abstract void apply(double[] input);

    /**
     * Read params and fill fields of object.
     */
    public void fill(AbstractAnn ann, HashMap<String, Object> optimizerParams) {
        this.ann = ann;
        optimizerParams.forEach((k, v) -> {
            if (!this.params.containsKey(k)) {
                throw new IllegalArgumentException("Arg 'params' contains illegal key!");
            }
        });
        this.params.putAll(optimizerParams);
    }

    public HashMap<String, Object> getParams() {
        return params;
    }

    public void setParams(HashMap<String, Object> params) {
        this.params = params;
    }

    public AbstractAnn getAnn() {
        return ann;
    }

    public void setAnn(AbstractAnn ann) {
        this.ann = ann;
    }
}
