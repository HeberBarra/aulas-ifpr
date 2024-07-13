package org.heber.aulas;

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
    private static final int PESQUISAR_CURSO = 0x0C;
    private static final int REMOVER_CURSO = 0x0D;
    private static final int SAIR = 0x0E;
    private Scanner scanner;

    public static void main(String[] args) {
        new Main().run();
    }

    public Main() {
        scanner = new Scanner(System.in);
    }

    private void run() {
        while (true) {
            mostrarMenu();
            System.out.print("> ");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {

                case SAIR -> {
                    return;
                }

            }
        }
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
        System.out.printf("[ %2d ] - Pesquisar curso%n", PESQUISAR_CURSO);
        System.out.printf("[ %2d ] - Remover curso%n", REMOVER_CURSO);
        System.out.printf("[ %2d ] - Sair do programa%n", SAIR);
    }
}