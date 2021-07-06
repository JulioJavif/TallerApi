package com.example.tallerapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
         Button conectar, lista1,lista2;
         String url="https://api.nytimes.com/svc/mostpopular/v2/viewed/30.json?api-key=3Uqjw0PBzSTH9lBOSoQB4XoS3cXp00qj";

    ArrayList<Articulo> cs=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conectar=findViewById(R.id.btnconectar);
        lista1=findViewById(R.id.btnlistado1);
        lista2=findViewById(R.id.btnlistado2);

        conectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
  public void requestDatos(){

  }


}