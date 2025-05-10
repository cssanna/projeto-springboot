package com.example.cursos_online.jpa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O título é obrigatório")
    @Column(nullable = false)
    private String titulo;

    @NotNull(message = "A duração é obrigatória")
    @Positive(message = "A duração deve ser positiva")
    @Column(nullable = false)
    private Double duracaoHoras;

    @NotNull(message = "O instrutor é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instrutor_id", nullable = false)
    private Instrutor instrutor;

    public Curso() {}
    public Curso(String titulo, Double duracaoHoras, Instrutor instrutor) {
        this.titulo = titulo;
        this.duracaoHoras = duracaoHoras;
        this.instrutor = instrutor;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Double getDuracaoHoras() { return duracaoHoras; }
    public void setDuracaoHoras(Double duracaoHoras) { this.duracaoHoras = duracaoHoras; }

    public Instrutor getInstrutor() { return instrutor; }
    public void setInstrutor(Instrutor instrutor) { this.instrutor = instrutor; }
}
