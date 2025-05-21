package com.unaidario.Modelo;

import java.util.ArrayList;

import com.unaidario.lectores.LectorEnemigo;

public class Juego {
    private static Juego instance;
    private int nivel;
    private GestorMapa GestorMapa; 
    private ArrayList<Enemigo> enemigos; 
    
    public static Juego getInstance() {
        if (instance == null) {
            instance = new Juego();
        }
        return instance;
    }
    
    private Juego() {
        GestorMapa = new GestorMapa(); 
        enemigos = LectorEnemigo.leerEnemigos();
        
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
}
