package com.simulator.utils;

public class PoissonDistribution {
    private final double lambda;

    public PoissonDistribution(double lambda) {
        this.lambda = lambda;
    }

    public long getNextArrivalTime() {
        double L = Math.exp(-lambda);
        double p = 1.0;
        int k = 0;
        
        do {
            k++;
            p *= Math.random();
        } while (p > L);
        
        return (k - 1) * 1000; // Convertir a milisegundos
    }
} 