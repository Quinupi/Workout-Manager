package com.gym.monster.academymanager.model;

import java.io.Serializable;

/**
 * Created by victor on 12/02/17.
 */

public class Exercicio implements Serializable{
    private int idExercicio, idCategoria;
    private String nome;
    private String descricao;
    private double peso;
    private int qtdRepeticoes;
    private int qtdSeries;
    public static final long  serialVersionUID = 100L;

    public Exercicio(){}

    public Exercicio(int idExercicio, int idCategoria, String nome, String descricao, int qtdSeries, int qtdRepeticoes,  double peso){
        this.idExercicio = idExercicio;
        this.nome = nome;
        this.idCategoria = idCategoria;
        this.descricao = descricao;
        this.peso = peso;
        this.qtdRepeticoes = qtdRepeticoes;
        this.qtdSeries = qtdSeries;
    }

    public int getIdExercicio() {
        return idExercicio;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getQtdRepeticoes() {
        return qtdRepeticoes;
    }

    public void setQtdRepeticoes(int qtdRepeticoes) {
        this.qtdRepeticoes = qtdRepeticoes;
    }

    public int getQtdSeries() {
        return qtdSeries;
    }

    public void setQtdSeries(int qtdSeries) {
        this.qtdSeries = qtdSeries;
    }
}
