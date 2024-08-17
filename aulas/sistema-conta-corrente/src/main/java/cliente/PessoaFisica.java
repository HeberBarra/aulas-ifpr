package cliente;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class PessoaFisica extends Cliente {

    private static final Logger logger = Logger.getLogger(PessoaFisica.class.getName());
    private long CPF;
    private LocalDate dataNascimento;
    private DateTimeFormatter dateTimeFormatter;
    
    public PessoaFisica() {
        super();
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        mascara = "###.###.###-##";
        contarDigitosMascara();
    }

    @Override
    public void mostrarInformacoes() {
        super.mostrarInformacoes();
        System.out.printf("CPF: %s%n", formatar(CPF));
        System.out.printf("DATA DE NASCIMENTO: %s%n", dataNascimento.format(dateTimeFormatter));
    }

    @Override
    protected String formatar(long valor) {
        String cpfFormatado = "";

        try {
            MaskFormatter maskFormatter = new MaskFormatter(mascara);
            maskFormatter.setValueContainsLiteralCharacters(false);
            cpfFormatado = maskFormatter.valueToString(pad(String.valueOf(CPF)));
        } catch (ParseException e) {
            System.out.println("Ocorreu um erro ao tentar formatar o CPF. Encerrando o programa...");
            logger.severe(e.getMessage());
            System.exit(0);
        }

        return cpfFormatado;
    }

    public long getCPF() {
        return CPF;
    }

    public void setCPF(long CPF) {
        this.CPF = CPF;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
