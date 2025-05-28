package com.unaidario.Modelo;

<<<<<<< HEAD
public class Prota {
    public int vida;
    public int ataque;
    public int defensa;
    public int evasion;
    public int velocidad;
    public int posicionX;
    public int posicionY;
    private Movimientos movimientos;

    public Prota(){
        movimientos = new Movimientos();
        posicionX = 2;
        posicionY = 2;
    }
    public Prota(int vida, int ataque, int defensa, int evasion, int velocidad) {
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
        this.evasion = evasion;
        this.velocidad = velocidad;
    }
    public int getVida() {
        return vida;
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

    public int getPosicionX(){
        return posicionX;
    }

    public int getPosicionY(){
        return posicionY;
    }

    public void setPosicionX(int posicionX){
        this.posicionX = posicionX;
    }

    public void setPosicionY(int posicionY){
        this.posicionY = posicionY;
    }


=======
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
  
>>>>>>> 67920aa5bf810ba26fd21fab52dfeac06b746fb2

}
