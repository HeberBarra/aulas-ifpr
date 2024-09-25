package org.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.crud.livro.GerenciarLivro;
import org.crud.livro.Livro;
import java.awt.EventQueue;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Main implements Callable<Void> {

    public static final int ADICIONAR_LIVRO = 1;
    public static final int LISTAR_LIVROS = 2;
    public static final int PESQUISAR_ID = 3;
    public static final int PESQUISAR_TITULO = 4;
    public static final int PESQUISAR_ISBN = 5;
    public static final int ATUALIZAR_LIVRO = 6;
    public static final int EXCLUIR_LIVRO = 7;
    public static final int SAIR = 8;
    private final EntityManagerFactory entityManagerFactory;
    private final EntityManager entityManager;
    private final Scanner scanner;
    private final GerenciarLivro gerenciarLivro;

    public Main() {
        entityManagerFactory = Persistence.createEntityManagerFactory("crud");
        entityManager = entityManagerFactory.createEntityManager();
        scanner = new Scanner(System.in);
        gerenciarLivro = new GerenciarLivro(entityManager);
    }

    public static void main(String[] args) {
        new Main().call();
    }

    public void executar() {
        while (true) {
            mostrarMenu();
            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == ADICIONAR_LIVRO) {
                adicionarLivro();
            } else if (escolha == LISTAR_LIVROS) {
                listarLivros();
            } else if (escolha == PESQUISAR_ID) {
                pesquisarId();
            } else if (escolha == PESQUISAR_TITULO) {
                pesquisarTitulo();
            } else if (escolha == PESQUISAR_ISBN) {
                pesquisarIsbn();
            } else if (escolha == ATUALIZAR_LIVRO) {
                atualizarLivro();
            } else if (escolha == EXCLUIR_LIVRO) {
                excluirLivro();
            } else if (escolha == SAIR) {
                System.out.println("Encerrando o programa...");
                return;
            } else {
                System.out.println("Opção inválida. Por favor tente novamente...");
            }
        }
    }

    public void adicionarLivro() {
        System.out.print("Digite o titulo do livro: \n> ");
        String titulo = scanner.nextLine();

        System.out.print("Digite o ISBN do livro: \n> ");
        String isbn = scanner.nextLine();

        System.out.print("Digite a edição do livro(apenas o número): \n> ");
        long edicao = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Digite o ano de publicação do livro: \n> ");
        long ano = scanner.nextLong();
        scanner.nextLine();

        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setIsbn(isbn);
        livro.setEdicao(edicao);
        livro.setAno(ano);

        gerenciarLivro.create(livro);
        System.out.println("Livro adicionado com sucesso");
    }

    public void listarLivros() {
        List<Livro> livros = gerenciarLivro.list();

        if (livros.isEmpty()) {
            System.out.println("Não há livros cadastrados");
            return;
        }

        for (Livro livro: livros) {
            System.out.printf("ID: %d%n", livro.getId());
            System.out.printf("Titulo: %s%n", livro.getTitulo());
            System.out.printf("ISBN: %s%n", livro.getIsbn());
            System.out.printf("Edição: %d%n", livro.getEdicao());
            System.out.printf("Ano: %d%n", livro.getAno());
        }

    }

    public void pesquisarId() {
        System.out.print("Digite o ID a ser pesquisado: \n> ");
        long id = scanner.nextLong();
        scanner.nextLine();

        Livro livro = gerenciarLivro.findById(id);
        if (livro == null) {
            System.out.println("Livro não encontrado");
            return;
        }

        System.out.printf("ID: %d%n", livro.getId());
        System.out.printf("Titulo: %s%n", livro.getTitulo());
        System.out.printf("ISBN: %s%n", livro.getIsbn());
        System.out.printf("Edição: %d%n", livro.getEdicao());
        System.out.printf("Ano: %d%n", livro.getAno());
    }

    public void pesquisarTitulo() {
        System.out.print("Digite o titulo do livro a ser pesquisado: \n> ");
        String titulo = scanner.nextLine();

        List<Livro> livros = gerenciarLivro.searchByTitle(titulo);

        if (livros.isEmpty()) {
            System.out.println("Não há livros cadastrados");
            return;
        }

        for (Livro livro: livros) {
            System.out.printf("ID: %d%n", livro.getId());
            System.out.printf("Titulo: %s%n", livro.getTitulo());
            System.out.printf("ISBN: %s%n", livro.getIsbn());
            System.out.printf("Edição: %d%n", livro.getEdicao());
            System.out.printf("Ano: %d%n", livro.getAno());
        }

    }

    public void pesquisarIsbn() {
        System.out.print("Digite o ISBN do livro a ser pesquisado: \n> ");
        String isbn = scanner.nextLine();

        Livro livro = gerenciarLivro.findByIsbn(isbn);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        System.out.printf("ID: %d%n", livro.getId());
        System.out.printf("Titulo: %s%n", livro.getTitulo());
        System.out.printf("ISBN: %s%n", livro.getIsbn());
        System.out.printf("Edição: %d%n", livro.getEdicao());
        System.out.printf("Ano: %d%n", livro.getAno());
    }

    public void atualizarLivro() {
        System.out.print("Digite o id do livro a ser atualizado: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Digite o novo titulo do livro: \n> ");
        String titulo = scanner.nextLine();

        System.out.print("Digite o novo ISBN do livro: \n> ");
        String isbn = scanner.nextLine();

        System.out.print("Digite a nova edição do livro(apenas o número): \n> ");
        long edicao = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Digite o novo ano de publicação do livro: \n> ");
        long ano = scanner.nextLong();
        scanner.nextLine();

        Livro livro = gerenciarLivro.findById(id);
        livro.setTitulo(titulo);
        livro.setIsbn(isbn);
        livro.setEdicao(edicao);
        livro.setAno(ano);

        gerenciarLivro.update(livro);
        System.out.println("Livro atualizado com sucesso");
    }

    public void excluirLivro() {
        System.out.print("Digite o id do livro a ser excluído: \n> ");
        long id = scanner.nextLong();
        scanner.nextLine();

        Livro livro = gerenciarLivro.findById(id);

        if (livro == null) {
            System.out.println("O livro solicitado não foi encontrado");
            return;
        }

        gerenciarLivro.delete(livro);
        System.out.println("Livro excluído com sucesso");
    }

    public void mostrarMenu() {
        System.out.printf("[%d] Adicionar livro %n", ADICIONAR_LIVRO);
        System.out.printf("[%d] Listar livros%n", LISTAR_LIVROS);
        System.out.printf("[%d] Pesquisar por ID%n", PESQUISAR_ID);
        System.out.printf("[%d] Pesquisar por titulo%n", PESQUISAR_TITULO);
        System.out.printf("[%d] Pesquisar por ISBN%n", PESQUISAR_ISBN);
        System.out.printf("[%d] Atualizar livro%n", ATUALIZAR_LIVRO);
        System.out.printf("[%d] Excluir livro%n", EXCLUIR_LIVRO);
        System.out.printf("[%d] Sair%n", SAIR);
        System.out.print("Escolha uma opção: \n> ");
    }

    @Override
    public Void call() {

        EventQueue.invokeLater(this::executar);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            entityManagerFactory.close();
            entityManager.close();
        }));

        return null;
    }
}
