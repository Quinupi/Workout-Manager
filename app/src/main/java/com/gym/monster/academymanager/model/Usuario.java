package com.gym.monster.academymanager.model;

/**
 * Created by victo on 17/08/2017.
 */

public class Usuario {
    private String nomeUsuario, emailUsuario;
    private Double pesoUsuario, alturaUsuario;
    private int generoUsuario, tipoDeTreinoUsuario;

    public Usuario() {
    }

    public Usuario(String nomeUsuario, String emailUsuario, Double pesoUsuario, Double alturaUsuario, int generoUsuario, int tipoDeTreinoUsuario) {
        this.nomeUsuario = nomeUsuario;
        this.emailUsuario = emailUsuario;
        this.pesoUsuario = pesoUsuario;
        this.alturaUsuario = alturaUsuario;
        this.generoUsuario = generoUsuario;
        this.tipoDeTreinoUsuario = tipoDeTreinoUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public Double getPesoUsuario() {
        return pesoUsuario;
    }

    public void setPesoUsuario(Double pesoUsuario) {
        this.pesoUsuario = pesoUsuario;
    }

    public Double getAlturaUsuario() {
        return alturaUsuario;
    }

    public void setAlturaUsuario(Double alturaUsuario) {
        this.alturaUsuario = alturaUsuario;
    }

    public int getGeneroUsuario() {
        return generoUsuario;
    }

    public void setGeneroUsuario(int generoUsuario) {
        this.generoUsuario = generoUsuario;
    }

    public int getTipoDeTreinoUsuario() {
        return tipoDeTreinoUsuario;
    }

    public void setTipoDeTreinoUsuario(int tipoDeTreinoUsuario) {
        this.tipoDeTreinoUsuario = tipoDeTreinoUsuario;
    }
}
