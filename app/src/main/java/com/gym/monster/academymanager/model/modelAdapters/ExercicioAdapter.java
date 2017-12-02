package com.gym.monster.academymanager.model.modelAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gym.monster.academymanager.R;
import com.gym.monster.academymanager.model.Exercicio;

import java.util.ArrayList;

/**
 * Created by victor on 25/02/17.
 */

public class ExercicioAdapter extends ArrayAdapter<Exercicio>{
    public ExercicioAdapter(Context context, ArrayList<Exercicio> exercicios) {
        super(context, 0, exercicios);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Pega o item associado a posição
        Exercicio e = getItem(position);

        //Checa se alguma view está sendo reusada, caso não, infla uma nova view com o list_row_treino
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_exercicio, parent, false);
        }

        //Recupera as referencias do xml
        TextView txtNome = (TextView) convertView.findViewById(R.id.txtNome);
        TextView txtId = (TextView) convertView.findViewById(R.id.txtId);
        TextView txtIdCategora = (TextView) convertView.findViewById(R.id.txtCategoria);

        //Preenche o conteúdo
        txtNome.setText(e.getNome());
        txtId.setText(String.valueOf(e.getIdExercicio()));
        txtIdCategora.setText(String.valueOf(e.getIdCategoria()));

        return convertView;
    }
}
