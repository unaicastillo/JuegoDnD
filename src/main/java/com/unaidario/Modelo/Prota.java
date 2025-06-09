package com.unaidario.Modelo;

import java.util.ArrayList;

public class Prota extends Entidad {
    private int posicionX;
    private int posicionY;

    private Movimientos movimientos;

    public Prota(int vida, int ataque, int defensa, int evasion, int velocidad, int posicionX, int posicionY) {
        super(vida, ataque, defensa, evasion, velocidad);
        this.posicionX = 1;
        this.posicionY = 1;
        movimientos = new Movimientos();
    }

    /** 
     * @return int
     */
    public int getPosicionX() {
        return this.posicionX;
    }

    /** 
     * @param posicionX
     */
    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    /** 
     * @return int
     */
    public int getPosicionY() {
        return this.posicionY;
    }

    /** 
     * @param posicionY
     */
    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    /** 
     * @param mapa
     * @param tecla
     * @param entidades
     * @return ArrayList<Entidad>
     */
    /*
     * Tecla representa la tecla que se ha pulsado en la vista (w, a, s, d)
     * si es w -> le sumará a la posición actual del protagonista la posición 0 del
     * array ( que representa a arriba),
     * y dará como resultado la nueva posición.
     * 
     * pulsar w,a,s,d devuelve un int correspondiente con el índice del Array de
     * dirección, al cual al
     * sumarlo con la posición devuelve la posición correspondiente a la tecla
     * direccional pulsada
     */
    public ArrayList<Entidad> movimientoProta(int[][] mapa, int tecla, ArrayList<Entidad> entidades) {

        // Suma de la posición actual con la dirección correspondiente
        int[] nuevaPosicion = movimientos.sumPosiciones(new int[] { posicionX, posicionY },
                movimientos.getDireccion(tecla));

        if (comprobarSuelo(mapa, nuevaPosicion[0], nuevaPosicion[1])) {
            if (comprobarTipoSuelo(mapa, nuevaPosicion[0], nuevaPosicion[1]) == 0) {
                {

                    if (comprobarLibreDeEnemigos(entidades, nuevaPosicion[0], nuevaPosicion[1])) {
                        // Si la posición esta libre de obstáculos y de enemigos, se cambian los valores
                        // de posición a esta nueva
                        posicionX = nuevaPosicion[0];
                        posicionY = nuevaPosicion[1];
                    } else {
                        // Atacar al enemigo con posición = nuevaposicion[]
                        int num = buscarPosicion(entidades, nuevaPosicion[0], nuevaPosicion[1]);

                        // Ataca o elimina al prota si es que el ataque es mortal
                        if (entidades.get(num).ataqueFatal(ataque)) {
                            entidades.remove(num);
                        }

                    }
                }
            }
            // else {
            //      if (comprobarLibreDeEnemigos(entidades, nuevaPosicion[0], nuevaPosicion[1])) {
            //             posicionX = nuevaPosicion[0];
            //             posicionY = nuevaPosicion[1];
            //             setVida(getVida() - 1); 
            //         } else {
            //             int num = buscarPosicion(entidades, nuevaPosicion[0], nuevaPosicion[1]);

            //             if (entidades.get(num).ataqueFatal(ataque)) {
            //                 entidades.remove(num);
            //             }

            //         }
            // }
        }
        return entidades;

    }

    /** 
     * @param entidad
     * @return Entidad
     */
    public Entidad atacar(Entidad entidad) {

        if (entidad.getDefensa() > ataque) {
            entidad.setVida(entidad.getVida() - entidad.getDefensa() - ataque);
        } else {
            int resto = entidad.getDefensa();
            entidad.setVida(entidad.getVida() - (ataque - entidad.getDefensa()) / 2 - resto);
        }
        return entidad;
    }

    /** 
     * @param entidades
     * @param x
     * @param y
     * @return int
     */
    /*
     * Busca la posición en el ArrayList En la cual un enemigo tiene una posición
     */
    public int buscarPosicion(ArrayList<Entidad> entidades, int x, int y) {
        for (int i = 0; i < entidades.size(); i++) {

            if (entidades.get(i) instanceof Enemigo) {
                Enemigo enemigo = (Enemigo) entidades.get(i);
                if (enemigo.getPosicionX() == x && enemigo.getPosicionY() == y) {
                    return i;
                }
            }

        }
        return -1; // No encontrado
    }

    /** 
     * @param mapa
     * @param nuevaPosicionX
     * @param nuevaPosicionY
     * @return boolean
     */
    /*
     * Comprobar que la nueva posición a la que el enemigo se quiere desplazar, es
     * un suelo ( 0 )
     * Y además comprueba que no se salga detro de los límites del mapa
     */
    public boolean comprobarSuelo(int[][] mapa, int nuevaPosicionX, int nuevaPosicionY) {
        // Recuerda: mapa[fila][columna] = mapa[Y][X]
        if (nuevaPosicionX >= 0 && nuevaPosicionX < mapa[0].length && nuevaPosicionY >= 0 && nuevaPosicionY < mapa.length) {
            return mapa[nuevaPosicionY][nuevaPosicionX] == 0;
        } else {
            return false;
        }
    }
    
    /** 
     * @param mapa
     * @param nuevaPosicionX
     * @param nuevaPosicionY
     * @return int
     */
    public int comprobarTipoSuelo(int[][] mapa, int nuevaPosicionX, int nuevaPosicionY) {
        // Usa el mismo orden de índices que comprobarSuelo
        if (mapa[nuevaPosicionY][nuevaPosicionX] == 0) {
            return 0;
        } else {
            return 2;
        }
    }

    /** 
     * @param entidades
     * @param nuevaPosicionX
     * @param nuevaPosicionY
     * @return boolean
     */
    public boolean comprobarLibreDeEnemigos(ArrayList<Entidad> entidades, int nuevaPosicionX, int nuevaPosicionY) {

        boolean libre = true;
        for (Entidad entidad : entidades) {
            if (entidad instanceof Enemigo) {
                Enemigo enemigo = (Enemigo) entidad;
                if (enemigo.getPosicionX() == nuevaPosicionX && enemigo.getPosicionY() == nuevaPosicionY) {
                    libre = false;
                }
            }

        }
        return libre;
    }

}
