package com.ffutagawa.cursos.spring_rest.resource.rest;

import com.ffutagawa.cursos.spring_rest.domain.Curso;
import com.ffutagawa.cursos.spring_rest.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.attribute.standard.MediaTray;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping (
        value = "rest/cursos",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CursoRestController {

    @Autowired
    private CursoService service;

    @GetMapping("/listartudo")
    @ResponseStatus(HttpStatus.OK)
    public List<Curso> listar(){
        return service.findAll();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Curso> listar(@RequestParam(name = "fields", required = false, defaultValue = "") String fields){
        return fields.equals("videoaulas")
                ? service.findAll()
                : service.findAllSemVideoAulas();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Curso> findbyid(@PathVariable Long id){
        Curso obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Curso curso){
        service.save(curso);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/id")
                .buildAndExpand(curso.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Curso editar(@PathVariable ("id") Long id, @RequestBody Curso curso){

        service.update(id, curso);
        return curso;

    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Curso editarDataInicio(@PathVariable ("id") Long id, @RequestBody Curso curso){

        return service.updateDataInicio(id, curso.getDataInicio());

    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){
        service.delete(id);

    }

}

































