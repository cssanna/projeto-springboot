package com.example.cursos_online.jdbc.service;

import com.example.cursos_online.jdbc.model.Instrutor;
import com.example.cursos_online.jdbc.repository.InstrutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InstrutorService {
    @Autowired
    private InstrutorRepository repository;

    public void salvar(Instrutor instrutor) {
        repository.save(instrutor);
    }

    public List<Instrutor> listarTodos() {
        return repository.findAll();
    }

    public Instrutor buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void atualizar(Instrutor instrutor) {
        repository.update(instrutor);
    }

    public void deletar(Long id) {
        repository.delete(id);
    }
}
