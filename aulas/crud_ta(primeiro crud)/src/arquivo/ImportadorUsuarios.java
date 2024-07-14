package arquivo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.StringTokenizer;

import usuario.Usuario;

public class ImportadorUsuarios {

	private String nomeArquivo = "C:\\Users\\IFPR\\eclipse-workspace\\crud_ta\\src\\usuarios_novo.csv";
	
	public Hashtable<Integer, Usuario> importar()
	{
		Hashtable<Integer, Usuario> usuarios = new Hashtable<>();
		BufferedReader reader = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		try {
			reader = new BufferedReader(new FileReader(nomeArquivo));
			String line = "";

			while ( (line = reader.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, ",");
				int id = Integer.parseInt(st.nextToken());
				String nome = st.nextToken();
				String email = st.nextToken();
				String dtNascimento = st.nextToken();
				
				Usuario user = new Usuario();
				user.setId(id);
				user.setNome(nome);
				user.setEmail(email);
				user.setDataNascimento(LocalDate.parse(dtNascimento, dtf));
				
				usuarios.put(user.getId(), user);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return usuarios;
	}
	
}
