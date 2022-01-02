package com.company.nets.layers;

import com.company.nets.layers.neurons.AbstractNeuron;
import com.company.nets.layers.neurons.ActivationFunc;
import com.company.nets.layers.neurons.CombinationFunc;
import com.company.nets.layers.neurons.Neuron;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public double[] parallelAffect(double... inputs) {
        int layerSize = this.size();
        double[] out = new double[layerSize];
        AtomicInteger i = new AtomicInteger(0);
        BlockingQueue<AbstractNeuron> queue = new LinkedBlockingQueue<>(layerSize);
        ExecutorService executorService = Executors.newFixedThreadPool(layerSize);

        for (AbstractNeuron n : this) {
            while (!queue.offer(n)) ;
            try {
                executorService.submit(() -> {
                    out[i.get()] = Objects.requireNonNull(queue.poll()).affect(inputs);
                    i.getAndIncrement();
                });
            } catch (NullPointerException ignored) {
            }
        }
        executorService.shutdown();

        return out;
    }
}
