package com.example.cursos_online.jpa.service;

import com.example.cursos_online.jpa.model.Curso;
import com.example.cursos_online.jpa.repository.CursoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private static final Logger logger = LoggerFactory.getLogger(CursoService.class);

    private final CursoRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public CursoService(CursoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Curso salvar(Curso curso) {
        logger.info("Salvando curso: {}", curso.getTitulo());
        try {
            return repository.save(curso);
        } catch (Exception e) {
            logger.error("Erro ao salvar curso", e);
            throw e; // rollback automático em exceções
        }
    }

    public List<Curso> listarTodos() {
        return repository.findAll();
    }

    public Optional<Curso> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Curso atualizar(Long id, Curso curso) {
        logger.info("Atualizando curso com id {}", id);
        Curso existente = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        existente.setTitulo(curso.getTitulo());
        existente.setDuracaoHoras(curso.getDuracaoHoras());
        existente.setInstrutor(curso.getInstrutor());

        return repository.save(existente);
    }

    @Transactional
    public void deletar(Long id) {
        logger.warn("Deletando curso com id {}", id);
        repository.deleteById(id);
    }
}
