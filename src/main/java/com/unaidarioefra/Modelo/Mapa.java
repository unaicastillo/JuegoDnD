package com.unaidarioefra.Modelo;

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
    public String getSuelo() {
        return suelo;
    }   
    public void setSuelo(String suelo) {
        this.suelo = suelo;
    }
    public String getPared() {
        return pared;
    }
    public void setPared(String pared) {
        this.pared = pared;
    }
    public String getNivel() {
        return nivel;
    }
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
    public int[][] getMapa() {
        return mapa;
    }
    public void setMapa(int[][] mapa) {
        this.mapa = mapa;
    }
}
