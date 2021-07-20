package com.example.tallerapi;

import android.os.Parcel;
import android.os.Parcelable;

public class Articulo implements Parcelable {
    private String url;
    private String fecha;
    private String seccion;
    private String titulo;

    public Articulo(String url, String fecha, String seccion, String titulo) {
        this.url = url;
        this.fecha = fecha;
        this.seccion = seccion;
        this.titulo = titulo;
    }
    protected Articulo(Parcel in) {
        url= in.readString();
        fecha = in.readString();
        seccion = in.readString();
        titulo = in.readString();
    }


    public String getUrl() {
        return url;
    }

    public String getFecha() {
        return fecha;
    }

    public String getSeccion() {
        return seccion;
    }

    public String getTitulo() {
        return titulo;
    }
    public static final Parcelable.Creator<Articulo> CREATOR = new Parcelable.Creator<Articulo>() {
        @Override
        public Articulo createFromParcel(Parcel in) {
            return new Articulo(in);
        }

        @Override
        public Articulo[] newArray(int size) {
            return new Articulo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(fecha);
        dest.writeString(seccion);
        dest.writeString(titulo);
    }

}
