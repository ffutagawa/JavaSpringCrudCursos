package com.ffutagawa.cursos.spring_rest.resource.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
        value = "rest/cursos/{idCurso}/videoaulas",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class VideoAulaRestController {
    //rest/cursos/1/videoaulas/2 -- GET Buscar Video aula por id
}
