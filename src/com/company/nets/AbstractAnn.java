package com.company.nets;

import com.company.nets.layers.AbstractLayer;
import com.company.nets.layers.InputLayer;
import com.company.nets.layers.neurons.AbstractNeuron;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Layers are counted from first (input - 0) to last.
 */
public abstract class AbstractAnn extends ArrayList<AbstractLayer> {
    /**
     * Create input layer.
     * */
    public AbstractAnn(int numOfInputs) {
        this.add(new InputLayer(numOfInputs));
    }

    /**
     * Constructs an empty list with an initial capacity of ten.
     * Create input layer
     */
    public AbstractAnn() {
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     * Don't create input layer.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public AbstractAnn(Collection<? extends AbstractLayer> c) {
        super(c);
    }

    public abstract void fit(Training algorithm);

    public abstract void fit();

    public abstract void predict();

    /**
     * @return array of size L x N x W, where L is layers count,
     * N is neuron count, W is weights count. Uneven array.
     * */
    public double[][][] getWeights() {
        double[][][] weights = new double[this.size() - 1][][];
        Iterator<AbstractLayer> iter = this.iterator();
        iter.next();    // input layer
        int i = 1;

        while (iter.hasNext()) {
            AbstractLayer layer = iter.next();
            weights[i] = new double[layer.size()][];
            int j = 0;
            for (AbstractNeuron neuron : layer) {
                weights[i][j] = neuron.getWeights();
            }
            i++;
        }

        return weights;
    }
}
