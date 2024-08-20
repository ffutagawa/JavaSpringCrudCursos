package com.ffutagawa.cursos.spring_rest.dao;

import com.ffutagawa.cursos.spring_rest.domain.VideoAula;

import java.util.List;

public interface VideoAulaDao {

    void save (VideoAula videoAula);

    void update (VideoAula videoAula);

    void delete (VideoAula videoAula);

    VideoAula findByIdVideoAulaAndIdCurso(Long idVideoaula, Long idCurso);

    List<VideoAula> findAllByCurso(Long idCurso, String fields);
}
