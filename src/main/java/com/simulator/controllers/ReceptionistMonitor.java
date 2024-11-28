package com.simulator.controllers;

import com.simulator.config.Config;
import com.simulator.models.Comensal;
import com.simulator.models.EstadoComensal;

public class ReceptionistMonitor { 
    private int comensalesActuales;
    private boolean recepcionistaOcupado;

    public ReceptionistMonitor() {
        this.comensalesActuales = 0;
        this.recepcionistaOcupado = false;
    }

    public synchronized boolean intentarEntrar(Comensal comensal) throws InterruptedException {
        // Esperar si el recepcionista está ocupado
        while (recepcionistaOcupado) {
            wait();
        }
        
        // Marcar al recepcionista como ocupado
        recepcionistaOcupado = true;
        
        // Verificar si hay espacio disponible
        if (comensalesActuales >= Config.AFORO_MAXIMO) {
            recepcionistaOcupado = false;
            notifyAll();
            return false;
        }

        // Si hay espacio, incrementar el contador y permitir entrada
        comensalesActuales++;
        comensal.setEstado(EstadoComensal.EN_RESTAURANTE);
        
        // Imprimir el aforo actual después de que el comensal entra
        System.out.println("Comensal " + comensal.getId() + " ha entrado al restaurante. Aforo actual: " + comensalesActuales + "/" + Config.AFORO_MAXIMO);
        
        // Liberar al recepcionista
        recepcionistaOcupado = false;
        notifyAll();
        return true;
    }

    public synchronized void salirRestaurante() {
        comensalesActuales--; // Decrementar el número de comensales actuales
        // Imprimir el aforo actual después de que el comensal sale
        System.out.println("Un comensal ha salido. Aforo actual: " + comensalesActuales + "/" + Config.AFORO_MAXIMO);
        notifyAll(); // Notificar a otros hilos
    }

    public synchronized int getComensalesActuales() {
        return comensalesActuales;
    }
}