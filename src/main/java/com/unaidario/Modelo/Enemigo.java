package com.unaidario.Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Enemigo extends Entidad {
    private int tipo;
    private int percepcion;
    private int posicionX;  //Columna
    private int posicionY;  //Fila

    private Random r = new Random();
    private Movimientos movimientos;
    
    public Enemigo(int tipo, int vida, int ataque, int defensa, int evasion,int percepcion, int velocidad, int posicionX, int posicionY) {
        super(vida, ataque, defensa, evasion, velocidad);
        this.tipo = tipo;
        this.percepcion = percepcion;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        movimientos = new Movimientos();
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }
    public int getPercepcion() {
        return percepcion;
    }
    public void setPercepcion(int percepcion) {
        this.percepcion = percepcion;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    

    /*
     * A partir de aqui se debe de gestionar todo para que el enemigo se mueva.
     * Se necesita:
     *      -Mapa
     *      -Array<Enemigos> para obtener sus posiciones
     *      -Posición Protagonista = para calcular con percepcion
     *      
     * 
     */


    public void moverEnemigo(int[][] mapa, Prota prota, ArrayList<Enemigo> enemigos){

        
        //Comprobar si el protagonista se encuentra dentro del rango de visión
        boolean detectado = Math.sqrt(Math.pow(prota.getPosicionX() - posicionX, 2) + Math.pow(prota.getPosicionY() - posicionY, 2)) <= percepcion;
        if(detectado){
            IAenemigo(prota, mapa, enemigos);
        }
        else{
            movimientoErrante(mapa, enemigos);
        }
    }

    /*
     * En este método hacemos que el enemio se mueba una posición aleatoria.
     * Si después de 10 intentos no se consigue encontrar una posición libre, el enemigo no se mueve y termina su turno.
     */
    public void movimientoErrante(int[][] mapa, ArrayList<Enemigo> enemigos){

        ArrayList<int[]> direc = new ArrayList<>(movimientos.getDirecciones()); //Crea un Arraylis identico al de direcciones
        Collections.shuffle(direc); // Desordena la lista

        int nuevaX = 0;
        int nuevaY = 0;

        
        for (int[] direccion : direc) {
            nuevaX = posicionX + direccion[0];
            nuevaY = posicionY + direccion[1];
        
            // Comprueba que la nueva posición es válida
            if (comprobarLibreDeEnemigos(enemigos, nuevaX, nuevaY) && comprobarSuelo(mapa, nuevaX, nuevaY)) {
                posicionX = nuevaX;
                posicionY = nuevaY;
                return; // Salir del método
            }
        }
        

    }


    /*
     * Una vez se ha comprobado que la posición a la que el enemigo se va a mover sea una pared y este
     * libre. Vamos a comprobar que la posición este ocupada por el protagonista para:
     *      1. Moverse a la nueva posición
     *      2. No moverse y mandar la función de ataque al Protagonista
     */
    public boolean comprobarOcupadoPorProta(int nuevaPosicionX, int nuevaPosicionY, Prota prota){

        if(prota.getPosicionX()== nuevaPosicionX && prota.getPosicionY()== nuevaPosicionY){
            return true;
        }
        else{   //Se actualiza la posición del enemigo al comprobar que no va a atacar al prota
            return false;
        }
    }


    // public Prota atacarProta(Prota prota){
    //     int defensaProta  = prota.getDefensa()
    //     if(prota.getDefensa() > ataque){
    //         prota.setVida(prota.getVida() - Math.round(ataque/2));
    //     }
    //     else if(prota.getDefensa() < ataque && prota.getDefensa())
    //     else if
    // }


    /*
     * Esta clase comprueba que la posición a la que se quiere mover el enemigo no este ocupada por otro enemigo
     */
    public boolean comprobarLibreDeEnemigos(ArrayList<Enemigo> enemigos, int nuevaPosicionX, int nuevaPosicionY){

        boolean libre = true;


        for (Enemigo enemigo : enemigos) {
            if(enemigo.getPosicionX()==nuevaPosicionX && enemigo.getPosicionY()==nuevaPosicionY){
                libre = false;
            }
        }
        return libre;
    }


    /*
     * Este método es la IA que permite al enemigo buscar la posición mas eficiente para perseguir y
     * Aracar al protagonista.
     * Si la posición mas óptima esta ocupada, tomara la seegunda posición mas óptima.
     * Si ninguna de las dos esta libre, se movera aleatoriamente por el método movimientoErrante
     */
    public void IAenemigo(Prota prota, int[][] mapa, ArrayList<Enemigo> enemigos){

        int[] mejorDir = null;
        int[] segundaMejorDir = null;

        int menorDistancia = Integer.MAX_VALUE;
        int segundaMenorDistancia = Integer.MAX_VALUE;


        /*
         *  1.Buscar primera y segunda mejor dirección
         *          -for each con ArrayList<Direccion> direcciones de Movimientos
         *              -Comprobar que esa posición esta libre y es un suelo para guardar la posición
         */
        for (int[] dir : movimientos.getDirecciones()) {
            int nuevaX = posicionX + dir[0];
            int nuevaY = posicionY + dir[1];
            int distancia = Math.abs(nuevaX - prota.getPosicionX()) + Math.abs(nuevaY - prota.getPosicionY());

            if (comprobarSuelo(mapa, nuevaX, nuevaY) && comprobarLibreDeEnemigos(enemigos, nuevaX, nuevaY)) {
                if (distancia < menorDistancia) {
                    // Guardar las posiciones
                    segundaMejorDir = mejorDir;
                    segundaMenorDistancia = menorDistancia;

                    mejorDir = dir;
                    menorDistancia = distancia;
                } 
                else if (distancia < segundaMenorDistancia) {
                    segundaMejorDir = dir;
                    segundaMenorDistancia = distancia;
                }
            }
        }

        /*
         * 2.  Si la nueva posición no esta ocupada y no es un suelo, si no la segunda, y si tampoco 
         */
        if (mejorDir != null ) {
            if (!comprobarOcupadoPorProta(posicionX + mejorDir[0], posicionY + mejorDir[1], prota)){
                posicionX += mejorDir[0];
                posicionY += mejorDir[1];
            }
            else{
                //Atacar Prota
            }


        }
        else if (segundaMejorDir != null) {
            if (!comprobarOcupadoPorProta(posicionX + segundaMejorDir[0], posicionY + segundaMejorDir[1], prota)){
                posicionX += segundaMejorDir[0];
                posicionY += segundaMejorDir[1];
            }
            else{
                //AtacarProta
            }
        }
        else {
            movimientoErrante(mapa, enemigos);
        }


    }


    /*
     * Comprobar que la nueva posición a la que el enemigo se quiere desplazar, es un suelo ( 0 )
     * Y además comprueba que no se salga detro de los límites del mapa
     */
    public boolean comprobarSuelo(int[][] mapa, int nuevaPosicionX, int nuevaPosicionY){
        if (nuevaPosicionX <20 && nuevaPosicionX >-1 && nuevaPosicionY <20 && nuevaPosicionY >-1){
            if(mapa[nuevaPosicionY][nuevaPosicionX] == 0){
                return true;
            }
            else {
                return false;
            }
        }
        else{
            return false;
        }
    }


}
