package com.generation.fitness_personalizado.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.fitness_personalizado.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
	
	public Optional<Categoria> findByDescricaoContainingIgnoreCase(String descricao);
	
}
