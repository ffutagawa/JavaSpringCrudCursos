package com.ffutagawa.cursos.spring_rest.service;

import com.ffutagawa.cursos.spring_rest.domain.Curso;

import java.util.Date;
import java.util.List;

public interface CursoService {

    void save(Curso curso);

    void update(Long id, Curso curso);

    void delete (Long id);

    Curso findById (Long id);

    List<Curso> findAll();

    Curso updateDataInicio(Long id, Date dataInicio);

    List<Curso> findAllSemVideoAulas();
}
