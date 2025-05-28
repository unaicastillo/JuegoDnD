package com.unaidario.Modelo;

public class Entidad implements Comparable<Entidad> {
    public int vida;
    public int ataque;
    public int defensa;
    public int evasion;
    public int velocidad;

    public Movimientos movimientos;
    public Entidad(int vida, int ataque, int defensa, int evasion, int velocidad) {
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.evasion = evasion;
        this.velocidad = velocidad;
        movimientos = new Movimientos();
    }

   public void setVida(int vida) {
        
        if (vida < 0|| vida > 20) {
            this.vida=15;
        }
        this.vida = vida;
    }
    public int getAtaque() {
        return ataque;
    }
    public void setAtaque(int ataque) {
        
        if (ataque < 0|| ataque > 20) {
            this.ataque=15;
        }
        this.ataque =ataque ;
    }
    public int getDefensa() {
        return defensa;
    }
    public void setDefensa(int defensa) {
        if (defensa< 0|| defensa> 20) {
            this.defensa=15;
        }
        this.defensa = defensa;
    }
    public int getEvasion() {
        return evasion;
    }
    public void setEvasion(int evasion) {
        if (evasion < 0|| evasion > 20) {
            this.evasion=15;
        }
        this.evasion = evasion;
    }
    public int getVelocidad() {
        return velocidad;
    }
    public void setVelocidad(int velocidad) {
        if (velocidad< 0|| velocidad> 20) {
            this.velocidad=15;
        }
        
        this.velocidad = velocidad;
    }
      public int getVida() {
        return vida;
    }
    @Override
    public int compareTo(Entidad otra) {
        return Integer.compare(this.getVelocidad(), otra.getVelocidad());
    }
}
