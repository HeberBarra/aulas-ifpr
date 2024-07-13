package org.heber.aulas.curso;

public class Curso {

    private int codigoCurso;
    private String nomeCurso;
    private int duracaoCurso;

    public Curso(int codigoCurso, String nomeCurso, int duracaoCurso) {
        this.codigoCurso = codigoCurso;
        this.nomeCurso = nomeCurso;
        this.duracaoCurso = duracaoCurso;
    }

    public int getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(int codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public int getDuracaoCurso() {
        return duracaoCurso;
    }

    public void setDuracaoCurso(int duracaoCurso) {
        this.duracaoCurso = duracaoCurso;
    }
}
