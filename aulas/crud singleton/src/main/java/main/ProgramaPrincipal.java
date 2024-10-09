package main;

import conector.ConectorBanco;
import org.apache.log4j.BasicConfigurator;
import console.Console;

public class ProgramaPrincipal {

	private final static int GERENCIAMENTO_USUARIO = 0;
	private final static int GERENCIAMENTO_PRODUTO = 1;
	private final static int SAIR = 9;
	private MenuProduto menuProduto;
	private MenuUsuario menuUsuario;
	private Console console;

	public ProgramaPrincipal() {
		console = new Console();
		menuProduto = new MenuProduto();
		menuUsuario = new MenuUsuario();
	}

	public static void main(String[] args) {
		BasicConfigurator.configure();
		new ProgramaPrincipal().executar();
	}

	private void executar() {
		int opcao = 0;

		do {
			mostrarMenu();
			opcao = console.readInt("Escolha: ");

			if (opcao == GERENCIAMENTO_PRODUTO) {
				menuProduto.show();
			} else if (opcao == GERENCIAMENTO_USUARIO) {
				menuUsuario.show();
			} else if (opcao == SAIR) {
				System.out.println("Encerrando o programa");
			} else {
				System.out.println("Opção inválida");
			}

		} while (opcao != SAIR);

		menuUsuario.fechar();
		menuProduto.fechar();
		ConectorBanco.fecharConexao();
	}

	private void mostrarMenu() {
		System.out.println("\n------ SUPER CRUD ------\n");
		System.out.printf("[ %d ] Gerenciamento Usuário%n", GERENCIAMENTO_USUARIO);
		System.out.printf("[ %d ] Gerenciamento Produto%n", GERENCIAMENTO_PRODUTO);
		System.out.printf("[ %d ] Sair%n", SAIR);
	}


}
