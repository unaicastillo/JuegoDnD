package com.unaidario.Modelo;

import java.util.HashMap;

public class Enemigo {
    private int tipo;
    private int vida;
    private int ataque;
    private int defensa;
    private int evasion;
    private int percepcion;
    private int velocidad;
    private int posicionX;
    private int posicionY;

    public Enemigo(int tipo, int vida, int ataque, int defensa, int evasion,int percepcion, int velocidad, int posicionX, int posicionY) {
        this.tipo = tipo;
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.evasion = evasion;
        this.velocidad = velocidad;
        this.percepcion = percepcion;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public Enemigo(HashMap<String, Integer> atributos) {
        this.vida = atributos.getOrDefault("SALUD", 0);
        this.ataque = atributos.getOrDefault("ATAQUE", 0);
        this.defensa = atributos.getOrDefault("DEFENSA", 0);
        this.evasion = atributos.getOrDefault("EVASION", 0);
        this.velocidad = atributos.getOrDefault("PERCEPCION", 0);
        this.posicionX = 0; // Inicializa la posición X
        this.posicionY = 0; // Inicializa la posición Y
    }

    public int getTipo() {
        return tipo;
    }

    public int getVida() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getEvasion() {
        return evasion;
    }

    public int getVelocidad() {
        return velocidad;
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

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

}
