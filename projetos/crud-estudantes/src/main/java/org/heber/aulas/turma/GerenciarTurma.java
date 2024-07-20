package org.heber.aulas.turma;

import org.heber.aulas.Main;
import org.heber.aulas.base.Gerenciador;
import org.heber.aulas.curso.Curso;
import org.heber.aulas.estudante.Estudante;

import java.util.ArrayList;
import java.util.List;

public class GerenciarTurma extends Gerenciador<Turma> {

    private static final String HEADERS = "NOME;CODIGO;CODIGO_CURSO\n";

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

    @Override
    public String pegarInformacoes() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(HEADERS);

        for (Turma turma: list().reversed()) {
            stringBuilder.append("%s;".formatted(turma.getNome()));
            stringBuilder.append("%d;".formatted(turma.getCodigo()));
            stringBuilder.append("%d%n".formatted(turma.getCurso().getCodigo()));
        }

        return stringBuilder.toString();
    }

    @Override
    public void lerInformacoes(String linhaCsv) {
        String[] informacoes = linhaCsv.split(";");
        String nome = informacoes[0];
        int codigo = Integer.parseInt(informacoes[1]);
        int codigoCurso = Integer.parseInt(informacoes[2]);
        Curso curso = Main.main.gerenciarCurso.findById(codigoCurso);
        Turma turma = new Turma(nome, curso);
        turma.setCodigo(codigo);
        bancoDeDados.put(codigo, turma);
    }

}
