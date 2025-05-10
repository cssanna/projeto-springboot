package com.example.cursos_online.jdbc.repository;

import com.example.cursos_online.jdbc.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Batch insert de múltiplos cursos
    public void saveAll(List<Curso> cursos) {
        String sql = "INSERT INTO curso (titulo, duracaoHoras, instrutorId) VALUES (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, cursos, cursos.size(), (ps, curso) -> {
            ps.setString(1, curso.getTitulo());
            ps.setDouble(2, curso.getDuracaoHoras());
            ps.setLong(3, curso.getInstrutorId());
        });
    }

    // Consulta com JOIN por instrutor
    public List<Curso> findCursosPorInstrutorComJoin(Long instrutorId) {
        String sql = """
            SELECT c.id, c.titulo, c.duracaoHoras, c.instrutorId
            FROM curso c
            JOIN instrutor i ON c.instrutorId = i.id
            WHERE i.id = ?
        """;
        return jdbcTemplate.query(sql, this::mapRowToCurso, instrutorId);
    }

    // Filtros dinâmicos
    public List<Curso> findByFiltros(String titulo, Double duracaoMin, Double duracaoMax, Long instrutorId) {
        StringBuilder sql = new StringBuilder("SELECT id, titulo, duracaoHoras, instrutorId FROM curso WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (titulo != null && !titulo.isEmpty()) {
            sql.append(" AND titulo LIKE ?");
            params.add("%" + titulo + "%");
        }
        if (duracaoMin != null) {
            sql.append(" AND duracaoHoras >= ?");
            params.add(duracaoMin);
        }
        if (duracaoMax != null) {
            sql.append(" AND duracaoHoras <= ?");
            params.add(duracaoMax);
        }
        if (instrutorId != null) {
            sql.append(" AND instrutorId = ?");
            params.add(instrutorId);
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), this::mapRowToCurso);
    }

    // Inserção de um único curso
    public void save(Curso curso) {
        String sql = "INSERT INTO curso (titulo, duracaoHoras, instrutorId) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, curso.getTitulo(), curso.getDuracaoHoras(), curso.getInstrutorId());
    }

    public List<Curso> findAll() {
        String sql = "SELECT id, titulo, duracaoHoras, instrutorId FROM curso";
        return jdbcTemplate.query(sql, this::mapRowToCurso);
    }

    public Curso findById(Long id) {
        String sql = "SELECT id, titulo, duracaoHoras, instrutorId FROM curso WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToCurso, id);
    }

    public void update(Curso curso) {
        String sql = "UPDATE curso SET titulo = ?, duracaoHoras = ?, instrutorId = ? WHERE id = ?";
        jdbcTemplate.update(sql, curso.getTitulo(), curso.getDuracaoHoras(), curso.getInstrutorId(), curso.getId());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM curso WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // Conversão de linha do ResultSet para objeto Curso
    private Curso mapRowToCurso(ResultSet rs, int rowNum) throws SQLException {
        return new Curso(
                rs.getLong("id"),
                rs.getString("titulo"),
                rs.getDouble("duracaoHoras"),
                rs.getLong("instrutorId")
        );
    }
}
