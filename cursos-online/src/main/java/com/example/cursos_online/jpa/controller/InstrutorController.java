package com.example.cursos_online.jpa.controller;

import com.example.cursos_online.jpa.model.Instrutor;
import com.example.cursos_online.jpa.service.InstrutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instrutores")
public class InstrutorController {

    @Autowired
    private InstrutorService instrutorService;

    @PostMapping
    public Instrutor criar(@RequestBody @Valid Instrutor instrutor) {
        return instrutorService.salvar(instrutor);
    }

    @GetMapping
    public List<Instrutor> listar() {
        return instrutorService.listarTodos();
    }

    @GetMapping("/{id}")
    public Instrutor buscarPorId(@PathVariable Long id) {
        return instrutorService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Instrutor atualizar(@PathVariable Long id, @RequestBody @Valid Instrutor instrutor) {
        instrutor.setId(id);
        return instrutorService.salvar(instrutor);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        instrutorService.deletar(id);
    }
}
