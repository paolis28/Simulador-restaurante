package com.simulator;

import java.util.ArrayList;
import java.util.List;

import com.simulator.controllers.RestauranteMonitor;
import com.simulator.models.Comensal;


public class Main {
    private static final int TOTAL_COMENSALES = 100;

    public static void main(String[] args) {
        RestauranteMonitor restaurante = new RestauranteMonitor();
        List<Thread> hilosComensales = new ArrayList<>();

        // Crear 100 comensales
        for (int i = 0; i < TOTAL_COMENSALES; i++) {
            Comensal comensal = new Comensal(i);
            Thread hiloComensal = new Thread(() -> {
                try {
                    boolean pudoEntrar = restaurante.intentarEntrar(comensal);
                    if (pudoEntrar) {
                        System.out.println("Comensal " + comensal.getId() + " entró al restaurante");
                        // Aquí iría la lógica de estar en el restaurante
                        Thread.sleep((long) (Math.random() * 5000)); // Simular tiempo en el restaurante
                        restaurante.salirRestaurante();
                        System.out.println("Comensal " + comensal.getId() + " salió del restaurante");
                    } else {
                        System.out.println("Comensal " + comensal.getId() + " no pudo entrar, restaurante lleno");
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