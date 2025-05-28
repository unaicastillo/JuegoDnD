package com.unaidario.Modelo;

import java.util.ArrayList;

public class Prota extends Entidad {
    private int posicionX;
    private int posicionY;
    public Prota(int vida, int ataque, int defensa, int evasion, int velocidad , int posicionX, int posicionY) {
        super(vida, ataque, defensa, evasion, velocidad);
        this.posicionX = 1;
        this.posicionY = 1;
        movimientos = new Movimientos();
    }

    public int getPosicionX() {
        return this.posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return this.posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }
  

    /*
     * Tecla representa la tecla que se ha pulsado en la vista (w, a, s, d)
     *  si es w -> le sumará a la posición actual del protagonista la posición 0 del array ( que representa a arriba),
     *  y dará como resultado la nueva posición.
     * 
     *  pulsar w,a,s,d devuelve un int correspondiente con el índice del Array de dirección, al cual al 
     *  sumarlo con la posición devuelve la posición correspondiente a la tecla direccional pulsada
     */
    public void movimientoProta(int[][] mapa, int tecla, ArrayList<Enemigo> enemigos){

        //Suma de la posición actual con la dirección correspondiente 
        int[] nuevaPosicion = movimientos.sumPosiciones(new int[] {posicionX,posicionY}, movimientos.getDireccion(tecla));
        

        if (comprobarSuelo(mapa, nuevaPosicion[0], nuevaPosicion[1])){
            if(comprobarLibreDeEnemigos(enemigos, nuevaPosicion[0], nuevaPosicion[1])){
                // Si la posición esta libre de obstáculos y de enemigos, se cambian los valores de posición a esta nueva
                posicionX = nuevaPosicion[0];
                posicionY = nuevaPosicion[1];
            }
            else {
                // Atacar al enemigo con posición = nuevaposicion[]
            }
        }
        

    }


        /*
     * Comprobar que la nueva posición a la que el enemigo se quiere desplazar, es un suelo ( 0 )
     * Y además comprueba que no se salga detro de los límites del mapa
     */
    public boolean comprobarSuelo(int[][] mapa, int nuevaPosicionX, int nuevaPosicionY){
        if (nuevaPosicionX < 20 && nuevaPosicionX > -1 && nuevaPosicionY < 20 && nuevaPosicionY > -1){
            return mapa[nuevaPosicionY][nuevaPosicionX] == 0;
        } else {
            return false;
        }
    }



    public boolean comprobarLibreDeEnemigos(ArrayList<Enemigo> enemigos, int nuevaPosicionX, int nuevaPosicionY){

        boolean libre = true;
        for (Enemigo enemigo : enemigos) {
            if(enemigo.getPosicionX()==nuevaPosicionX && enemigo.getPosicionY()==nuevaPosicionY){
                libre = false;
            }
        }
        return libre;
    }



}


