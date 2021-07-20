package com.example.tallerapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoMasCompartidosFB extends AppCompatActivity {
    ArrayList<Articulo> lista = new ArrayList<>();
    ListView listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_mas_compartidos_fb);

        Intent i = getIntent();
        lista = i.getParcelableArrayListExtra("articulos");
        listado = findViewById(R.id.lvFB);
        if (lista!=null && lista.size()>0){
            ArticuloAdapter adapter = new ArticuloAdapter(this, lista);
            listado.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else {
            Toast.makeText(this,"No hay datos" , Toast.LENGTH_LONG).show();
        }
    }
}