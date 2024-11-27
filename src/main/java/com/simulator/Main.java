package com.simulator;

import java.util.ArrayList;
import java.util.List;

import com.simulator.controllers.RestauranteMonitor;
import com.simulator.models.Comensal;
import com.simulator.utils.PoissonDistribution;


public class Main {
    private static final int TOTAL_COMENSALES = 100;
    private static final double LAMBDA = 2.0; // Tasa media de llegadas por minuto

    public static void main(String[] args) {
        RestauranteMonitor restaurante = new RestauranteMonitor();
        List<Thread> hilosComensales = new ArrayList<>();
        PoissonDistribution poissonDist = new PoissonDistribution(LAMBDA);

        // Crear 100 comensales
        for (int i = 0; i < TOTAL_COMENSALES; i++) {
            Comensal comensal = new Comensal(i);
            Thread hiloComensal = new Thread(() -> {
                try {
                    Thread.sleep(poissonDist.getNextArrivalTime());
                    boolean pudoEntrar = false;
                    // Seguir intentando hasta lograr entrar
                    while (!pudoEntrar) {
                        pudoEntrar = restaurante.intentarEntrar(comensal);
                        if (pudoEntrar) {
                            System.out.println("Comensal " + comensal.getId() + " ha entró al restaurante");
                            Thread.sleep((long) (Math.random() * 5000));
                            restaurante.salirRestaurante();
                            System.out.println("Comensal " + comensal.getId() + " ha salió del restaurante");
                        } else {
                            System.out.println("Comensal " + comensal.getId() + " ha de esperar, restaurante lleno");
                            Thread.sleep(1000); // Esperar un momento antes de volver a intentar
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            hilosComensales.add(hiloComensal);
        }

        // Iniciar todos los hilos
        hilosComensales.forEach(Thread::start);
    }
}