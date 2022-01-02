package com.company.nets.opt;

import com.company.nets.AbstractAnn;

import java.util.HashMap;

public abstract class Optimizer {
    public Optimizer() {
    }

    /**
     * Read params and fill fields of object.
     */
    public abstract void fill(AbstractAnn ann, CostFunc cost, HashMap<String, Object> params);
}
