package com.company.nets;

import com.company.nets.layers.AbstractLayer;
import com.company.nets.layers.neurons.AbstractNeuron;
import com.company.nets.opt.CostFunc;
import com.company.nets.opt.Optimizer;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Layers are counted from first hide layer to output layer.
 */
public abstract class AbstractAnn extends LinkedList<AbstractLayer> {
    private Optimizer algorithm;
    private CostFunc cost;

    /**
     * Constructs an empty list with an initial capacity of ten.
     * Create input layer
     */
    public AbstractAnn() {
        setDefaultValues();
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public AbstractAnn(Collection<? extends AbstractLayer> c) {
        super(c);
        setDefaultValues();
    }

    public void fillObj(Optimizer algorithm, CostFunc cost) {
        this.algorithm = algorithm;
        this.cost = cost;
    }

    /**
     * Fill map of params and call optimizer.
     */
    public abstract void fit(double[][] xTrain, double[][] yTrain,
                             double[][] xValid, double[][] yValid,
                             int epochs, HashMap<String, Object> trainingParams);

    public abstract double[][] predict(double[][] xNew);

    public abstract double evaluate(double[][] xTest, double[][] yTest);

    /**
     * @return array of size L x N x W, where L is layers count,
     * N is neuron count, W is 1 (shift) plus weights count. Uneven array.
     */
    public double[][][] getWeights() {
        double[][][] weights = new double[this.size()][][];
        int i = 0;
        for (AbstractLayer layer : this) {
            weights[i] = new double[layer.size()][];
            int j = 0;
            for (AbstractNeuron neuron : layer) {
                weights[i][j] = neuron.getWeights();
                j++;
            }
            i++;
        }
        return weights;
    }

    /**
     * @param weights is array of size L x N x W, where L is layers count,
     *                N is neuron count, W is 1 (First array element is a shift) plus weights count. Uneven array.
     */
    public void setWeights(double[][][] weights) {
        if (this.size() != weights.length)
            throw new IllegalArgumentException("Illegal size L of param weights!");

        int i = 0;
        for (AbstractLayer layer : this) {
            String messageForLayer = "Illegal size N of param weights for " + i + " layer!";
            if (layer.size() != weights[i].length)
                throw new IllegalArgumentException(messageForLayer);

            int j = 0;
            for (AbstractNeuron neuron : layer) {
                String messageForNeuron = "Illegal size W of param weights for " + j + " neuron!";
                if (neuron.getWeights().length != weights[i][j].length)
                    throw new IllegalArgumentException(messageForNeuron);

                neuron.setWeights(weights[i][j]);
                j++;
            }
            i++;
        }
    }

    /**
     * For set default values of the object fields (for example, fields algorithm, optimizerParams).
     */
    protected abstract void setDefaultValues();

    public Optimizer getAlgorithm() {
        return algorithm;
    }

    protected void setAlgorithm(Optimizer algorithm) {
        this.algorithm = algorithm;
    }

    public CostFunc getCost() {
        return cost;
    }

    public void setCost(CostFunc cost) {
        this.cost = cost;
    }
}
