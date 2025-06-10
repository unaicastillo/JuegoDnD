package com.unaidario.Modelo;

import java.util.ArrayList;

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

   /** 
    * @param vida
    */
   public void setVida(int vida) {
        
        if (vida < 0|| vida > 20) {
            this.vida=15;
        }
        else{
            this.vida = vida;
        }
    }
    /** 
     * @return int
     */
    public int getAtaque() {
        return ataque;
    }
    /** 
     * @param ataque
     */
    public void setAtaque(int ataque) {
        
        if (ataque < 0|| ataque > 20) {
            this.ataque=15;
        }
        else{
            this.ataque =ataque ;
        }
    }
    /** 
     * @return int
     */
    public int getDefensa() {
        return defensa;
    }
    /** 
     * @param defensa
     */
    public void setDefensa(int defensa) {
        if (defensa< 0|| defensa> 20) {
            this.defensa=15;
        }
        else{
            this.defensa = defensa;
        }
    }
    /** 
     * @return int
     */
    public int getEvasion() {
        return evasion;
    }
    /** 
     * @param evasion
     */
    public void setEvasion(int evasion) {
        if (evasion < 0|| evasion > 20) {
            this.evasion=15;
        }
        else{
            this.evasion = evasion;

        }
    }
    /** 
     * @return int
     */
    public int getVelocidad() {
        return velocidad;
    }
    /** 
     * @param velocidad
     */
    public void setVelocidad(int velocidad) {
        if (velocidad< 0|| velocidad> 20) {
            this.velocidad=15;
        }
        
        else{this.velocidad = velocidad;}
    }
      /** 
       * @return int
       */
      public int getVida() {
        return vida;
    }
    /** 
     * @param otra
     * @return int
     */
    @Override
    public int compareTo(Entidad otra) {
        return Integer.compare(this.getVelocidad(), otra.getVelocidad());
    }

    
    public void moverse(int[][] mapa, ArrayList<Entidad> entidades){}

    /** 
     * @param ataqueRecibido
     */
    public void atacado(int ataqueRecibido){
        if(defensa >= ataqueRecibido){
            this.vida --;
        }
        else{
            this.vida = vida - (ataqueRecibido-defensa);
        }
    }

    /** 
     * @param ataqueRecibido
     * @return boolean
     */
    /*Si el ataque que va a ser mortar devuelve true para eliminar directamente al enemigo, si no es fatal,
     * devuelve false y se ataca automáticamente a esta entidad.
     */
    public boolean ataqueFatal(int ataqueRecibido){
        if((vida+defensa)-ataqueRecibido <1 || vida == 1){
            return true;
        }
        else{
            atacado(ataqueRecibido);
            return false;
        }
    }
}
