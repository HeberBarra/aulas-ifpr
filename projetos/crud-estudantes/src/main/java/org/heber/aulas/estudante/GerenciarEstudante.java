package org.heber.aulas.estudante;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.heber.aulas.Main;
import org.heber.aulas.base.Gerenciador;
import org.heber.aulas.turma.Turma;

public class GerenciarEstudante extends Gerenciador<Estudante> {

    private static final String HEADERS = "NOME;CODIGO;DATA_NASCIMENTO;CODIGO_TURMA\n";
    private final DateTimeFormatter dateTimeFormatter;

    public GerenciarEstudante() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    }

    @Override
    public String pegarInformacoes() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(HEADERS);

        for (Estudante estudante: list().reversed()) {
            stringBuilder.append("%s;".formatted(estudante.getNome()));
            stringBuilder.append("%d;".formatted(estudante.getCodigo()));
            stringBuilder.append("%s;".formatted(formatarData(estudante.getDataNascimento())));
            stringBuilder.append("%d%n".formatted(estudante.getTurma().getCodigo()));
        }

        return stringBuilder.toString();
    }

    @Override
    public void lerInformacoes(String linhaCsv) {
        String[] informacoes = linhaCsv.split(";");
        String nome = informacoes[0];
        int codigo = Integer.parseInt(informacoes[1]);
        LocalDate dataNascimento = formatarData(informacoes[2]);
        int codigoTurma = Integer.parseInt(informacoes[3]);

        Turma turma = Main.main.gerenciarTurma.findById(codigoTurma);

        Estudante estudante = new Estudante(nome, dataNascimento, turma);
        estudante.setCodigo(codigo);
        bancoDeDados.put(codigo, estudante);
    }

    private String formatarData(LocalDate dataNascimento) {
        return dateTimeFormatter.format(dataNascimento);
    }

    private LocalDate formatarData(String dataNascimento) {
        return LocalDate.parse(dataNascimento, dateTimeFormatter);
    }

}
