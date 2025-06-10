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
    private ArrayList<Entidad> entidades;
    ArrayList<Observer> observers;

        
        
        /** 
         * @param observer
         */
        public void subscribe(Observer observer) {
            observers.add(observer);
        }
        
        /** 
         * @param observer
         */
        public void unsubscribe(Observer observer) {
            observers.add(observer);
        }
        
        public void notifyObservers() {
            observers.forEach(item -> item.onChange());
        }
        
        /** 
         * @return Juego
         */
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
        entidades = new ArrayList<>();
    }

    /** 
     * @return int
     */
    public int getNivel() {
        return nivel;
    }

    /** 
     * @param nivel
     */
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    /** 
     * @return GestorMapa
     */
    public GestorMapa getGestorMapas() {
        return GestorMapa;
    }

    public void iniciarentidades() {
        enemigos = LectorEnemigo.leerEnemigos();
    }

    /** 
     * @return ArrayList<Enemigo>
     */
    public ArrayList<Enemigo> getEnemigos() {
        return enemigos; 
    }
    /** 
     * @return ArrayList<Entidad>
     */
    public ArrayList<Entidad> getEntidades() {
        return entidades; 
    }
    /** 
     * @return Prota
     */
    public Prota getProta() {
        return prota;
    }
    /** 
     * @param prota
     */
    public void setProta(Prota prota) {
        this.prota = prota;
    }
    /** 
     * @return ArrayList<Entidad>
     */
    public ArrayList<Entidad> orden() {
        ArrayList<Entidad> ordenados = new ArrayList<>();
        ordenados.add(prota);
        ordenados.addAll(enemigos);
        ordenados.sort(null); 
        return ordenados;
    }
    /** 
     * @param tecla
     */
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

    /** 
     * @param enemigos
     * @param enemigoBuscado
     * @return int
     */
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
    

    /** 
     * @param enemigos
     */
    public void certificarDefunción(ArrayList<Enemigo> enemigos){
        for (int i=0; i<enemigos.size(); i++) {

            if (enemigos.get(i).getVida() <= 0){
                enemigos.remove(i);
            }
        }
    }

    /** 
     * @return boolean
     */
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

    
}
