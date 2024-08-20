package com.ffutagawa.cursos.spring_rest.resource.rest;

import com.ffutagawa.cursos.spring_rest.domain.Videoaula;
import com.ffutagawa.cursos.spring_rest.service.VideoAulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(
        value = "rest/cursos/{idCurso}/videoaulas",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class VideoAulaRestController {
    //rest/cursos/1/videoaulas/2 -- GET Buscar Video aula por id

    @Autowired
    private VideoAulaService service;

    @GetMapping("/{idVideoaula}")
    @ResponseStatus(HttpStatus.OK)
    public Videoaula getVideoaula(@PathVariable("idCurso") Long idCurso,
                                  @PathVariable("idVideoaula") Long idVideoaula) {

        return service.findByIdVideoAulaAndIdCurso(idVideoaula, idCurso);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Videoaula> lista (@PathVariable("idCurso") Long idCurso,
                                  @RequestParam(name = "fields", required = false,
                                 defaultValue = "") String  fields){

        return service.findAllByCurso(idCurso, fields);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> salvar (@PathVariable("idCurso") Long idCurso, @RequestBody Videoaula videoaula){
        service.save(idCurso, videoaula);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(videoaula.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping ("/{idVideoaula}")
    @ResponseStatus(HttpStatus.OK)
    public Videoaula editar (@PathVariable("idCurso") Long idCurso,
                             @PathVariable("idVideoaula") Long idvideoaula,
                             @RequestBody Videoaula videoaula){
        service.update(idvideoaula, idCurso, videoaula);
        return videoaula;
    }

    @DeleteMapping("/{idVideoaula}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir (@PathVariable("idCurso") Long idCurso,
                         @PathVariable("idVideoaula") Long idvideoaula,
                         @RequestBody Videoaula videoaula){
        service.delete(idvideoaula, idCurso);
    }

}








































