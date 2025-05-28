package com.unaidario.Modelo;

public class Prota extends Entidad {
    private int posicionX;
    private int posicionY;
    
    public Prota(int vida, int ataque, int defensa, int evasion, int velocidad , int posicionX, int posicionY) {
        super(vida, ataque, defensa, evasion, velocidad);
        this.posicionX = 1;
        this.posicionY = 1;
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
  

}
