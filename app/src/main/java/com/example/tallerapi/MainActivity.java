package com.example.tallerapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
         Button conectar, lista1,lista2;
         TextView texto,texart;
         String url="https://api.nytimes.com/svc/mostpopular/v2/viewed/30.json?api-key=3Uqjw0PBzSTH9lBOSoQB4XoS3cXp00qj";

    ArrayList<Articulo> cs=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conectar=findViewById(R.id.btnconectar);
        lista1=findViewById(R.id.btnlistado1);
        lista2=findViewById(R.id.btnlistado2);
        texto=findViewById(R.id.txtmostrar);
        texart=findViewById(R.id.txt);

        conectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDatos();
            }
        });
        lista1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),ListadoArticulos.class);
                i.putParcelableArrayListExtra("articulos",cs);
                startActivity(i);
            }
        });

    }
  public void requestDatos(){
      RequestQueue cola = Volley.newRequestQueue(this);
      JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
              new Response.Listener<JSONObject>() {
                  @Override
                  public void onResponse(JSONObject response) {
                      //dato.setText(response.toString());
                      parserJson(response);

                  }
              }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
              Toast.makeText(getApplicationContext(),"Error en la conexion", Toast.LENGTH_LONG).show();
          }
      })
      {
         @Override
         public Map getHeaders() throws AuthFailureError {
              HashMap headers = new HashMap();
              //headers.put("Content-Type", "application/json");
              headers.put("api-key", "3Uqjw0PBzSTH9lBOSoQB4XoS3cXp00qj");
              return headers;
          }
      };
      cola.add(jsonObjectRequest);
  }
   public void parserJson(JSONObject response) {
        try {
            String cadena=" ";
            JSONArray result = response.getJSONArray("results");
            int c=0;
            for (int i=0;i<result.length();i++){
                JSONObject resul = result.getJSONObject(i);
                String urla = resul.getString("url");
                String fecha=resul.getString("published_date");
                String seccion=resul.getString("section");
                String titulo=resul.getString("title");
                cadena=cadena + urla + "," + fecha + "," + seccion +"," + titulo + "\n";
                c=c+1;
                 Articulo art= new Articulo(urla,fecha,seccion,titulo);
                 cs.add(art);
            }
            texto.setText(cadena);
            texart.setText(String.valueOf(c));
        }catch (JSONException e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
   }


}