package com.ffutagawa.cursos.spring_rest.dao;

import com.ffutagawa.cursos.spring_rest.domain.Videoaula;

import java.util.List;

public interface VideoAulaDao {

    void save (Videoaula videoAula);

    void update (Videoaula videoAula);

    void delete (Videoaula videoAula);

    Videoaula findByIdVideoAulaAndIdCurso(Long idVideoaula, Long idCurso);

    List<Videoaula> findAllByCurso(Long idCurso, String fields);
}
