package com.example.quemacademy.models;

public class Disciplina {

    String titulo;
    int horas;
    Area area;

    public Disciplina(String titulo, int horas, Area area) {
        this.titulo = titulo;
        this.horas = horas;
        this.area = area;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
