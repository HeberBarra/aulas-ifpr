package main;

import console.Console;
import produto.Produto;
import produto.ProdutoDAO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class MenuProduto {

    private Console console;
    private ProdutoDAO produtoDAO;
    private final static int CADASTRAR = 1;
    private final static int EDITAR = 2;
    private final static int LISTAR = 3;
    private final static int REMOVER = 4;
    private final static int VOLTAR = 9;

    public MenuProduto() {
        console = new Console();
        produtoDAO = new ProdutoDAO();
    }

    public void show() {
        int opcao;

        do {
            mostrarMenu();
            opcao = console.readInt("Escolha: ");

            if (opcao == CADASTRAR) {
                System.out.println("Cadastrar");
                cadastrar();
            } else if (opcao == EDITAR) {
                System.out.println("Editar");
                editar();
            } else if (opcao == LISTAR) {
                System.out.println("Listar");
                listar();
            } else if (opcao == REMOVER) {
                System.out.println("Remover");
                remover();
            }

        } while (opcao != VOLTAR);
    }

    private void cadastrar() {
        Produto produto = new Produto();
        lerDados(produto);
        produtoDAO.create(produto);
        System.out.println("Produto cadastro com sucesso");
    }

    private void listar() {
        List<Produto> produtos = produtoDAO.list();

        for (Produto produto : produtos) {
            System.out.println("\nID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
        }
    }

    private void editar() {
        System.out.println("Digite o ID para editar: ");
        int id = console.readInt();

        Produto produto = produtoDAO.findById(id);

        if (produto == null) {
            System.out.println("Produto não encontrado");
            return;
        }

        lerDados(produto);
        produtoDAO.update(produto);
        System.out.println("Produto atualizado com sucesso");
    }

    private void remover() {
        System.out.println("Digite o id para remover: ");

        int idRemover = console.readInt();

        Produto produto = produtoDAO.findById(idRemover);
        if (produto == null) {
            System.out.println("ID informado não existe");
        } else {
            System.out.println("ID: " + produto.getId());
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Marca: " + produto.getMarca());
            System.out.println("Preço: R$" + produto.getPreco());

            System.out.println("Confirma a exclusão: 0 - sim, 1 - não");
            int opcao = console.readInt();

            if (opcao == 0) {
                produtoDAO.delete(idRemover);
                System.out.println("OK, removido com sucesso");
            } else {
                System.out.println("Cancelar");
            }
        }
    }

    private void lerDados(Produto produto) {
        String nome = console.readLine("Digite o nome: ");
        String marca = console.readLine("Digite a marca: ");
        LocalDate dataFabricacao = console.readLocalDate("Digite a data de fabricação: ");
        BigDecimal preco = console.readBigDecimal("Digite o preço: ");

        produto.setNome(nome);
        produto.setMarca(marca);
        produto.setDataFabricacao(dataFabricacao);
        produto.setPreco(preco);
    }

    private void mostrarMenu() {
        System.out.println("\n------ SUPER CRUD ------\n");
        System.out.printf("[ %d ] - Cadastrar produto%n", CADASTRAR);
        System.out.printf("[ %d ] - Editar produto%n", EDITAR);
        System.out.printf("[ %d ] - Listar produtos%n", LISTAR);
        System.out.printf("[ %d ] - Remover produto%n", REMOVER);
        System.out.printf("[ %d ] - Voltar%n", VOLTAR);
    }

}
