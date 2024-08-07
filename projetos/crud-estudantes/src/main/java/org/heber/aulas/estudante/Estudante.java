package org.heber.aulas.estudante;

import org.heber.aulas.base.Modelo;
import org.heber.aulas.turma.Turma;
import java.time.LocalDate;

public class Estudante extends Modelo {

    private LocalDate dataNascimento;
    private Turma turma;

    public Estudante(String nome, LocalDate dataNascimento, Turma turma) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.turma = turma;
    }

    public int calcularIdade() {
        LocalDate dataAtual = LocalDate.now();
        int anoNascimento = dataNascimento.getYear();
        int anoAtual = dataAtual.getYear();

        if (dataAtual.isAfter(LocalDate.of(anoAtual, dataNascimento.getMonthValue(), dataNascimento.getDayOfMonth())))
            return anoAtual - anoNascimento;

        return anoAtual - anoNascimento - 1;
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
