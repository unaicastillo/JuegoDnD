package com.unaidario.Modelo;

import java.util.ArrayList;

import com.unaidario.Interfaz.Observer;
import com.unaidario.lectores.LectorEnemigo;

public class Juego {
    private static Juego instance;
    private int nivel;
    private GestorMapa GestorMapa; 
    private ArrayList<Enemigo> enemigos; 
    private Prota prota;
    private Entidad entidadActual;
        ArrayList<Observer> observers;

        
        
        public void subscribe(Observer observer) {
            observers.add(observer);
        }
        
        public void unsubscribe(Observer observer) {
            observers.add(observer);
        }
        
        public void notifyObservers() {
            observers.forEach(item -> item.onChange());
        }
        
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
        observers = new ArrayList<>();
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
        for (int i = 0; i < ordenados.size(); i++) {
            Entidad entidad = ordenados.get(i);

            if (entidad instanceof Prota) {
                ordenados = prota.movimientoProta(GestorMapa.getMapaActual().getMapa(), tecla, ordenados);

                //Para finalizar abruptamente si es que se dan las condiciones para que se termine el juego
                if(finalizarPartida()==true){
                    return;
                }

            } else {
                int posicion = posicionEnemigo(enemigos, ((Enemigo) entidad));
                ordenados = enemigos.get(posicion).moverEnemigo(GestorMapa.getMapaActual().getMapa(), ordenados);

                if(finalizarPartida()==true){
                    return;
                }
                

            }
        }
        notifyObservers();

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
    

    public void certificarDefunción(ArrayList<Enemigo> enemigos){
        for (int i=0; i<enemigos.size(); i++) {

            if (enemigos.get(i).getVida() <= 0){
                enemigos.remove(i);
            }
        }
    }

    /*
     * Comprueba si se ha muerto el prota o todos los enemigos
     */
    public boolean finalizarPartida(){
        if(prota.getVida()<1 || enemigos.size()<1){
            return true;
        }
        else{
            return false;
        }
    }

    
}
