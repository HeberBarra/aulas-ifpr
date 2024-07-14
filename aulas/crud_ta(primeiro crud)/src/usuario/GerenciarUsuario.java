package usuario;

import arquivo.ImportadorUsuarios;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GerenciarUsuario {

	private AtomicInteger geradorId;
	private Hashtable<Integer, Usuario> usuarioBD;
	private ImportadorUsuarios importadorUsuarios;
	
	public GerenciarUsuario() {
		importadorUsuarios = new ImportadorUsuarios();
		geradorId = new AtomicInteger();
		usuarioBD = importadorUsuarios.importar();
	}
	
	public void create(Usuario usuario) {
		int novoId = geradorId.incrementAndGet();
		usuario.setId(novoId);
		usuarioBD.put(novoId, usuario);
	}
	
	public void update(Usuario usuario) {
		usuarioBD.replace(usuario.getId(), usuario);
	}
	
	public List<Usuario> list() {
		List<Usuario> listaUsuarios = new ArrayList<>(usuarioBD.values());
		return listaUsuarios;
	}
	
	public void remove(int id) {
		usuarioBD.remove(id);
	}
	
	public Usuario findById(int id) {
		return usuarioBD.get(id);
	}

	public List<Usuario> listarPorNome(String nome) {
		List<Usuario> usuarios = new ArrayList<>();

		for (Usuario usuario: this.list()) {
			if (usuario.getNome().toLowerCase().contains(nome.toLowerCase())) {
				usuarios.add(usuario);
			}
		}

		return usuarios;
	}
	
}
