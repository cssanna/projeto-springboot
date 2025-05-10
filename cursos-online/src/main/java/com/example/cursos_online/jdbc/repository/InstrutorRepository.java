package com.example.cursos_online.jdbc.repository;

import com.example.cursos_online.jdbc.model.Instrutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InstrutorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Instrutor> findByFiltros(String nome, String email) {
        StringBuilder sql = new StringBuilder("SELECT id, nome, email FROM instrutor WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (nome != null && !nome.isEmpty()) {
            sql.append(" AND nome LIKE ?");
            params.add("%" + nome + "%");
        }
        if (email != null) {
            sql.append(" AND email = ?");
            params.add(email);
        }
        return jdbcTemplate.query(sql.toString(), params.toArray(), this::mapRowToInstrutor);
    }

    public void save(Instrutor instrutor) {
        String sql = "INSERT INTO instrutor (nome, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, instrutor.getNome(), instrutor.getEmail());
    }

    public List<Instrutor> findAll() {
        String sql = "SELECT id, nome, email FROM instrutor";
        return jdbcTemplate.query(sql, this::mapRowToInstrutor);
    }

    public Instrutor findById(Long id) {
        String sql = "SELECT id, nome, email FROM instrutor WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToInstrutor, id);
    }

    public void update(Instrutor instrutor) {
        String sql = "UPDATE instrutor SET nome = ?, email = ? WHERE id = ?";
        jdbcTemplate.update(sql, instrutor.getNome(), instrutor.getEmail());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM instrutor WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private Instrutor mapRowToInstrutor(ResultSet rs, int rowNum) throws SQLException {
        return new Instrutor(
                rs.getLong("id"),
                rs.getString("nome"),
                rs.getString("email")
        );
    }
}
