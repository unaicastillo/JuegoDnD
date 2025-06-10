package com.unaidario.Modelo;

import java.util.ArrayList;

public class Prota extends Entidad {
    private int posicionX;
    private int posicionY;

    private Movimientos movimientos;

    public Prota(Float vida, int ataque, int defensa, int evasion, int velocidad, int posicionX, int posicionY) {
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
            else if(comprobarTipoSuelo(mapa, nuevaPosicion[0], nuevaPosicion[1])==2){
                 if (comprobarLibreDeEnemigos(entidades, nuevaPosicion[0], nuevaPosicion[1])) {
                        
                        posicionX = nuevaPosicion[0];
                        posicionY = nuevaPosicion[1];
                        Juego.getInstance().maldito(entidades);
                        
                    } else {
                        int num = buscarPosicion(entidades, nuevaPosicion[0], nuevaPosicion[1]);

                        if (entidades.get(num).ataqueFatal(ataque)) {
                            entidades.remove(num);
                        }

                    }
            }
        }
        return entidades;

    }

    public Entidad atacar(Entidad entidad) {

        if (entidad.getDefensa() > ataque) {
            entidad.setVida(entidad.getVida() - entidad.getDefensa() - ataque);
        } else {
            int resto = entidad.getDefensa();
            entidad.setVida(entidad.getVida() - (ataque - entidad.getDefensa()) / 2 - resto);
        }
        return entidad;
    }

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

    /*
     * Comprobar que la nueva posición a la que el enemigo se quiere desplazar, es
     * un suelo ( 0 )
     * Y además comprueba que no se salga detro de los límites del mapa
     */
    public boolean comprobarSuelo(int[][] mapa, int nuevaPosicionX, int nuevaPosicionY) {
        if (nuevaPosicionX < 20 && nuevaPosicionX > -1 && nuevaPosicionY < 20 && nuevaPosicionY > -1) {
            return mapa[nuevaPosicionY][nuevaPosicionX] != 1;
        } else {
            return false;
        }
    }

    public int comprobarTipoSuelo(int[][] mapa, int nuevaPosicionX, int nuevaPosicionY) {
        int Tipo = 0;
        if (mapa[nuevaPosicionX][nuevaPosicionY] == 0) {
            return Tipo;
        } else {
            Tipo = 2;
            return Tipo;
        }
    }

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
