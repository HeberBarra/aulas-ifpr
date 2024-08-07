package org.heber.aulas.arquivo;

import org.heber.aulas.base.Gerenciador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Arquivo {

    private static final Logger logger = Logger.getLogger(Arquivo.class.getName());
    private static final String pastaInformacoes = "data/";
    private final List<Gerenciador<?>> gerenciadores;

    public Arquivo() {
        gerenciadores = new ArrayList<>();
    }

    public void adicionarGerenciador(Gerenciador<?> gerenciador) {
        gerenciadores.add(gerenciador);
    }

    public void salvarInformacoes() {

        for (Gerenciador<?> gerenciador : gerenciadores) {
            String informacoes = gerenciador.pegarInformacoes();
            String nomeArquivo = obterNomeArquivo(gerenciador);

            criarArquivo(nomeArquivo);
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(nomeArquivo))) {
                bufferedWriter.write(informacoes);
            } catch (IOException e) {
                logger.severe("Um erro ocorreu ao tentar escrever no arquivo %s.%n%s%n".formatted(nomeArquivo, e.getMessage()));
            }

        }

    }

    public void lerInformacoes() {
        for (Gerenciador<?> gerenciador: gerenciadores) {
            String nomeArquivo = obterNomeArquivo(gerenciador);

            if (!new File(nomeArquivo).exists())
                continue;

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(nomeArquivo))) {
                bufferedReader.readLine();

                String linha = bufferedReader.readLine();
                while (linha != null) {
                    gerenciador.lerInformacoes(linha);
                    linha = bufferedReader.readLine();
                }

                gerenciador.atualizarIndiceGerador();

            } catch (IOException e) {
                logger.severe("Um erro ocorreu ao tentar ler o arquivo %s.%n%s%n".formatted(nomeArquivo, e.getMessage()));
            }

        }
    }

    private String obterNomeArquivo(Gerenciador<?> gerenciador) {
        String nomeClasse = gerenciador.getClass().getSimpleName().substring("Gerenciar".length());
        return "%s%s.csv".formatted(pastaInformacoes, nomeClasse);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void criarArquivo(String nomeArquivo) {
        File arquivo = new File(nomeArquivo);

        try {
            arquivo.createNewFile();
        } catch (IOException e) {
            logger.severe("Houve um problema ao criar o arquivo %s.%n%s%n".formatted(nomeArquivo, e.getMessage()));
        }

    }

}
