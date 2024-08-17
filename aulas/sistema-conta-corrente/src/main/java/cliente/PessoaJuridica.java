package cliente;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.util.logging.Logger;

public class PessoaJuridica extends Cliente {

    private static final Logger logger = Logger.getLogger(PessoaJuridica.class.getName());
    private long CNPJ;
    private String inscricaoEstadual;

    public PessoaJuridica() {
        super();
        mascara = "##.###.###/####-##";
        contarDigitosMascara();
    }

    @Override
    public void mostrarInformacoes() {
        super.mostrarInformacoes();
        System.out.printf("CNPJ: %s%n", formatar(CNPJ));
        System.out.printf("INSCRIÇÃO ESTADUAL: %s%n", inscricaoEstadual);
    }

    @Override
    protected String formatar(long valor) {
        String cnpjFormatado = "";

        try {
            MaskFormatter maskFormatter = new MaskFormatter(mascara);
            maskFormatter.setValueContainsLiteralCharacters(false);
            cnpjFormatado = maskFormatter.valueToString(pad(String.valueOf(CNPJ)));
        } catch (ParseException e) {
            System.out.println("Um erro ocorreu ao tentar formatar o CNPJ. Encerrando o programa...");
            logger.severe(e.getMessage());
            System.exit(0);
        }

        return cnpjFormatado;
    }

    public long getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(long CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

}
