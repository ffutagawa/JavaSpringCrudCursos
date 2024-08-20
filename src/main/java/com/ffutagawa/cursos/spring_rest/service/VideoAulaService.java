package com.ffutagawa.cursos.spring_rest.service;

import com.ffutagawa.cursos.spring_rest.domain.Videoaula;

import java.util.List;

public interface VideoAulaService {

    void save (Long idCurso, Videoaula videoAula);

    void update (Long idVideoaula, Long idCurso, Videoaula videoAula);

    void delete (Long idVideoaula, Long idCurso);

    Videoaula findByIdVideoAulaAndIdCurso(Long idVideoaula, Long idCurso);

    List<Videoaula> findAllByCurso(Long idCurso, String fields);

}


























