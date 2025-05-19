package com.unaidarioefra.Modelo;

import java.util.ArrayList;
import com.unaidarioefra.lectores.LectorEnemigo;

public class Juego {
    private static Juego instance;
    private int nivel;
    private ArrayList<Enemigo> enemigos; // Lista de enemigos

    public static Juego getInstance() {
        if (instance == null) {
            instance = new Juego();
        }
        return instance;
    }

    private Juego() {
        enemigos = new ArrayList<>(); // Inicializar la lista de enemigos
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public GestorMapa getGestorMapas() {
        return new GestorMapa();
    }

    public void iniciarentidades() {
        enemigos = LectorEnemigo.leerEnemigos();
    }

    public ArrayList<Enemigo> getEnemigos() {
        return enemigos; 
    }
}
