package com.example.cursos_online.jpa.repository;

import com.example.cursos_online.jpa.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Consultas derivadas
    List<Curso> findByTituloContainingIgnoreCase(String titulo);

    List<Curso> findByDuracaoHorasBetween(Double min, Double max);

    List<Curso> findByInstrutorId(Long instrutorId);

    // Consulta JPQL personalizada
    @Query("SELECT c FROM Curso c WHERE c.instrutor.id = :instrutorId AND c.duracaoHoras >= :duracaoMin")
    List<Curso> buscarPorInstrutorEDuracaoMinima(Long instrutorId, Double duracaoMin);

    // JOIN FETCH para evitar problema N+1
    @Query("SELECT c FROM Curso c JOIN FETCH c.instrutor")
    List<Curso> buscarCursosComInstrutores();
}
