package org.crud.livro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;

public class GerenciarLivro {

    private final EntityManager entityManager;

    public GerenciarLivro(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void create(Livro livro) {
        entityManager.getTransaction().begin();
        entityManager.persist(livro);
        entityManager.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public List<Livro> list() {
        String hqlList = "from Livro";
        Query queryList = entityManager.createQuery(hqlList);
        return queryList.getResultList();
    }

    public Livro findById(long id) {
        return entityManager.find(Livro.class, id);
    }

    public Livro findByIsbn(String isbn) {
        String hqlFindIsbn = "from Livro l where l.isbn = :isbn";
        Query queryFind = entityManager.createQuery(hqlFindIsbn).setParameter("isbn", isbn);

        return (Livro) queryFind.getResultList().getFirst();
    }

    @SuppressWarnings("unchecked")
    public List<Livro> searchByTitle(String titulo) {
        String hqlSearch = "from Livro l where l.titulo like :titulo";
        Query querySearch = entityManager.createQuery(hqlSearch).setParameter("titulo", "%" + titulo + "%");

        return querySearch.getResultList();
    }

    public void update(Livro livro) {
        entityManager.getTransaction().begin();
        entityManager.merge(livro);
        entityManager.getTransaction().commit();
    }

    public void delete(Livro livro) {
        entityManager.getTransaction().begin();
        entityManager.remove(livro);
        entityManager.getTransaction().commit();
    }

}
