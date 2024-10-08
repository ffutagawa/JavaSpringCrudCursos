package com.ffutagawa.cursos.spring_rest.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "videoaulas")
public class Videoaula implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String titulo;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(nullable = false)
    private Integer numero;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties({"videoaulas"})
    @ManyToOne
    @JoinColumn(name = "id_curso_fk")
    private Curso curso;

    public Videoaula() {
    }

    public Videoaula(Long id, String titulo, String descricao, Integer numero) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Videoaula videoAula = (Videoaula) o;
        return Objects.equals(id, videoAula.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Videoaula{" +
                "id=" + id +
                '}';
    }
}
