package com.example.tallerapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ArticuloAdapter  extends ArrayAdapter<Articulo> {

    public ArticuloAdapter(@NonNull Context context, @NonNull ArrayList<Articulo> Articulos) {
        super(context, 0, Articulos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Articulo art=getItem(position);
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_articulos, parent, false);
        }
        TextView titulo= (TextView)  convertView.findViewById(R.id.txttitulo);
        TextView fecha= (TextView)  convertView.findViewById(R.id.txtfecha);
        TextView secion= (TextView)  convertView.findViewById(R.id.txtsecion);
        TextView url= (TextView)  convertView.findViewById(R.id.txturl);

        titulo.setText("Titulo: "+ art.getTitulo());
        fecha.setText("Fecha publicacion: "+ art.getFecha());
        secion.setText("Seccion: "+ art.getSeccion());
        url.setText("Url: "+ art.getUrl());

        return convertView;
    }
}
