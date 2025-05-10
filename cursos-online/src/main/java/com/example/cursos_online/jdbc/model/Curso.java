package com.example.cursos_online.jdbc.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Curso {
    private Long id;

    @NotNull(message = "O título é obrigatório")
    private String titulo;

    @NotNull(message = "A duração é obrigatória")
    @Positive(message = "A duração deve ser positiva")
    private Double duracaoHoras;

    private Long instrutorId;

    // Construtores
    public Curso() {}
    public Curso(Long id, String titulo, Double duracaoHoras, Long instrutorId) {
        this.id = id;
        this.titulo = titulo;
        this.duracaoHoras = duracaoHoras;
        this.instrutorId = instrutorId;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public Double getDuracaoHoras() { return duracaoHoras; }
    public void setDuracaoHoras(Double duracaoHoras) { this.duracaoHoras = duracaoHoras; }
    public Long getInstrutorId() { return instrutorId; }
    public void setInstrutorId(Long instrutorId) { this.instrutorId = instrutorId; }
}
