package org.heber.aulas.turma;

import org.heber.aulas.curso.Curso;
import org.heber.aulas.estudante.Estudante;

import java.util.List;

public class Turma {

    private int codigoTurma;
    private int capacidadeMaxima;
    private int serieTurma;
    private String nomeTurma;
    private Curso curso;
    private List<Estudante> estudantes;

    public int getCodigoTurma() {
        return codigoTurma;
    }

    public void setCodigoTurma(int codigoTurma) {
        this.codigoTurma = codigoTurma;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public int getSerieTurma() {
        return serieTurma;
    }

    public void setSerieTurma(int serieTurma) {
        this.serieTurma = serieTurma;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
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
