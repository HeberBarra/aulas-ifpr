package org.heber.aulas.curso;

import org.heber.aulas.base.Modelo;

public class Curso extends Modelo {

    private int duracaoCurso;

    public Curso(String nomeCurso, int duracaoCurso) {
        this.nome = nomeCurso;
        this.duracaoCurso = duracaoCurso;
    }

    public int getDuracaoCurso() {
        return duracaoCurso;
    }

    public void setDuracaoCurso(int duracaoCurso) {
        this.duracaoCurso = duracaoCurso;
    }

}
