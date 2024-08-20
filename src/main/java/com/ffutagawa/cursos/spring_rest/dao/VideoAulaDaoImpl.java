package com.ffutagawa.cursos.spring_rest.dao;

import com.ffutagawa.cursos.spring_rest.domain.VideoAula;
import com.ffutagawa.cursos.spring_rest.exception.NaoExisteDaoException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VideoAulaDaoImpl implements VideoAulaDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(VideoAula videoAula) {
        entityManager.persist(videoAula);

    }

    @Override
    public void update(VideoAula videoAula) {
        entityManager.merge(videoAula);

    }

    @Override
    public void delete(VideoAula videoAula) {
        entityManager.remove(videoAula);

    }

    @Override
    public VideoAula findByIdVideoAulaAndIdCurso(Long idVideoaula, Long idCurso) {
        String query = "select v from Videoaula v where v.id = ?1 and v.curso.id = ?2";
        try {
            return entityManager
                    .createQuery(query, VideoAula.class)
                    .setParameter(1, idVideoaula)
                    .setParameter(2, idCurso)
                    .getSingleResult();
        }catch (NoResultException ex) {
            throw new NaoExisteDaoException("Videoaula id = " + idVideoaula + " não encontrado para Curso id " + idCurso);
        }

    }

    @Override
    public List<VideoAula> findAllByCurso(Long idCurso, String fields) {
        String select = fields.equals("curso")
                ? "select v"
                : "select new Videoaula(v.id, v.titulo, v.descricao, v.numero)";
        return entityManager
                .createQuery(select + "from Videoaula v where v.curso.id = ?1", VideoAula.class)
                .setParameter(1, idCurso)
                .getResultList();
    }
}
























