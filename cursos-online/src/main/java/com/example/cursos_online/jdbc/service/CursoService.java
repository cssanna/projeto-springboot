package com.example.cursos_online.jdbc.service;

import com.example.cursos_online.jdbc.model.Curso;
import com.example.cursos_online.jdbc.repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class CursoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CursoRepository repository;

    @Transactional(rollbackOn = Exception.class)
    public void criarCursoComLog(Curso curso) throws Exception {
        if (curso.getDuracaoHoras() < 0) {
            throw new Exception("Duração do curso não pode ser negativa.");
        }

        // Salva o curso no banco
        repository.save(curso);

        // Recupera o ID do curso salvo (assumindo que o repositório atualiza o ID)
        Long cursoId = curso.getId();

        // Insere o log
        String sqlLog = "INSERT INTO log_curso (curso_id, titulo, data_criacao) VALUES (?, ?, ?)";
        jdbcTemplate.update(sqlLog, cursoId, curso.getTitulo(), LocalDate.now());
    }

    public void salvar(Curso curso) {
        repository.save(curso);
    }

    public List<Curso> listarTodos() {
        return repository.findAll();
    }

    public Curso buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void atualizar(Curso curso) {
        repository.update(curso);
    }

    public void deletar(Long id) {
        repository.delete(id);
    }
}
