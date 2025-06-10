package com.unaidario.Modelo;

import java.util.HashMap;

import com.unaidario.lectores.LectorMapa;

public class GestorMapa {
    HashMap<String, Mapa> mapas;
    Mapa mapaActual;

    

    public GestorMapa() {
        this.mapas = new HashMap<>();
        LectorMapa.leerMapa().forEach(mapa -> {
            mapas.put(mapa.getNivel(), mapa);
        });
        mapaActual = this.mapas.values().iterator().next();
    }


    /** 
     * @return HashMap<String, Mapa>
     */
    public HashMap<String,Mapa> getMapas() {
        return this.mapas;
    }

    /** 
     * @param mapas
     */
    public void setMapas(HashMap<String,Mapa> mapas) {
        this.mapas = mapas;
    }


    /** 
     * @return Mapa
     */
    public Mapa getMapaActual() {
        return this.mapaActual;
    }

    /** 
     * @param mapaActual
     */
    public void setMapaActual(Mapa mapaActual) {
        this.mapaActual = mapaActual;
    }



}
