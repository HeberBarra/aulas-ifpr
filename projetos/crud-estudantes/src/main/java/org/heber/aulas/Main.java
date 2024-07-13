package org.heber.aulas;

import org.heber.aulas.curso.Curso;
import org.heber.aulas.curso.GerenciarCurso;
import org.heber.aulas.estudante.Estudante;
import org.heber.aulas.estudante.GerenciarEstudante;
import org.heber.aulas.turma.GerenciarTurma;
import org.heber.aulas.turma.Turma;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final int ADICIONAR_ESTUDANTE = 0x00;
    private static final int LISTAR_ESTUDANTES = 0x01;
    private static final int LISTAR_ESTUDANTES_POR_NOME = 0x02;
    private static final int LISTAR_ESTUDANTES_TURMA = 0x03;
    private static final int PESQUISAR_ESTUDANTE = 0x04;
    private static final int ATUALIZAR_ESTUDANTE = 0x05;
    private static final int REMOVER_ESTUDANTE = 0x06;
    private static final int ADICIONAR_TURMA = 0x07;
    private static final int LISTAR_TURMAS = 0x08;
    private static final int LISTAR_TURMAS_CURSO = 0x09;
    private static final int ATUALIZAR_TURMA = 0x0A;
    private static final int REMOVER_TURMA = 0x0B;
    private static final int ADICIONAR_CURSO = 0x0C;
    private static final int LISTAR_CURSOS = 0x0D;
    private static final int LISTAR_CURSOS_POR_NOME = 0x0E;
    private static final int PESQUISAR_CURSO = 0x0F;
    private static final int ATUALIZAR_CURSO = 0x10;
    private static final int REMOVER_CURSO = 0x11;
    private static final int SAIR = 0x12;
    private static final String REGEX_DATA = "^(3[01]|[12][0-9]|0[1-9])(/)(1[0-2]|0[1-9])\\2([0-9]{4})$";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final GerenciarCurso gerenciarCurso;
    private final GerenciarEstudante gerenciarEstudante;
    private final GerenciarTurma gerenciarTurma;
    private final Scanner scanner;

    public static void main(String[] args) {
        new Main().run();
    }

    public Main() {
        gerenciarCurso = new GerenciarCurso();
        gerenciarEstudante = new GerenciarEstudante();
        gerenciarTurma = new GerenciarTurma();
        scanner = new Scanner(System.in);
    }

    private void run() {
        while (true) {
            mostrarMenu();
            System.out.print("> ");
            int escolha = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (escolha) {

                case ADICIONAR_ESTUDANTE -> adicionarEstudante();

                case LISTAR_ESTUDANTES -> listarEstudantes();

                case LISTAR_ESTUDANTES_POR_NOME -> listarEstudantesPorNome();

                case LISTAR_ESTUDANTES_TURMA -> listarEstudantesTurma();

                case PESQUISAR_ESTUDANTE -> pesquisarEstudante();

                case ATUALIZAR_ESTUDANTE -> atualizarEstudante();

                case REMOVER_ESTUDANTE -> removerEstudante();

                case ADICIONAR_TURMA -> adicionarTurma();

                case LISTAR_TURMAS -> listarTurmas();

                case LISTAR_TURMAS_CURSO -> listarTurmasCurso();

                case ATUALIZAR_TURMA -> atualizarTurma();

                case REMOVER_TURMA -> removerTurma();

                case ADICIONAR_CURSO -> adicionarCurso();

                case LISTAR_CURSOS -> listarCursos();

                case LISTAR_CURSOS_POR_NOME -> listarCursoPorNome();

                case PESQUISAR_CURSO -> pesquisarCurso();

                case ATUALIZAR_CURSO -> atualizarCurso();

                case REMOVER_CURSO -> removerCurso();

                case SAIR -> { return; }

                default -> System.out.println("Opção inválida");

            }
        }
    }

    private void adicionarEstudante() {
        if (gerenciarTurma.list().isEmpty()) {
            System.out.println("Não há nenhuma turma cadastrada. Não é possível cadastrar um estudante");
            return;
        }

        System.out.print("Digite o nome do estudante: \n> ");
        String nome = scanner.nextLine();

        System.out.print("Digite a data de nascimento do estudante(formato dd/mm/aaaa): \n> ");
        String stringDataNascimento = scanner.nextLine();

        if (!stringDataNascimento.matches(REGEX_DATA)) {
            System.out.println("Data inválida");
            System.out.println("Operação cancelada");
            return;
        }

        System.out.println();
        listarTurmas();
        System.out.print("Digite o código da turma do estudante: \n> ");
        int codigoTurma = scanner.nextInt();
        scanner.nextLine();

        LocalDate dataNascimento = LocalDate.parse(stringDataNascimento, dateTimeFormatter);
        Turma turma = gerenciarTurma.findById(codigoTurma);
        Estudante estudante = new Estudante(nome, dataNascimento, turma);
        gerenciarEstudante.create(estudante);
        gerenciarTurma.adicionarEstudante(turma, estudante);
    }

    private void listarEstudantes() {
        List<Estudante> estudantes = gerenciarEstudante.list();

        if (estudantes.isEmpty()) {
            System.out.println("Não há estudantes cadastrados");
            return;
        }

        for (Estudante estudante: estudantes) {
            System.out.printf("Matrícula: %d%n", estudante.getCodigo());
            System.out.printf("Nome: %s%n", estudante.getNome());
            System.out.printf("Data de nascimento: %s%n", estudante.getDataNascimento().format(dateTimeFormatter));
            System.out.printf("Idade: %d%n", estudante.calcularIdade());
            System.out.printf("Turma: %s%n", estudante.getTurma().getNome());
            System.out.println();
        }

    }

    private void listarEstudantesPorNome() {
        System.out.print("Digite o nome a ser pesquisado: \n> ");
        String nome = scanner.nextLine();

        List<Estudante> estudantes = gerenciarEstudante.listByName(nome);

        if (estudantes.isEmpty()) {
            System.out.println("Nenhum estudante foi encontrado");
            return;
        }

        System.out.println();
        for (Estudante estudante: estudantes) {
            System.out.printf("Matrícula: %d%n", estudante.getCodigo());
            System.out.printf("Nome: %s%n", estudante.getNome());
            System.out.printf("Data de nascimento: %s%n", estudante.getDataNascimento().format(dateTimeFormatter));
            System.out.printf("Idade: %d%n", estudante.calcularIdade());
            System.out.printf("Turma: %s%n", estudante.getTurma().getNome());
            System.out.println();
        }

    }

    private void listarEstudantesTurma() {
        listarTurmas();
        System.out.print("Digite o código da turma: \n> ");
        int codigoTurma = scanner.nextInt();
        scanner.nextLine();

        Turma turma = gerenciarTurma.findById(codigoTurma);

        if (turma == null) {
            System.out.println("Turma solicitada não foi encontrada");
            return;
        }

        System.out.println();
        gerenciarTurma.listarEstudantes(turma);

    }

    private void pesquisarEstudante() {
        System.out.print("Digite o código do estudante: \n> ");
        int codigoEstudante = scanner.nextInt();
        scanner.nextLine();

        Estudante estudante = gerenciarEstudante.findById(codigoEstudante);

        if (estudante == null) {
            System.out.println("Estudante não encontrado");
            return;
        }

        System.out.printf("Matrícula: %d%n", estudante.getCodigo());
        System.out.printf("Nome: %s%n", estudante.getNome());
        System.out.printf("Data de nascimento: %s%n", estudante.getDataNascimento().format(dateTimeFormatter));
        System.out.printf("Idade: %d%n", estudante.calcularIdade());
        System.out.printf("Turma: %s%n", estudante.getTurma().getNome());
        System.out.println();
    }

    private void atualizarEstudante() {
        System.out.print("Digite o código do estudante a ser modificado: \n> ");
        int codigoEstudante = scanner.nextInt();
        scanner.nextLine();

        Estudante estudante = gerenciarEstudante.findById(codigoEstudante);

        if (estudante == null) {
            System.out.println("Estudante não encontrado");
            return;
        }

        System.out.print("Digite o nome do estudante: \n> ");
        String nome = scanner.nextLine();

        System.out.print("Digite a data de nascimento do estudante(formato dd/mm/aaaa): \n> ");
        String stringDataNascimento = scanner.nextLine();

        if (!stringDataNascimento.matches(REGEX_DATA)) {
            System.out.println("Data inválida");
            System.out.println("Operação cancelada");
            return;
        }

        System.out.println();
        listarTurmas();
        System.out.print("Digite o código da turma do estudante: \n> ");
        int codigoTurma = scanner.nextInt();
        scanner.nextLine();

        LocalDate dataNascimento = LocalDate.parse(stringDataNascimento, dateTimeFormatter);
        Turma turma = gerenciarTurma.findById(codigoTurma);

        estudante.setNome(nome);
        estudante.setDataNascimento(dataNascimento);
        estudante.setTurma(turma);
        gerenciarEstudante.update(estudante);
        System.out.println("Estudante atualizado com sucesso");
    }

    private void removerEstudante() {
        System.out.print("Digite o código do estudante a ser removido: \n> ");
        int codigoEstudante = scanner.nextInt();
        scanner.nextLine();

        Estudante estudante = gerenciarEstudante.findById(codigoEstudante);

        if (estudante == null) {
            System.out.println("Estudante não encontrado");
            return;
        }

        gerenciarEstudante.remove(codigoEstudante);
        gerenciarTurma.removerEstudante(estudante.getTurma(), estudante);
        System.out.println("Estudante removido com sucesso");
    }


    private void adicionarTurma() {

        if (gerenciarCurso.list().isEmpty()) {
            System.out.println("É necessário adicionar um curso antes de criar uma turma. Operação cancelada");
            return;
        }

        listarCursos();
        System.out.print("Digite o código do curso desejado: \n> ");
        int codigoCurso = scanner.nextInt();
        scanner.nextLine();
        Curso curso = gerenciarCurso.findById(codigoCurso);

        System.out.print("Digite o nome da turma: \n> ");
        String nome = scanner.nextLine();

        Turma turma = new Turma(nome, curso);
        gerenciarTurma.create(turma);
    }

    private void listarTurmas() {
        List<Turma> turmas = gerenciarTurma.list();

        if (turmas.isEmpty()) {
            System.out.println("Não há nenhuma turma cadastrada");
            return;
        }

        for (Turma turma: turmas) {
            System.out.printf("Código: %d%n", turma.getCodigo());
            System.out.printf("Nome: %s%n", turma.getNome());
            System.out.printf("Curso: %s%n", turma.getCurso().getNome());
            System.out.printf("Número estudantes: %d%n", turma.getEstudantes().size());
            System.out.println();
        }
    }

    private void listarTurmasCurso() {
        System.out.print("Digite o codigo do curso: \n> ");
        int codigoCurso = scanner.nextInt();
        scanner.nextLine();

        Curso curso = gerenciarCurso.findById(codigoCurso);
        List<Turma> turmas = gerenciarTurma.listarTurmasPorCurso(curso);

        if (turmas.isEmpty()) {
            System.out.println("Não há nenhuma turma para o curso solicitado");
            return;
        }

        System.out.println();
        for (Turma turma: turmas) {
            System.out.printf("Código: %d%n", turma.getCodigo());
            System.out.printf("Nome: %s%n", turma.getNome());
            System.out.printf("Curso: %s%n", turma.getCurso());
            System.out.printf("Número estudantes: %d%n", turma.getEstudantes().size());
            System.out.println();
        }
    }

    private void atualizarTurma() {
        System.out.print("Digite o código da turma a ser modificada: \n> ");
        int codigoTurma = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o código do curso da turma: \n> ");
        int codigoCurso = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o nome da turma: \n> ");
        String nomeTurma = scanner.nextLine();

        Turma turma = gerenciarTurma.findById(codigoTurma);
        Curso curso = gerenciarCurso.findById(codigoCurso);

        turma.setCurso(curso);
        turma.setNome(nomeTurma);

        gerenciarTurma.update(turma);
    }

    private void removerTurma() {
        System.out.print("Digite o código da turma a ser removido: \n> ");
        int codigoTurma = scanner.nextInt();
        scanner.nextLine();

        Turma turma = gerenciarTurma.findById(codigoTurma);

        if (!turma.getEstudantes().isEmpty()) {
            System.out.println("Turma possui estudantes, não é possível removê-la");
            return;
        }

        gerenciarTurma.remove(codigoTurma);
        System.out.println("Turma removida com sucesso");
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

        System.out.println();
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

        System.out.println();
        System.out.printf("Código: %d%n", curso.getCodigo());
        System.out.printf("Curso: %s%n", curso.getNome());
        System.out.printf("Duração: %d%n", curso.getDuracaoCurso());
        System.out.println();
    }

    private void atualizarCurso() {
        System.out.print("Digite o código do curso a ser modificado: \n> ");
        int codigoCurso = scanner.nextInt();
        scanner.nextLine();

        Curso curso = gerenciarCurso.findById(codigoCurso);

        if (curso == null) {
            System.out.println("Curso solicitado não foi encontrado");
            return;
        }

        System.out.print("Digite o novo nome do curso: \n> ");
        String nome = scanner.nextLine();

        System.out.print("Digite a nova duração do curso: \n> ");
        int duracao = scanner.nextInt();
        scanner.nextLine();

        curso.setNome(nome);
        curso.setDuracaoCurso(duracao);

        gerenciarCurso.update(curso);
    }

    private void removerCurso() {
        System.out.print("Digite o código do curso a ser removido: \n> ");
        int codigoCurso = scanner.nextInt();
        scanner.nextLine();

        Curso curso = gerenciarCurso.findById(codigoCurso);

        for (Turma turma: gerenciarTurma.list()) {
            if (turma.getCurso() == curso) {
                System.out.println("Não é possível remover o curso, pois ele está associado a uma turma");
                return;
            }
        }

        gerenciarCurso.remove(codigoCurso);
        System.out.println("Curso removido com sucesso");
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.printf("[ %2d ] - Adicionar estudante%n", ADICIONAR_ESTUDANTE);
        System.out.printf("[ %2d ] - Listar estudantes%n", LISTAR_ESTUDANTES);
        System.out.printf("[ %2d ] - Listar estudantes por nome%n", LISTAR_ESTUDANTES_POR_NOME);
        System.out.printf("[ %2d ] - Listar estudantes de uma turma%n", LISTAR_ESTUDANTES_TURMA);
        System.out.printf("[ %2d ] - Pesquisar estudante%n", PESQUISAR_ESTUDANTE);
        System.out.printf("[ %2d ] - Atualizar informações estudante%n", ATUALIZAR_ESTUDANTE);
        System.out.printf("[ %2d ] - Remover estudante%n", REMOVER_ESTUDANTE);
        System.out.printf("[ %2d ] - Adicionar turma%n", ADICIONAR_TURMA);
        System.out.printf("[ %2d ] - Listar turmas%n", LISTAR_TURMAS);
        System.out.printf("[ %2d ] - Listar turmas por curso%n", LISTAR_TURMAS_CURSO);
        System.out.printf("[ %2d ] - Atualizar turma%n", ATUALIZAR_TURMA);
        System.out.printf("[ %2d ] - Remover turma%n", REMOVER_TURMA);
        System.out.printf("[ %2d ] - Adicionar curso%n", ADICIONAR_CURSO);
        System.out.printf("[ %2d ] - Listar cursos%n", LISTAR_CURSOS);
        System.out.printf("[ %2d ] - Listar cursos por nome%n", LISTAR_CURSOS_POR_NOME);
        System.out.printf("[ %2d ] - Pesquisar curso%n", PESQUISAR_CURSO);
        System.out.printf("[ %2d ] - Atualizar curso%n", ATUALIZAR_CURSO);
        System.out.printf("[ %2d ] - Remover curso%n", REMOVER_CURSO);
        System.out.printf("[ %2d ] - Sair do programa%n", SAIR);
    }
}
