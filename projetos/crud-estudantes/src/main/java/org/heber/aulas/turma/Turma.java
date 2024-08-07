package org.heber.aulas.turma;

import org.heber.aulas.base.Modelo;
import org.heber.aulas.curso.Curso;

public class Turma extends Modelo {

    private Curso curso;

    public Turma(String nomeTurma, Curso curso) {
        this.nome = nomeTurma;
        this.curso = curso;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

}
