package com.example.tallerapi;

public class Articulo {
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
}
