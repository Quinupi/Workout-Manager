package com.gym.monster.academymanager.model;

/**
 * Created by victor on 14/02/17.
 */

public class Treino {
    private String nomeTreino;
    private long idTreino;

    public Treino (){
    }
    public Treino (String nome){
        nomeTreino = nome;
    }

    public Treino (String nome, Long idTreino){
        nomeTreino = nome;
        this.idTreino = idTreino;
    }

    public String getNomeTreino() {
        return nomeTreino;
    }

    public void setNomeTreino(String nomeTreino) {
        this.nomeTreino = nomeTreino;
    }

    public long getIdTreino() {
        return idTreino;
    }

    public void setIdTreino(long idTreino) {
        this.idTreino = idTreino;
    }
}
