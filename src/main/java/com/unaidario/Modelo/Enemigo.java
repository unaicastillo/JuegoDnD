package com.unaidario.Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

public class Enemigo extends Entidad {
    private int tipo;
    private int percepcion;
    private int posicionX;
    private int posicionY;

    private Random r = new Random();
    private Movimientos movimientos;
    
    public Enemigo(int tipo, int vida, int ataque, int defensa, int evasion,int percepcion, int velocidad, int posicionX, int posicionY) {
        super(vida, ataque, defensa, evasion, velocidad);
        this.tipo = tipo;
        this.percepcion = percepcion;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
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

}
