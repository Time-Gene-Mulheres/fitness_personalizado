package com.generation.fitness_personalizado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.fitness_personalizado.model.Usuario;
import com.generation.fitness_personalizado.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

    public double calcularIMC(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

        double peso = usuario.getPeso();
        double altura = usuario.getAltura();

        if (altura <= 0) {
            throw new IllegalArgumentException("A altura deve ser maior que zero.");
        }

        return peso / (altura * altura);
    }

	
}
