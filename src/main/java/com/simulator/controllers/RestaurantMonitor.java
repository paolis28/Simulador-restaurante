package com.simulator.controllers;

import com.simulator.models.Comensal;
import com.simulator.models.Mesero;

import java.util.LinkedList;
import java.util.Queue;

public class RestaurantMonitor {
    private int comensalesActuales;
    private int meserosDisponibles;
    private Queue<String> bufferPedidos; // Buffer para almacenar pedidos

    public RestaurantMonitor(){
        this.comensalesActuales = 0;
        this.meserosDisponibles = 2;
        this.bufferPedidos = new LinkedList<>(); // Inicializar el buffer
    }

    public synchronized boolean intentarPedir(Comensal comensal) throws InterruptedException {
        while(meserosDisponibles == 0){
            wait();
        }

        meserosDisponibles--;
        // Aquí se puede agregar la lógica para crear un pedido
        String pedido = "Comensal " + comensal.getId() + " ha sido atendido"; // Ejemplo de pedido
        bufferPedidos.add(pedido); // Añadir el pedido al buffer
        System.out.println(pedido + ". Longitud del buffer de pedidos: " + bufferPedidos.size());
        
        // Simular el tiempo que tarda el mesero en atender el pedido
        Thread.sleep(1000); // Simulación de tiempo de atención
        meserosDisponibles++; // Liberar el mesero
        notifyAll(); // Notificar a otros hilos
        return true;
    }
}