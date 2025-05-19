package com.unaidario.Modelo;

import java.util.ArrayList;
import java.util.HashMap;

import com.unaidario.Interfaz.Observer;
import com.unaidario.lectores.LectorMapa;

public class GestorMapa {
    HashMap<String, Mapa> mapas;
    ArrayList<Observer> observers;
    Mapa mapaActual;

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void unsubscribe(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        observers.forEach(item -> item.onChange());
    }

    public GestorMapa() {
        this.mapas = new HashMap<>();
        LectorMapa.leerMapa().forEach(mapa -> {
            mapas.put(mapa.getNivel(), mapa);
        });
        observers = new ArrayList<>();
        mapaActual = this.mapas.values().iterator().next();
    }

    public boolean avanzarAlSiguienteMapa() {
        ArrayList<Mapa> listaMapas = new ArrayList<>(mapas.values());
        int indiceActual = listaMapas.indexOf(mapaActual);
        if (indiceActual < listaMapas.size() - 1) {
            mapaActual = listaMapas.get(indiceActual + 1);
            notifyObservers();
            return true;
        } else {
            return false;
        }
    }

    public HashMap<String,Mapa> getMapas() {
        return this.mapas;
    }

    public void setMapas(HashMap<String,Mapa> mapas) {
        this.mapas = mapas;
    }

    public ArrayList<Observer> getObservers() {
        return this.observers;
    }

    public void setObservers(ArrayList<Observer> observers) {
        this.observers = observers;
    }

    public Mapa getMapaActual() {
        return this.mapaActual;
    }

    public void setMapaActual(Mapa mapaActual) {
        this.mapaActual = mapaActual;
    }



}
