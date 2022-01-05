package com.company.nets.layers;

import com.company.nets.layers.neurons.AbstractNeuron;
import com.company.nets.layers.neurons.ActivationFunc;
import com.company.nets.layers.neurons.CombinationFunc;
import com.company.nets.layers.neurons.Neuron;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DenseLayer extends AbstractLayer {
    public DenseLayer(int layerSize, int sizeOfPreviousLayer,
                      CombinationFunc comb,
                      ActivationFunc act,
                      boolean shift) {
        super(layerSize);
        for (int i = 0; i < layerSize; i++) {
            this.add(new Neuron(sizeOfPreviousLayer, comb, act, shift));
        }
    }

    @Override
    public double[] affect(double... inputs) {
        double[] out = new double[this.size()];
        int i = 0;
        for (AbstractNeuron n : this) {
            out[i] = n.affect(inputs);
            i++;
        }
        return out;
    }

    /**
     * inputs.length == this.size
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public double[] parallelAffect(double... inputs) {
        assert inputs.length == this.size() : "Different length of arrays!";

        int layerSize = this.size();
        double[] out = new double[layerSize];
        AbstractNeuron[] layer = this.toArray(new AbstractNeuron[0]);
        AtomicInteger i = new AtomicInteger(0);
        ExecutorService pool = Executors.newFixedThreadPool(layerSize);

        for (AbstractNeuron n : this) {
            pool.submit(() -> {
                int j = i.getAndIncrement();
                out[j] = layer[j].affect(inputs);
            });
        }
        pool.shutdown();
        try {
            while (!pool.awaitTermination(1, TimeUnit.SECONDS)) ;
        } catch (InterruptedException ie) {
            // Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }

        return out;
    }
}
