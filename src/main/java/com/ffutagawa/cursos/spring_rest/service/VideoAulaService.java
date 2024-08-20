package com.ffutagawa.cursos.spring_rest.service;

import com.ffutagawa.cursos.spring_rest.domain.VideoAula;

import java.util.List;

public interface VideoAulaService {

    void save (Long idCurso, VideoAula videoAula);

    void update (Long idVideoaula, Long idCurso, VideoAula videoAula);

    void delete (Long idVideoaula, Long idCurso);

    VideoAula findByIdVideoAulaAndIdCurso(Long idVideoaula, Long idCurso);

    List<VideoAula> findAllByCurso(Long idCurso, String fields);

}


























