package com.generation.fitness_personalizado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.fitness_personalizado.model.Treino;

public interface TreinoRepository extends JpaRepository<Treino, Long>{

	public List<Treino> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
}
