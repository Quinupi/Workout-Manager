package com.gym.monster.academymanager.model;

/**
 * Created by victor on 21/02/17.
 */

public class CategoriaExercicio {
    private String nomeCategoria;
    private int idCategoria;

    public CategoriaExercicio(int idCategoria, String nomeCategoria){
        this.nomeCategoria = nomeCategoria;
        this.idCategoria = idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public int getIdCategoria() {
        return idCategoria;
    }
}
