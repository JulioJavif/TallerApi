package com.example.tallerapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoArticulos extends AppCompatActivity {
    ArrayList<Articulo> art= new ArrayList<>();
    ListView listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_articulos);
        Intent i= getIntent();
        art= i.getParcelableArrayListExtra("articulo");
        listado=findViewById(R.id.listarticulos);
        if (art!=null && art.size()>0){
            ArticuloAdapter adapter = new ArticuloAdapter(this, art);
            listado.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        else{
            Toast.makeText(this,"No hay datos" , Toast.LENGTH_LONG).show();
        }

    }
}