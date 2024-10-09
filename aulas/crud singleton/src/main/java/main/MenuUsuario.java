package main;

import console.Console;
import usuario.Usuario;
import usuario.UsuarioDAO;

import java.util.List;

public class MenuUsuario {

    private final static int CADASTRAR = 1;
    private final static int EDITAR = 2;
    private final static int LISTAR = 3;
    private final static int REMOVER = 4;
    private final static int VOLTAR = 9;
    private Console console = new Console();
    private UsuarioDAO usuarioDAO;

    public MenuUsuario() {
        usuarioDAO = new UsuarioDAO();
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

    public void cadastrar() {
        Usuario usuario = new Usuario();
        lerDados(usuario);

        usuarioDAO.create(usuario);
        System.out.println("Usuário adicionado com sucesso");
    }

    private void listar() {
        List<Usuario> usuarios = usuarioDAO.list();

        if (usuarios.isEmpty()) {
            System.out.println("Não há usuários cadastrados");
            return;
        }

        for (Usuario usuario: usuarios) {
            System.out.printf("ID: %d%n", usuario.getId());
            System.out.printf("Nome: %s%n", usuario.getNome());
            System.out.printf("E-Mail: %s%n", usuario.getEmail());
            System.out.printf("CPF: %d%n", usuario.getCpf());
        }
    }

    private void editar() {
        System.out.println("Digite o ID para editar: ");
        int id = console.readInt();

        Usuario usuario = usuarioDAO.findById(id);

        if (usuario == null) {
            System.out.println("Produto não encontrado");
            return;
        }

        lerDados(usuario);
        usuarioDAO.update(usuario);
        System.out.println("Produto atualizado com sucesso");
    }

    private void remover() {
        int id = console.readInt("Digite o id do usuário a ser excluído: ");

        Usuario usuario = usuarioDAO.findById(id);

        if (usuario == null) {
            System.out.println("O usuário solicitado não foi encontrado");
            return;
        }

        usuarioDAO.delete(id);
        System.out.println("Usuário excluído com sucesso");
    }

    private void lerDados(Usuario usuario) {
        String nome = console.readLine("Digite o nome do usuário: ");
        String email = console.readLine("Digite o e-mail do usuário: ");
        long cpf = console.readLong("Digite o cpf do usuário(apenas o número): ");
        String senha = console.readLine("Digite a senha do usuário: ");

        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setCpf(cpf);
        usuario.setSenha(senha);
    }

    private void mostrarMenu() {
        System.out.println("\n------ SUPER CRUD ------\n");
        System.out.printf("[ %d ] - Cadastrar usuário%n", CADASTRAR);
        System.out.printf("[ %d ] - Editar usuário%n", EDITAR);
        System.out.printf("[ %d ] - Listar usuárioss%n", LISTAR);
        System.out.printf("[ %d ] - Remover usuário%n", REMOVER);
        System.out.printf("[ %d ] - Voltar%n", VOLTAR);
    }

    public void fechar() {
        usuarioDAO.fecharConexao();
    }
}
