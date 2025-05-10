package com.example.cursos_online.jpa.service;

import com.example.cursos_online.jpa.model.Instrutor;
import com.example.cursos_online.jpa.repository.InstrutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrutorService {
    @Autowired
    private InstrutorRepository repository;

    public Instrutor salvar(Instrutor instrutor) {
        return repository.save(instrutor);
    }

    public List<Instrutor> listarTodos() {
        return repository.findAll();
    }

    public Instrutor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instrutor não encontrado"));
    }

    public void atualizar(Instrutor instrutor) {
        repository.save(instrutor);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
