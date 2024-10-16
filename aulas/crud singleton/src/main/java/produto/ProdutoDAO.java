package produto;

import dao.GenericDAO;
import jakarta.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class ProdutoDAO extends GenericDAO<Produto> {

	public ProdutoDAO() {
		super(Produto.class);
	}

	@SuppressWarnings("unchecked")
	public List<Produto> gerarRelatorioFabricacao(LocalDate dataInicio, LocalDate dataFinal) {
		String hql = "from Produto p where p.dataFabricacao between :dataInicio and :dataFim";
		Query query = entityManager.createQuery(hql);
		query.setParameter(":dataInicio", dataInicio);
		query.setParameter(":dataFim", dataFinal);

		return query.getResultList();
	}

}
