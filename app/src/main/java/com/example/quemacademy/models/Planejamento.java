package com.example.quemacademy.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Planejamento {

    public String ano;
    public String semestre;
    public int horas;
    public Map<Area, Integer> porcentagens;
    public List<Disciplina> disciplinas;

    public Planejamento(String ano, String semestre, int horas) {
        this.ano = ano;
        this.semestre = semestre;
        this.horas = horas;
        this.porcentagens = new HashMap<>();
        this.disciplinas = new ArrayList<>();
    }

    public String getAno() {
        return ano;
    }

    public String getSemestre() {
        return semestre;
    }

    public int getHoras() {
        return horas;
    }

    public Map<Area, Integer> getPorcentagens() {
        return porcentagens;
    }

    public void setPorcentagens(Map<Area, Integer> porcentagens) {
        this.porcentagens = porcentagens;
    }

    public String getAnoSemestre(){
        return getAno() + "/" + getSemestre();
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public int getHorasComputadas(){
        int result = 0;
        for(Disciplina d : disciplinas){
            result += d.horas;
        }
        return result;
    }

    public int getHorasComputadas(Area a){
        int result = 0;
        for(Disciplina d : disciplinas){
            if (d.area == a){
                result += d.horas;
            }
        }
        return result;
    }

    public float getPercent(){
        return Math.round(getHorasComputadas() * 100) / horas;
    }

    public float getPercent(Area a){
        return Math.round(getHorasComputadas(a) * 100) / horas;
    }
}
