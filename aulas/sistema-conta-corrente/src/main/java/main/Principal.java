package main;

import cliente.Cliente;
import cliente.GerenciarCliente;
import cliente.PessoaFisica;
import cliente.PessoaJuridica;
import conta.ContaCorrente;
import conta.ContaEspecial;
import conta.ContaPoupanca;
import conta.GerenciarConta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private static final int ADICIONAR_CLIENTE = 1;
    private static final int LISTAR_CLIENTES = 2;
    private static final int LISTAR_CLIENTES_POR_NOME = 3;
    private static final int PESQUISAR_CLIENTE = 4;
    private static final int ATUALIZAR_CLIENTE = 5;
    private static final int REMOVER_CLIENTE = 6;
    private static final int ADICIONAR_CONTA = 7;
    private static final int LISTAR_CONTAS = 8;
    private static final int PESQUISAR_CONTA = 9;
    private static final int ATUALIZAR_CONTA = 10;
    private static final int REMOVER_CONTA = 11;
    private static final int LOGIN = 12;
    private static final int LOGOUT = 13;
    private static final int SACAR = 14;
    private static final int DEPOSITAR = 15;
    private static final int TRANSFERIR = 16;
    private static final int SAIR = 17;
    private static final int PESSOA_FISICA = 0;
    private static final int PESSOA_JURIDICA = 1;
    private static final int CONTA_POUPANCA = 0;
    private static final int CONTA_ESPECIAL = 1;

    private final DateTimeFormatter dateTimeFormatter;
    private final GerenciarCliente gerenciarCliente;
    private final GerenciarConta gerenciarConta;
    private final Scanner scanner;
    private Cliente usuarioAtual;

    public static void main(String[] args) {
        new Principal().run();
    }

    public Principal() {
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        gerenciarCliente = new GerenciarCliente();
        gerenciarConta = new GerenciarConta();
        scanner = new Scanner(System.in);
    }

    private void run() {

        while (true) {
            mostrarMenu();
            int escolha = scanner.nextInt();
            scanner.nextLine();

            if (escolha == ADICIONAR_CLIENTE) {
                criarCliente();
            } else if (escolha == LISTAR_CLIENTES) {
                listarClientes();
            } else if (escolha == LISTAR_CLIENTES_POR_NOME) {
                listarClientesPorNome();
            } else if (escolha == PESQUISAR_CLIENTE) {
                pesquisarCliente();
            } else if (escolha == ATUALIZAR_CLIENTE) {
                atualizarCliente();
            } else if (escolha == REMOVER_CLIENTE) {
                removerCliente();
            } else if (escolha == ADICIONAR_CONTA) {
                adicionarConta();
            } else if (escolha == LISTAR_CONTAS) {
                listarContas();
            } else if (escolha == PESQUISAR_CONTA) {
                pesquisarConta();
            } else if (escolha == ATUALIZAR_CONTA) {
                atualizarConta();
            } else if (escolha == REMOVER_CONTA) {
                removerConta();
            } else if (escolha == LOGIN) {
                login();
            } else if (escolha == LOGOUT) {
                logout();
            } else if (escolha == SACAR) {
                sacar();
            } else if (escolha == DEPOSITAR) {
                depositar();
            } else if (escolha == TRANSFERIR) {
                transferir();
            } else if (escolha == SAIR) {
                return;
            } else {
                System.out.println("Opção inválida. Tente novamente...");
            }

        }

    }

    private void criarCliente() {

        System.out.print("O cliente é uma pessoa física ou jurídica? [ 0 / 1 ]\n> ");
        int tipoCliente = scanner.nextInt();
        scanner.nextLine();

        if (tipoCliente != PESSOA_JURIDICA && tipoCliente != PESSOA_FISICA) {
            System.out.println("Opção inválida. Cancelando a operação...");
            return;
        }

        System.out.print("Digite o nome do cliente: \n> ");
        String nome = scanner.nextLine().strip();
        String login = pegarLoginCliente();
        String senha = pegarSenhaCliente();
        long codigo = 0;

        while (codigo == 0) {
            codigo = pegarCodigoCliente(tipoCliente);
        }

        Cliente cliente;
        if (tipoCliente == PESSOA_FISICA) {
            PessoaFisica pessoaFisica= new PessoaFisica();
            pessoaFisica.setCPF(codigo);
            pessoaFisica.setDataNascimento(pegarDataNascimentoCliente());
            cliente = pessoaFisica;
        } else {
            PessoaJuridica pessoaJuridica = new PessoaJuridica();
            pessoaJuridica.setCNPJ(codigo);
            System.out.print("Digite a inscrição estadual do cliente: \n> ");
            String inscricaoEstadual = scanner.nextLine();
            pessoaJuridica.setInscricaoEstadual(inscricaoEstadual);
            cliente = pessoaJuridica;
        }

        cliente.setNome(nome);
        cliente.setLogin(login);
        cliente.setSenha(senha);
        gerenciarCliente.add(cliente);
        System.out.println("Cliente adicionado com sucesso!");
    }

    private long pegarCodigoCliente(int tipoCliente) {
        String regexCPF = "([0-9]{2}\\.?[0-9]{3}[.]?[0-9]{3}/?[0-9]{4}-?[0-9]{2})|([0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}-?[0-9]{2})";
        String regexCNPJ = "\\d{2}\\.?\\d{3}\\.?\\d{3}/?\\d{4}-?\\d{2}";

        if (tipoCliente == PESSOA_FISICA) {
            System.out.print("Digite seu CPF: \n> ");
            String CPF = scanner.nextLine();

            if (!CPF.matches(regexCPF)) {
                System.out.println("CPF Inválido. Tente novamente...");
                return 0;
            }

            CPF = CPF.replace(".", "");
            CPF = CPF.replace("-", "");
            CPF = CPF.replace("/", "");
            CPF = CPF.replace(" ", "");

            return Long.parseLong(CPF);
        }

        System.out.print("Digite o CNPJ do cliente: \n> ");
        String CNPJ = scanner.nextLine();

        if (!CNPJ.matches(regexCNPJ)) {
            System.out.println("CNPJ Inválido. Tente novamente...");
            return 0;
        }

        CNPJ = CNPJ.replace(".", "");
        CNPJ = CNPJ.replace("-", "");
        CNPJ = CNPJ.replace("/", "");
        CNPJ = CNPJ.replace(" ", "");

        return Long.parseLong(CNPJ);
    }

    private String pegarLoginCliente() {
        while (true) {
            boolean loginValido = true;
            System.out.print("Digite o login do cliente: \n> ");
            String login = scanner.nextLine().strip();
            List<Cliente> clientes = gerenciarCliente.list();

            for (Cliente cliente : clientes) {
                if (cliente.getLogin().equals(login)) {
                    loginValido = false;
                    break;
                }
            }

            if (!loginValido) {
                System.out.println("Este login já está em uso por outro usuário. Por favor digite outro...");
                continue;
            }

            return login;
        }

    }

    private String pegarSenhaCliente() {
        while(true) {
            System.out.print("Digite a senha do cliente: \n> ");
            String senha = scanner.nextLine().strip();

            System.out.print("Confirme a senha digitada: \n> ");
            String confirmacaoSenha = scanner.nextLine().strip();

            if (senha.equals(confirmacaoSenha)) {
                return senha;
            }

            System.out.println("Senha incorreta. Por favor tente novamente... ");
        }
    }

    private LocalDate pegarDataNascimentoCliente() {
        while (true) {
            System.out.print("Digite a data de nascimento do cliente [dd/mm/aaaa]: \n> ");
            String dataNascimento = scanner.nextLine().strip();

            try {
                return LocalDate.parse(dataNascimento, dateTimeFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data de nascimento inserida é inválida");
            }
        }
    }

    private void listarClientes() {
       gerenciarCliente.list().forEach(Cliente::mostrarInformacoes);
    }

    private void listarClientesPorNome() {
        System.out.print("Digite o nome a ser pesquisado: \n> ");
        String nome = scanner.nextLine().strip();
        List<Cliente> clientes = gerenciarCliente.listByName(nome);

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente foi encontrado.");
            return;
        }

        clientes.forEach(Cliente::mostrarInformacoes);
    }

    private void pesquisarCliente() {
        System.out.print("Digite o ID do cliente a ser procurado: \n> ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = gerenciarCliente.findById(id);

        if (cliente == null) {
            System.out.println("O cliente especificado não foi encontrado.");
            return;
        }

        cliente.mostrarInformacoes();
        cliente.getContas().forEach(ContaCorrente::mostrarInformacoes);
    }

    private void atualizarCliente() {
        System.out.print("Digite o ID do cliente a ser editado: \n> ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Cliente cliente = gerenciarCliente.findById(id);
        System.out.print("Digite o novo nome do cliente: \n> ");
        String nome = scanner.nextLine().strip();
        String login = pegarLoginCliente();
        String senha = pegarSenhaCliente();
        int tipoCliente = cliente instanceof PessoaFisica ? PESSOA_FISICA : PESSOA_JURIDICA;
        long codigo = pegarCodigoCliente(tipoCliente);

        cliente.setNome(nome);
        cliente.setLogin(login);
        cliente.setSenha(senha);

        if (cliente instanceof PessoaFisica pessoaFisica) {
            LocalDate dataNascimento = pegarDataNascimentoCliente();
            pessoaFisica.setCPF(codigo);
            pessoaFisica.setDataNascimento(dataNascimento);
        }

        if (cliente instanceof PessoaJuridica pessoaJuridica) {
            System.out.print("Digite a nova inscrição estadual do cliente: \n> ");
            String inscricaoEstadual = scanner.nextLine().strip();
            pessoaJuridica.setInscricaoEstadual(inscricaoEstadual);
            pessoaJuridica.setCNPJ(codigo);
        }

        gerenciarCliente.update(cliente);
    }

    private void removerCliente() {
        System.out.print("Digite o ID do cliente a ser removido: \n> ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = gerenciarCliente.findById(id);

        for (ContaCorrente conta: gerenciarConta.list()) {
            if (conta.getProprierario().equals(cliente)) {
                gerenciarConta.delete(conta);
            }
        }

        gerenciarCliente.delete(cliente);
    }

    public void adicionarConta() {

        if (gerenciarCliente.list().isEmpty()) {
            System.out.println("É necessário adicionar clientes antes de criar uma conta. Cancelando a operação...");
            return;
        }

        System.out.print("Digite o ID do proprietario da conta: \n> ");
        int id = scanner.nextInt();

        Cliente cliente = gerenciarCliente.findById(id);

        if (cliente == null) {
            System.out.println("O cliente solicitado não foi encontrado. Cancelando a operação...");
            return;
        }

        System.out.print("Digite o número da conta: \n> ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite a agência da conta: \n> ");
        String agencia = scanner.nextLine().strip();

        System.out.print("A conta será poupança ou especial? [ 0 / 1 ]: \n> ");
        int tipoConta = scanner.nextInt();
        scanner.nextLine();

        if ((tipoConta != CONTA_ESPECIAL) && (tipoConta != CONTA_POUPANCA)) {
            System.out.println("Tipo de conta inválido. Cancelando a operação...");
            return;
        }

        ContaCorrente conta;
        if (tipoConta == CONTA_ESPECIAL) {
            conta = new ContaEspecial();
        } else {
            conta = new ContaPoupanca();
        }

        conta.setNumero(numero);
        conta.setAgencia(agencia);
        conta.setProprierario(cliente);
        gerenciarConta.add(cliente, conta);
    }

    public void listarContas() {
        gerenciarConta.list().forEach(ContaCorrente::mostrarInformacoes);
    }

    public void pesquisarConta() {
        System.out.print("Digite o ID da conta a ser pesquisado: \n> ");
        int id = scanner.nextInt();
        ContaCorrente conta = gerenciarConta.findBy(id);

        if (conta == null) {
            System.out.println("A conta solicitada não foi encontrada.");
            return;
        }

        conta.mostrarInformacoes();
    }

    public void atualizarConta() {
        System.out.print("Digite o ID da conta a ser atualizado: \n> ");
        int idConta = scanner.nextInt();
        scanner.nextLine();

        ContaCorrente conta = gerenciarConta.findBy(idConta);

        if (conta == null) {
            System.out.println("A conta solicitada não foi encontrada. Cancelando a operação...");
            return;
        }

        System.out.print("Digite o ID do novo proprietário da conta: \n> ");
        int idProprietario = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = gerenciarCliente.findById(idProprietario);

        if (cliente == null) {
            System.out.println("O cliente solicitado não foi encontrado. Cancelando a operação...");
            return;
        }

        System.out.print("Digite o novo número da conta: \n> ");
        int numero = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite a nova agência da conta: \n> ");
        String agencia = scanner.nextLine().strip();

        conta.setProprierario(cliente);
        conta.setNumero(numero);
        conta.setAgencia(agencia);
        gerenciarConta.update(cliente, conta);
    }

    private void removerConta() {

        if (gerenciarConta.list().isEmpty()) {
            System.out.println("Não há contas a serem removidas. Cancelando a operação...");
            return;
        }

        System.out.print("Digite o ID da conta a ser removida: \n> ");
        int id = scanner.nextInt();
        scanner.nextLine();

        ContaCorrente conta = gerenciarConta.findBy(id);

        if (conta == null) {
            System.out.println("A conta solicitada não foi encontrada. Cancelando a operação...");
            return;
        }

        gerenciarConta.delete(conta.getProprierario(), conta);
    }

    public void login() {
        System.out.print("Digite o login do cliente: \n> ");
        String login = scanner.nextLine().strip();

        Cliente clienteProcurado = null;
        for (Cliente cliente: gerenciarCliente.list()) {
            if (cliente.getLogin().equals(login)) {
                clienteProcurado = cliente;
            }
        }

        if (clienteProcurado == null) {
            System.out.println("O cliente solicitado não foi encontrado. Cancelando a operação...");
            return;
        }

        System.out.print("Digite a senha do cliente: \n> ");
        String senha = scanner.nextLine().strip();

        if (clienteProcurado.getSenha().equals(senha)) {
            usuarioAtual = clienteProcurado;
            System.out.println("Login efetuado com sucesso.");
            return;
        }

        System.out.println("Senha incorreta. Cancelando a operação...");
    }

    public void logout() {
        usuarioAtual = null;
        System.out.println("Logout efetuado com sucesso.");
    }

    public void sacar() {
        if (usuarioAtual == null) {
            System.out.println("É necessário fazer login antes de sacar. Cancelando a operação...");
            return;
        }

        System.out.print("Digite o ID da conta da qual se deseja sacar: \n> ");
        int id = scanner.nextInt();
        scanner.nextLine();

        ContaCorrente conta = gerenciarConta.findBy(id);

        if (desvalidarContaCliente(conta))
            return;

        System.out.print("Qual o valor do saque?: \n> ");
        BigDecimal valor = BigDecimal.valueOf(scanner.nextDouble());
        conta.sacar(valor);
        System.out.println("Saque realizado com sucesso.");
    }

    public void depositar() {
        if (usuarioAtual == null) {
            System.out.println("É necessário fazer login antes de depositar. Cancelando a operação...");
            return;
        }

        System.out.print("Digite o ID da conta na qual se deseja depositar: \n> ");
        int id = scanner.nextInt();
        scanner.nextLine();

        ContaCorrente conta = gerenciarConta.findBy(id);

        if (desvalidarContaCliente(conta))
            return;

        System.out.print("Qual o valor do depósito?: \n> ");
        BigDecimal valor = BigDecimal.valueOf(scanner.nextDouble());
        conta.depositar(valor);
        System.out.println("Depósito realizado com sucesso.");
    }

    public void transferir() {
        if (usuarioAtual == null) {
            System.out.println("É necessário fazer login antes de transferir. Cancelando a operação...");
            return;
        }

        System.out.print("Digite o ID da conta da qual se deseja transferir: \n> ");
        int idConta = scanner.nextInt();
        scanner.nextLine();

        ContaCorrente conta = gerenciarConta.findBy(idConta);

        if (desvalidarContaCliente(conta))
            return;

        System.out.print("Digite o ID da conta para qual se deseja transferir: \n> ");
        int idContaAlvo = scanner.nextInt();
        scanner.nextLine();

        ContaCorrente contaAlvo = gerenciarConta.findBy(idContaAlvo);

        if (contaAlvo == null) {
            System.out.println("A conta solicitada não foi encontrada. Cancelando a operação...");
            return;
        }

        System.out.print("Qual o valor da transferência?: \n> ");
        BigDecimal valor = BigDecimal.valueOf(scanner.nextDouble());
        conta.transferir(valor, contaAlvo);
        System.out.println("Transferência realizada com sucesso.");
    }

    private boolean desvalidarContaCliente(ContaCorrente conta) {
        if (conta == null) {
            System.out.println("A conta solicitada não foi encontrada. Cancelando a operação...");
            return true;
        }

        if (!conta.getProprierario().equals(usuarioAtual)) {
            System.out.println("Esta conta não pertence a você. Cancelando a operação...");
            return true;
        }

        return false;
    }

    private void mostrarMenu() {
        System.out.println("[ 01 ] Adicionar Cliente");
        System.out.println("[ 02 ] Listar Clientes");
        System.out.println("[ 03 ] Listar Clientes por Nome");
        System.out.println("[ 04 ] Pesquisar Cliente por ID");
        System.out.println("[ 05 ] Atualizar Cliente");
        System.out.println("[ 06 ] Remover cliente");
        System.out.println("[ 07 ] Adicionar Conta");
        System.out.println("[ 08 ] Listar Contas");
        System.out.println("[ 09 ] Pesquisar Conta");
        System.out.println("[ 10 ] Atualizar Conta");
        System.out.println("[ 11 ] Remover Conta");
        System.out.println("[ 12 ] Login");
        System.out.println("[ 13 ] Logout");
        System.out.println("[ 14 ] Sacar");
        System.out.println("[ 15 ] Depositar");
        System.out.println("[ 16 ] Transferir");
        System.out.println("[ 17 ] Sair");
        System.out.print("Escolha uma opção: \n> ");
    }

}
