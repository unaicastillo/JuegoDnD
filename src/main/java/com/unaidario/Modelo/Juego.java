package com.unaidario.Modelo;

import java.util.ArrayList;

import com.unaidario.lectores.LectorEnemigo;

public class Juego {
    private static Juego instance;
    private int nivel;

    public static Juego getInstance() {
        if (instance == null) {
            instance = new Juego();
        }
        return instance;
    }

    public void turno() {

    }

    private Juego() {

    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public GestorMapa getGestorMapas() {
        return GestorMapa;
    }

    public void iniciarentidades() {
        enemigos = LectorEnemigo.leerEnemigos();
    }

    public ArrayList<Enemigo> getEnemigos() {
        return enemigos;
    }
    public Prota getProta() {
        return prota;
    }
    public void setProta(Prota prota) {
        this.prota = prota;
    }
}
