package org.heber.aulas.turma;

import org.heber.aulas.base.Modelo;
import org.heber.aulas.curso.Curso;
import org.heber.aulas.estudante.Estudante;

import java.util.ArrayList;
import java.util.List;

public class Turma extends Modelo {

    private Curso curso;
    private List<Estudante> estudantes;

    public Turma(String nomeTurma, Curso curso) {
        this.nome = nomeTurma;
        this.curso = curso;
        this.estudantes = new ArrayList<>();
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Estudante> getEstudantes() {
        return estudantes;
    }

    public void setEstudantes(List<Estudante> estudantes) {
        this.estudantes = estudantes;
    }

}
