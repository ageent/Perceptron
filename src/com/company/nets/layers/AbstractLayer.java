package com.company.nets.layers;

import com.company.nets.layers.neurons.AbstractNeuron;

import java.util.ArrayList;
import java.util.Collection;

public abstract class AbstractLayer extends ArrayList<AbstractNeuron> {
    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *                                  is negative
     */
    public AbstractLayer(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public AbstractLayer() {
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public AbstractLayer(Collection<? extends AbstractNeuron> c) {
        super(c);
    }
}
