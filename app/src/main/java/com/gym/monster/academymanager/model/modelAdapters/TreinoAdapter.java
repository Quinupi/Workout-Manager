package com.gym.monster.academymanager.model.modelAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gym.monster.academymanager.R;
import com.gym.monster.academymanager.model.Treino;

import java.util.ArrayList;

/**
 * Created by victor on 16/02/17.
 */

public class TreinoAdapter extends ArrayAdapter<Treino> {

    public TreinoAdapter(Context context, ArrayList<Treino> treinos) {
        super(context, 0, treinos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //Pega o item associado a posição
        Treino t = getItem(position);

        //Checa se alguma view está sendo reusada, caso não, infla uma nova view com o list_row_treino
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_treino, parent, false);
        }

        //Recupera as referencias do xml
        TextView txtNome = (TextView) convertView.findViewById(R.id.txtNome);
        TextView txtId = (TextView) convertView.findViewById(R.id.txtId);

        //Preenche o conteúdo
        txtNome.setText(t.getNomeTreino());
        txtId.setText(String.valueOf(t.getIdTreino()));

        return convertView;
    }
}
