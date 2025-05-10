package com.example.cursos_online.jpa.controller;

import com.example.cursos_online.jpa.model.Curso;
import com.example.cursos_online.jpa.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public Curso criarCurso(@RequestBody @Valid Curso curso) {
        return cursoService.salvar(curso);
    }

    @GetMapping
    public List<Curso> listarCursos() {
        return cursoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Curso buscarPorId(@PathVariable Long id) {
        return cursoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }

    @PutMapping("/{id}")
    public Curso atualizarCurso(@PathVariable Long id, @RequestBody @Valid Curso curso) {
        return cursoService.atualizar(id, curso);
    }

    @DeleteMapping("/{id}")
    public void deletarCurso(@PathVariable Long id) {
        cursoService.deletar(id);
    }
}
