package com.unaidario.Modelo;

import java.util.ArrayList;
import java.util.Random;

import com.unaidario.Interfaz.Observer;
import com.unaidario.lectores.LectorEnemigo;

public class Juego {
    public Random rd= new Random();
    private static Juego instance;
    private int nivel;
    private GestorMapa GestorMapa; 
    private ArrayList<Enemigo> enemigos; 
    private Prota prota;
    private ArrayList<Entidad> entidades;
    ArrayList<Observer> observers;
    private Enemigo enem;

        
        
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
        prota = new Prota(0f, 0, 0,0, 0, 0, 0);
        enemigos = LectorEnemigo.leerEnemigos();
        observers = new ArrayList<>();
        entidades = new ArrayList<>();
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
    public ArrayList<Entidad> getEntidades() {
        return entidades; 
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
        entidades.clear();
        entidades = orden();
        for (int i = 0; i < entidades.size(); i++) {
            Entidad entidad = entidades.get(i);

            if (entidad instanceof Prota) {
                entidades = prota.movimientoProta(GestorMapa.getMapaActual().getMapa(), tecla, entidades);
            } 
            else {
                int posicion = posicionEnemigo(enemigos, ((Enemigo) entidad));
                entidades = enemigos.get(posicion).moverEnemigo(GestorMapa.getMapaActual().getMapa(), entidades);
            }
            
            // if(finalizarPartida()==true){
            //     return;
            // }
        }
        
        actualizarEntidades();
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
        if(prota.getVida() < 1 || enemigos.size()<1){
            return true;
        }
        else{
            return false;
        }
    }

    public void actualizarEntidades(){
        enemigos.clear();
        ArrayList<Enemigo> nuevosEnemigos = new ArrayList<>();

        for (Entidad entidad : entidades) {
            if(entidad instanceof Enemigo){
                nuevosEnemigos.add((Enemigo) entidad);
            }
            if(entidad instanceof Prota){
                prota = (Prota) entidad;
            }
        }
        this.enemigos = nuevosEnemigos;
    }

     public void maldito( ArrayList<Entidad> entity){
        int aleatorio=rd.nextInt(11);

        Entidad ent=entity.get(aleatorio);
        
                    if (ent instanceof Enemigo) {
                        enem = (Enemigo) ent;
                        Float vidaCambiada= enem.getVida()/4;
                        enem.setVida(enem.getVida() - vidaCambiada); 
                    }
                    else{
                        Float vidaCambiada= prota.getVida()/4;
                        prota.setVida(prota.getVida() - vidaCambiada); 
                }
            }
        }
