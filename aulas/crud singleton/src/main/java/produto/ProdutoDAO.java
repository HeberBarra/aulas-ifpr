package produto;

import java.util.List;

import conector.ConectorBanco;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class ProdutoDAO {

	private EntityManager entityManager;

	public ProdutoDAO() {
		entityManager = ConectorBanco.getInstance().createEntityManager();
	}

	public void create(Produto produto) {
		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();
	}

	public void update(Produto produto) {
		entityManager.getTransaction().begin();
		entityManager.merge(produto);
		entityManager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<Produto> list() {
		String hqlList = "from Produto";
		Query hql = entityManager.createQuery(hqlList);
		return hql.getResultList();
	}

	public void remove(int id) {
		Produto produto = findById(id);
		entityManager.getTransaction().begin();
		entityManager.remove(produto);
		entityManager.getTransaction().commit();
	}

	public Produto findById(int id) {
		return entityManager.find(Produto.class, id);
	}

	public void fecharConexao() {
		entityManager.close();;
	}

}
