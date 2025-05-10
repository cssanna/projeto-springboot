package com.example.cursos_online.jdbc.controller;

import com.example.cursos_online.jdbc.model.Instrutor;
import com.example.cursos_online.jdbc.service.InstrutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/instrutores")
public class InstrutorController {
    @Autowired
    private InstrutorService service;

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid Instrutor instrutor) {
        service.salvar(instrutor);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Instrutor>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instrutor> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody @Valid Instrutor instrutor) {
        instrutor.setId(id);
        service.atualizar(instrutor);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok().build();
    }
}
