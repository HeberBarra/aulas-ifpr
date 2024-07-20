package org.heber.aulas.curso;

import org.heber.aulas.base.Gerenciador;

public class GerenciarCurso extends Gerenciador<Curso> {

    private static final String HEADERS = "NOME;CODIGO;DURACAO_CURSO\n";

    @Override
    public String pegarInformacoes() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(HEADERS);

        for (Curso curso: list().reversed()) {
            stringBuilder.append("%s;".formatted(curso.getNome()));
            stringBuilder.append("%d;".formatted(curso.getCodigo()));
            stringBuilder.append("%d%n".formatted(curso.getDuracaoCurso()));
        }

        return stringBuilder.toString();
    }

    @Override
    public void lerInformacoes(String linhaCsv) {
        String[] informacoes = linhaCsv.split(";");
        String nome = informacoes[0];
        int codigo = Integer.parseInt(informacoes[1]);
        int duracaoCurso = Integer.parseInt(informacoes[2]);

        Curso curso = new Curso(nome, duracaoCurso);
        curso.setCodigo(codigo);

        bancoDeDados.put(codigo, curso);
    }
}
