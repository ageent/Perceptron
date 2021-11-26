package com.company.nets;

public abstract class AbstractAnn {
    public AbstractAnn() {

    }

    public abstract void hit(Training algorithm);

    public abstract void hit();

    public abstract void predict();

    public double getWeight(int numInCurrentLayer, int numInPreviousLayer) {

    }
}
