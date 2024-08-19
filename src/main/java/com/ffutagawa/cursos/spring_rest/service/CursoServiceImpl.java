package com.ffutagawa.cursos.spring_rest.service;

import com.ffutagawa.cursos.spring_rest.dao.CursoDao;
import com.ffutagawa.cursos.spring_rest.domain.Curso;
import com.ffutagawa.cursos.spring_rest.exception.IdNaoValidoServiceException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoDao dao;

    @Override
    public void save(Curso curso) {
        dao.save(curso);
        if (curso.getVideoaulas() != null){
            curso.getVideoaulas()
                    .parallelStream()
                    .forEach(curso::addVideoaula);
        }
    }

    @Override
    public void update(Long id, Curso curso) {
        curso.setId(idValido(id));
        dao.findById(id);
        dao.update(curso);
    }

    @Override
    public void delete(Long id) {
    dao.delete(idValido(id));
    }

    @Override
    public Curso findById(Long id) {
        return dao.findById(idValido(id));
    }

    @Override
    public List<Curso> findAll() {
        return dao.findAll();
    }

    @Override
    public Curso updateDataInicio(Long id, Date dataInicio) {
        Curso curso = dao.findById(idValido(id));
        curso.setDataInicio(dataInicio);
        return curso;
    }

    private Long idValido(Long id){
        if (id <= 0){
            throw new IdNaoValidoServiceException("Valor do campo ID esta invalido, dever ser um valor inteiro maior " +
                    "que zero");
        }
        return id;
    }


}
