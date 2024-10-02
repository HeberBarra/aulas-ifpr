package usuario;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class GerenciarUsuario {

    private final EntityManager entityManager;

    public GerenciarUsuario(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Usuario usuario) {
        entityManager.getTransaction().begin();
        entityManager.persist(usuario);
        entityManager.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> list() {
        String hqlList = "from Usuario";
        Query queryList = entityManager.createQuery(hqlList);
        return queryList.getResultList();
    }

    public Usuario findById(long id) {
        return entityManager.find(Usuario.class, id);
    }

    public Usuario findByCpf(long cpf) {
        String hqlFindIsbn = "from Usuario l where l.cpf = :cpf";
        Query queryFind = entityManager.createQuery(hqlFindIsbn).setParameter("cpf", cpf);

        return (Usuario) queryFind.getResultList().getFirst();
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> searchByName(String nome) {
        String hqlSearch = "from Usuario l where l.nome like :nome";
        Query querySearch = entityManager.createQuery(hqlSearch).setParameter("nome", "%" + nome + "%");

        return querySearch.getResultList();
    }

    public void update(Usuario usuario) {
        entityManager.getTransaction().begin();
        entityManager.merge(usuario);
        entityManager.getTransaction().commit();
    }

    public void delete(Usuario usuario) {
        entityManager.getTransaction().begin();
        entityManager.remove(usuario);
        entityManager.getTransaction().commit();
    }

    public void fecharConexao() {
        entityManager.close();
    }

}
