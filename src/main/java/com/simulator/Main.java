package com.simulator;

import java.util.ArrayList;
import java.util.List;

import com.simulator.config.Config;
import com.simulator.controllers.ReceptionistMonitor;
import com.simulator.models.Comensal;
import com.simulator.utils.PoissonDistribution;

public class Main {
    public static void main(String[] args) {
        ReceptionistMonitor restaurante = new ReceptionistMonitor();
        List<Thread> hilosComensales = new ArrayList<>();
        PoissonDistribution poissonDist = new PoissonDistribution(Config.LAMBDA); // Usar Config.LAMBDA

        // Crear 100 comensales
        for (int i = 0; i < Config.TOTAL_COMENSALES; i++) { // Usar Config.TOTAL_COMENSALES
            Comensal comensal = new Comensal(i);
            Thread hiloComensal = new Thread(() -> {
                try {
                    Thread.sleep(poissonDist.getNextArrivalTime());
                    boolean pudoEntrar = false;
                    // Seguir intentando hasta lograr entrar
                    while (!pudoEntrar) {
                        pudoEntrar = restaurante.intentarEntrar(comensal);
                        if (pudoEntrar) {
                            System.out.println("Comensal " + comensal.getId() + " ha entrado al restaurante. Aforo actual: " + restaurante.getComensalesActuales() + "/" + Config.AFORO_MAXIMO); // Usar Config.AFORO_MAXIMO
                            Thread.sleep((long) (Math.random() * 5000));
                            restaurante.salirRestaurante();
                            System.out.println("Comensal " + comensal.getId() + " ha salido del restaurante. Aforo actual: " + restaurante.getComensalesActuales() + "/" + Config.AFORO_MAXIMO); // Usar Config.AFORO_MAXIMO
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