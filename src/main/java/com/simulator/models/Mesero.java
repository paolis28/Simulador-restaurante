package com.simulator.models;

public class Mesero {
    private int id;
    private EstadoMesero estado;
   
    public Mesero(int id){
        this.id = id;
        this.estado = EstadoMesero.DESCANSANDO;
    }

    public int getId(){
        return id;
    }

    public EstadoMesero getEstado(){
        return estado;
    }

    public void setEstado(EstadoMesero estado){
        this.estado = estado;
    }
}
