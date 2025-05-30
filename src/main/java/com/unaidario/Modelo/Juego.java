package com.unaidario.Modelo;

import java.util.ArrayList;

import com.unaidario.lectores.LectorEnemigo;

public class Juego {
    private static Juego instance;
    private int nivel;
    private GestorMapa GestorMapa; 
    private ArrayList<Enemigo> enemigos; 
    private Prota prota;
    private Entidad entidadActual;
    
    public static Juego getInstance() {
        if (instance == null) {
            instance = new Juego();
        }
        return instance;
    }
    
    private Juego() {
        GestorMapa = new GestorMapa();
        prota = new Prota(0, 0, 0,0, 0, 0, 0);
        enemigos = LectorEnemigo.leerEnemigos();
        
    }
    public Entidad getEntidadActual() {
        return entidadActual;
    }
    public void setEntidadActual(Entidad entidadActual) {
        this.entidadActual = entidadActual;
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
    public ArrayList<Entidad> orden() {
        ArrayList<Entidad> ordenados = new ArrayList<>();
        ordenados.add(prota);
        ordenados.addAll(enemigos);
        ordenados.sort(null); 
        return ordenados;
    }
    public void Turnos(int tecla){
        ArrayList<Entidad> ordenados = orden();
        for (Entidad entidad : ordenados) {

            if (entidad instanceof Prota) {
                prota.movimientoProta(GestorMapa.getMapaActual().getMapa(), tecla, enemigos);

            } else {
                int posicion = posicionEnemigo(enemigos, ((Enemigo) entidad));
                enemigos.get(posicion).moverEnemigo(GestorMapa.getMapaActual().getMapa(), prota, enemigos);
                

            }
        }
    }

    public int posicionEnemigo(ArrayList<Enemigo> enemigos, Enemigo enemigoBuscado) {
        for (int i = 0; i < enemigos.size(); i++) {
            Enemigo e = enemigos.get(i);
            if (e.getPosicionX() == enemigoBuscado.getPosicionX() &&
                e.getPosicionY() == enemigoBuscado.getPosicionY() &&
                e.getVida() == enemigoBuscado.getVida() &&
                e.getAtaque() == enemigoBuscado.getAtaque() &&
                e.getDefensa() == enemigoBuscado.getDefensa()) {
                return i;
            }
        }
        return -1; // No encontrado
    }
 
    
}
