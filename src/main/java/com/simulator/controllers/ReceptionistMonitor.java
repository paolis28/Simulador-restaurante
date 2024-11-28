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
        
        // Liberar al recepcionista
        recepcionistaOcupado = false;
        notifyAll();
        return true;
    }

    public synchronized void salirRestaurante() {
        comensalesActuales--;
        notifyAll();
    }

    public synchronized int getComensalesActuales() {
        return comensalesActuales;
    }
}