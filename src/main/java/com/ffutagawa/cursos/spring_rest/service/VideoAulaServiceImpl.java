package com.ffutagawa.cursos.spring_rest.service;

import com.ffutagawa.cursos.spring_rest.dao.VideoAulaDao;
import com.ffutagawa.cursos.spring_rest.domain.VideoAula;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class VideoAulaServiceImpl implements VideoAulaService {


    @Autowired
    private VideoAulaDao dao;
    @Autowired
    private CursoService cursoService;

    @Override
    public void save(Long idCurso, VideoAula videoaula) {
        videoaula.setCurso(cursoService.findById(idCurso));
        dao.save(videoaula);
    }

    @Override
    public void update(Long idVideoaula, Long idCurso, VideoAula videoaula) {
        videoaula.setId(idVideoaula);
        videoaula.setCurso(findByIdVideoAulaAndIdCurso(idVideoaula, idCurso).getCurso());
        dao.update(videoaula);
    }

    @Override
    public void delete(Long idVideoaula, Long idCurso) {

        dao.delete(findByIdVideoAulaAndIdCurso(idVideoaula, idCurso));
    }

    @Override
    public VideoAula findByIdVideoAulaAndIdCurso(Long idVideoaula, Long idCurso) {
        return dao.findByIdVideoAulaAndIdCurso(idVideoaula, idCurso);
    }

    @Override
    public List<VideoAula> findAllByCurso(Long idCurso, String fields) {

        return dao.findAllByCurso(idCurso, fields);
    }
}

