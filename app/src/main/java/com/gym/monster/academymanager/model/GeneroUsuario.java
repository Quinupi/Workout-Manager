package com.gym.monster.academymanager.model;

/**
 * Created by victo on 22/08/2017.
 */

public class GeneroUsuario {
    private int idGenero;
    private String nomeGenero;

    public GeneroUsuario() {
    }

    public GeneroUsuario(int idGenero, String nomeGenero) {
        this.idGenero = idGenero;
        this.nomeGenero = nomeGenero;
    }

    public int getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
    }

    public String getNomeGenero() {
        return nomeGenero;
    }

    public void setNomeGenero(String nomeGenero) {
        this.nomeGenero = nomeGenero;
    }
}
