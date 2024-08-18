package com.ffutagawa.cursos.spring_rest.dao;

import com.ffutagawa.cursos.spring_rest.domain.Curso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CursoDaoImpl implements CursoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Curso curso) {
        entityManager.persist(curso);

    }

    @Override
    public void update(Curso curso) {
        entityManager.merge(curso);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.getReference(Curso.class, id));;
    }

    @Override
    public Curso findById(Long id) {
        return entityManager.find(Curso.class, id);
    }

    @Override
    public List<Curso> findAll() {
        return entityManager.createQuery("select c from Curso c", Curso.class).getResultList();
    }
}































