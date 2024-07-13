package org.heber.aulas.estudante;

import org.heber.aulas.turma.Turma;

import java.time.LocalDate;

public class Estudante {

    private int matricula;
    private String nome;
    private LocalDate dataNascimento;
    private Turma turma;

    public Estudante(int matricula, String nome, LocalDate dataNascimento, Turma turma) {
        this.matricula = matricula;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.turma = turma;
    }

    public int calcularIdade() {
        LocalDate dataAtual = LocalDate.now();
        int anoNascimento = dataNascimento.getYear();
        int anoAtual = dataAtual.getYear();

        if ( dataNascimento.getMonthValue() >= dataAtual.getMonthValue() )
            return anoAtual - anoNascimento;

        return anoAtual - anoNascimento - 1;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
}
