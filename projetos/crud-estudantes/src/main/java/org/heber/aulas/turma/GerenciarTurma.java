package org.heber.aulas.turma;

import org.heber.aulas.base.Gerenciador;
import org.heber.aulas.curso.Curso;
import org.heber.aulas.estudante.Estudante;

import java.util.ArrayList;
import java.util.List;

public class GerenciarTurma extends Gerenciador<Turma> {

    public void adicionarEstudante(Turma turma, Estudante estudante) {
        turma.getEstudantes().add(estudante);
    }

    public void removerEstudante(Turma turma, Estudante estudante) {
        turma.getEstudantes().remove(estudante);
    }

    public List<Turma> listarTurmasPorCurso(Curso curso) {
        List<Turma> turmas = new ArrayList<>();

        for (Turma turma: list()) {
            if (turma.getCurso() == curso) {
                turmas.add(turma);
            }
        }

        return turmas;
    }

    public void listarEstudantes(Turma turma) {

        for (Estudante estudante: turma.getEstudantes()) {
            System.out.printf("Matrícula: %d%n", estudante.getCodigo());
            System.out.printf("Nome: %s%n", estudante.getNome());
            System.out.println();
        }

    }

}
