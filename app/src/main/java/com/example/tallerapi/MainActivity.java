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
    String url2="https://api.nytimes.com/svc/mostpopular/v2/shared/30/facebook.json?api-key=Vhipug6dChCXKWNDkYO405uNjLzMgbXw";

    ArrayList<Articulo> cs=new ArrayList<>();
    ArrayList<Articulo> cs2 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conectar=findViewById(R.id.btnconectar);
        lista1=findViewById(R.id.btnlistado1);
        lista2=findViewById(R.id.btnlistado2);
        texto=findViewById(R.id.txtmostrar);
        texart=findViewById(R.id.numarticulos);

        conectar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestDatosViewed();
                requestDatosShared();

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

        lista2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListadoMasCompartidosFB.class);
                i.putParcelableArrayListExtra("articulos",cs2);
                startActivity(i);
            }
        });

    }
    public void requestDatosViewed(){
        RequestQueue cola = Volley.newRequestQueue(this);
        cola.getCache().clear();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                  new Response.Listener<JSONObject>() {
                      @Override
                      public void onResponse(JSONObject response) {
                          //dato.setText(response.toString());
                          parserJson(response, cs);
                          texto.setText("Conectado a las API");
                          conectar.setEnabled(false);

                      }
                  }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        texto.setText("No se pudo conectar a la API 1, revise su conexión");
                    }
                }){
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

    public void requestDatosShared(){
        RequestQueue cola = Volley.newRequestQueue(this);
        cola.getCache().clear();
        JsonObjectRequest jsonobject = new JsonObjectRequest(Request.Method.GET, url2, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parserJson(response, cs2);
                        texto.setText("Conectado a las API");
                        conectar.setEnabled(false);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(),"Error en la conexion 2", Toast.LENGTH_LONG).show();
                        texto.setText("No se pudo conectar a la API 2, revise su conexión");
                    }
                }){
            @Override
            public Map getHeaders() throws AuthFailureError{
                HashMap headers = new HashMap();
                headers.put("api-key", "Vhipug6dChCXKWNDkYO405uNjLzMgbXw");
                return headers;
            }
        };
        cola.add(jsonobject);
        cola.getCache().clear();
    }

   public void parserJson(JSONObject response, ArrayList<Articulo> aux) {
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
                 aux.add(art);
            }
            //texto.setText(cadena);
            //texart.setText("Cantidad de articulos encontrados en la lista: "+ String.valueOf(c));
        }catch (JSONException e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
   }


}