package main;

import usuario.GerenciarUsuario;
import usuario.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ProgramaPrincipal {

    private Scanner scanner;
    private final static int CADASTRAR = 1;
    private final static int EDITAR = 2;
    private final static int LISTAR = 3;
    private final static int REMOVER = 4;
    private final static int PESQUISAR = 5;
    private final static int SAIR = 9;
    private GerenciarUsuario gerenciarUsuario;

    public ProgramaPrincipal() {
        scanner = new Scanner(System.in);
        gerenciarUsuario = new GerenciarUsuario();
    }

    public static void main(String[] args) {
        new ProgramaPrincipal().executar();
    }

    private void executar() {
        do {
            mostrarMenu();
            int opcaoEscolhida = scanner.nextInt();

            if (opcaoEscolhida == CADASTRAR) {
                System.out.println("Cadastrar usuário");
                cadastrar();
            } else if (opcaoEscolhida == EDITAR) {
                System.out.println("Editar usuário");
                editar();
            } else if (opcaoEscolhida == LISTAR) {
                System.out.println("Listar usuários");
                listar();
            } else if (opcaoEscolhida == REMOVER) {
                System.out.println("Remover usuário");
                remover();
            } else if (opcaoEscolhida == PESQUISAR) {
                System.out.println("Pesquisar por nome");
                pesquisarPorNome();
            } else if (opcaoEscolhida == SAIR) {
                return;
            }
        } while (true);
    }

    private void pesquisarPorNome() {
        scanner.nextLine();
        System.out.print("Digite o nome a ser procurado: \n> ");
        String nome = scanner.nextLine();
        List<Usuario> usuarios = gerenciarUsuario.listarPorNome(nome);

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário com esse nome foi encontrado");
            return;
        }

        for (Usuario usuario: usuarios) {
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("E-mail: " + usuario.getEmail());
            System.out.println("Data de nascimento: " + usuario.getDataNascimento());
        }

    }

    private void editar() {
        System.out.print("Digite o id para editar: \n> ");
        int idEditar = scanner.nextInt();
        Usuario usuario = gerenciarUsuario.findById(idEditar);

        scanner.nextLine();
        System.out.print("Digite o novo nome: \n> ");
        String novoNome = scanner.nextLine();
        System.out.print("Digite o novo e-mail: \n> ");
        String novoEmail = scanner.nextLine();
        System.out.print("Digite a nova data de nascimento (dd/mm/yyyy):\n> ");
        String dataNascimento = scanner.nextLine();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dataNascimento, dateTimeFormatter);

        usuario.setNome(novoNome);
        usuario.setEmail(novoEmail);
        usuario.setDataNascimento(data);

        gerenciarUsuario.update(usuario);
        System.out.println("Usuário atualizado com sucesso");

    }

    private void remover() {
        System.out.print("Digite o id para remover:\n> ");
        int idRemover = scanner.nextInt();
        Usuario usuario = gerenciarUsuario.findById(idRemover);

        if (usuario == null) {
            System.out.println("Id informado não existe");
        } else {
            System.out.println("ID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("E-mail: " + usuario.getEmail());
            System.out.println("Data de nascimento: " + usuario.getDataNascimento());

            System.out.print("Confirma a exclusão? \n[ 0 ] Sim \n[ 1 ] Não\n> ");
            int opcao = scanner.nextInt();

            if (opcao == 0) {
                gerenciarUsuario.remove(idRemover);
                System.out.println("Removido com sucesso");
            } else {
                System.out.println("Cancelado");
            }

        }

    }

    private void listar() {
        List<Usuario> usuarios = gerenciarUsuario.list();
        for (Usuario usuario : usuarios) {
            System.out.println("\nID: " + usuario.getId());
            System.out.println("Nome: " + usuario.getNome());
        }

    }

    private void cadastrar() {
        scanner.nextLine();
        System.out.print("Digite seu nome:\n> ");
        String nome = scanner.nextLine();
        System.out.print("Digite seu email:\n> ");
        String email = scanner.nextLine();
        System.out.print("Digite sua data de nascimento (dd/mm/yyyy):\n> ");
        String dataNascimento = scanner.nextLine();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(dataNascimento, dateTimeFormatter);

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setDataNascimento(data);

        gerenciarUsuario.create(usuario);
        System.out.println("Usuário cadastrado com sucesso");
    }

    private void mostrarMenu() {
        System.out.println("----- SUPER CRUD -----");
        System.out.println("1 - Cadastrar usuário");
        System.out.println("2 - Editar usuário");
        System.out.println("3 - Listar usuários");
        System.out.println("4 - Remover usuário");
        System.out.println("5 - Pesquisar por nome");
        System.out.println("9 - Sair");
    }


}
