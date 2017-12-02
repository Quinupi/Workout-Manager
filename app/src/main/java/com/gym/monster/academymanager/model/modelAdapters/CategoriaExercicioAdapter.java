package com.gym.monster.academymanager.model.modelAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gym.monster.academymanager.R;
import com.gym.monster.academymanager.model.CategoriaExercicio;

import java.util.List;

/**
 * Created by victor on 22/02/17.
 */

public class CategoriaExercicioAdapter extends ArrayAdapter<CategoriaExercicio> {

    public CategoriaExercicioAdapter(Context context, List<CategoriaExercicio> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Pega o item associado a posição
        CategoriaExercicio ct = getItem(position);

        //Checa se alguma view está sendo reusada, caso não, infla uma nova view com o list_row_treino
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_categoria_exercicio, parent, false);
        }

        //Recupera as referencias do xml
        TextView txtCatExercicio = (TextView) convertView.findViewById(R.id.txtNomeCatExercicio);
        TextView txtIdCatExercicio = (TextView) convertView.findViewById(R.id.txtIdCatExercicio);

        //Preenche o conteúdo
        txtCatExercicio.setText(ct.getNomeCategoria());
        txtIdCatExercicio.setText(String.valueOf(ct.getIdCategoria()));


        return convertView;
    }
}
