package com.simulator.models;

public class Comensal {
    private int id;
    private EstadoComensal estado;

    public Comensal(int id) {
        this.id = id;
        this.estado = EstadoComensal.ESPERANDO;
    }

    public int getId() {
        return id;
    }

    public EstadoComensal getEstado() {
        return estado;
    }

    public void setEstado(EstadoComensal estado) {
        this.estado = estado;
    }
}