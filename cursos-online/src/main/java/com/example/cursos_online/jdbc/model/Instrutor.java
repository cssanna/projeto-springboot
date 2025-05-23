package com.example.cursos_online.jdbc.model;

import jakarta.validation.constraints.NotNull;

public class Instrutor {
    private Long id;

    @NotNull(message = "O nome é obrigatório")
    private String nome;

    @NotNull(message = "O e-mail é obrigatório")
    private String email;

    // Construtores
    public Instrutor() {}
    public Instrutor(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
