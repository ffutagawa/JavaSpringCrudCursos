package com.ffutagawa.cursos.spring_rest.dao;

import com.ffutagawa.cursos.spring_rest.domain.Curso;
import com.ffutagawa.cursos.spring_rest.exception.NaoExisteDaoException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
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
        try {
            entityManager.remove(entityManager.getReference(Curso.class, id));
        } catch (EntityNotFoundException ex) {
            throw new NaoExisteDaoException("Curso não encontrado para id = " + id + ".");
        }
    }

    @Override
    public Curso findById(Long id) {
        Curso curso = entityManager.find(Curso.class, id);
        if (curso == null){
            throw new NaoExisteDaoException("Curso não encontrado para id: " + id);
        }
        return curso;
    }

    @Override
    public List<Curso> findAll() {
        return entityManager.createQuery("select c from Curso c", Curso.class).getResultList();
    }
}































