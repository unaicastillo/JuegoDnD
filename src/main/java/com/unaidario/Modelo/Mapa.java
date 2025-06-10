package com.unaidario.Modelo;

public class Mapa {
    private String suelo;
    private String pared;
    private String nivel;
    private int[][] mapa;
    public Mapa(String suelo, String pared, String nivel, int[][] mapa) {
        this.suelo = suelo;
        this.pared = pared;
        this.nivel = nivel;
        this.mapa = mapa;
    }
    /** 
     * @return String
     */
    public String getSuelo() {
        return suelo;
    }   
    /** 
     * @param suelo
     */
    public void setSuelo(String suelo) {
        this.suelo = suelo;
    }
    /** 
     * @return String
     */
    public String getPared() {
        return pared;
    }
    /** 
     * @param pared
     */
    public void setPared(String pared) {
        this.pared = pared;
    }
    /** 
     * @return String
     */
    public String getNivel() {
        return nivel;
    }
    /** 
     * @param nivel
     */
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    /** 
     * @return int[][]
     */
    public int[][] getMapa() {
        return mapa;
    }
    /** 
     * @param mapa
     */
    public void setMapa(int[][] mapa) {
        this.mapa = mapa;
    }
}
