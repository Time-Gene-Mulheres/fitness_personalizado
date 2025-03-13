package com.generation.fitness_personalizado.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.fitness_personalizado.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public List<Usuario> findAllByUsuarioContainingIgnoreCase(@Param("usuario") String usuario);
	
}