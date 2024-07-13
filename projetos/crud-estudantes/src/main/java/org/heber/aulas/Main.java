package org.heber.aulas;

import org.heber.aulas.curso.Curso;
import org.heber.aulas.curso.GerenciarCurso;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final int ADICIONAR_ESTUDANTE = 0x01;
    private static final int LISTAR_ESTUDANTES = 0x02;
    private static final int LISTAR_ESTUDANTES_POR_NOME = 0x03;
    private static final int PESQUISAR_ESTUDANTE = 0x04;
    private static final int REMOVER_ESTUDANTE = 0x05;
    private static final int ADICIONAR_TURMA = 0x06;
    private static final int LISTAR_TURMAS = 0x07;
    private static final int LISTAR_TURMAS_POR_CURSO = 0x08;
    private static final int REMOVER_TURMA = 0x09;
    private static final int ADICIONAR_CURSO = 0x0A;
    private static final int LISTAR_CURSOS = 0x0B;
    private static final int LISTAR_CURSOS_POR_NOME = 0x0C;
    private static final int PESQUISAR_CURSO = 0x0D;
    private static final int REMOVER_CURSO = 0x0E;
    private static final int SAIR = 0x0F;
    private final GerenciarCurso gerenciarCurso;
    private final Scanner scanner;

    public static void main(String[] args) {
        new Main().run();
    }

    public Main() {
        gerenciarCurso = new GerenciarCurso();
        scanner = new Scanner(System.in);
    }

    private void run() {
        while (true) {
            mostrarMenu();
            System.out.print("> ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {

                case ADICIONAR_CURSO -> adicionarCurso();

                case LISTAR_CURSOS -> listarCursos();

                case LISTAR_CURSOS_POR_NOME -> listarCursoPorNome();

                case PESQUISAR_CURSO -> pesquisarCurso();

                case REMOVER_CURSO -> removerCurso();

                case SAIR -> {
                    return;
                }

            }
        }
    }

    private void adicionarCurso() {
        System.out.print("Digite o nome do curso: \n> ");
        String nome = scanner.nextLine();
        System.out.print("Digite a duração do curso em anos: \n> ");
        int duracao = scanner.nextInt();
        scanner.nextLine();

        Curso curso = new Curso(nome, duracao);
        gerenciarCurso.create(curso);
    }

    private void listarCursos() {
        List<Curso> cursos = gerenciarCurso.list();

        if (cursos.isEmpty()) {
            System.out.println("Não há nenhum curso cadastrado");
            return;
        }

        for (Curso curso: cursos) {
            System.out.printf("Código: %d%n", curso.getCodigo());
            System.out.printf("Curso: %s%n", curso.getNome());
            System.out.printf("Duração: %d%n", curso.getDuracaoCurso());
            System.out.println();
        }
    }

    private void listarCursoPorNome() {
        System.out.print("Digite o nome do curso a ser pesquisado: \n> ");
        String nome = scanner.nextLine();

        List<Curso> cursos = gerenciarCurso.listByName(nome);

        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso foi encontrado");
            return;
        }

        for (Curso curso: cursos) {
            System.out.printf("Código: %d%n", curso.getCodigo());
            System.out.printf("Curso: %s%n", curso.getNome());
            System.out.printf("Duração: %d%n", curso.getDuracaoCurso());
            System.out.println();
        }
    }

    private void pesquisarCurso() {
        System.out.print("Digite o código do curso: \n> ");
        int codigoCurso = scanner.nextInt();
        scanner.nextLine();

        Curso curso = gerenciarCurso.findById(codigoCurso);

        if (curso == null) {
            System.out.println("O curso solicitado não foi encontrado");
            return;
        }

        System.out.printf("Código: %d%n", curso.getCodigo());
        System.out.printf("Curso: %s%n", curso.getNome());
        System.out.printf("Duração: %d%n", curso.getDuracaoCurso());
        System.out.println();
    }

    private void removerCurso() {
        System.out.print("Digite o código do curso a ser removido: \n> ");
        int codigoCurso = scanner.nextInt();
        scanner.nextLine();
        gerenciarCurso.remove(codigoCurso);
        System.out.println("Curso removido com sucesso");
    }

    private void mostrarMenu() {
        System.out.printf("[ %2d ] - Adicionar estudante%n", ADICIONAR_ESTUDANTE);
        System.out.printf("[ %2d ] - Listar estudantes%n", LISTAR_ESTUDANTES);
        System.out.printf("[ %2d ] - Listar estudantes por nome%n", LISTAR_ESTUDANTES_POR_NOME);
        System.out.printf("[ %2d ] - Pesquisar estudante%n", PESQUISAR_ESTUDANTE);
        System.out.printf("[ %2d ] - Remover estudante%n", REMOVER_ESTUDANTE);
        System.out.printf("[ %2d ] - Adicionar turma%n", ADICIONAR_TURMA);
        System.out.printf("[ %2d ] - Listar turmas%n", LISTAR_TURMAS);
        System.out.printf("[ %2d ] - Listar turmas por curso%n", LISTAR_TURMAS_POR_CURSO);
        System.out.printf("[ %2d ] - Remover turma%n", REMOVER_TURMA);
        System.out.printf("[ %2d ] - Adicionar curso%n", ADICIONAR_CURSO);
        System.out.printf("[ %2d ] - Listar cursos%n", LISTAR_CURSOS);
        System.out.printf("[ %2d ] - Listar cursos por nome%n", LISTAR_CURSOS_POR_NOME);
        System.out.printf("[ %2d ] - Pesquisar curso%n", PESQUISAR_CURSO);
        System.out.printf("[ %2d ] - Remover curso%n", REMOVER_CURSO);
        System.out.printf("[ %2d ] - Sair do programa%n", SAIR);
    }
}