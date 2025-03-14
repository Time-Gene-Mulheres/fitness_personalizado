package com.generation.fitness_personalizado.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.fitness_personalizado.model.Usuario;
import com.generation.fitness_personalizado.model.UsuarioImc;
import com.generation.fitness_personalizado.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Optional<UsuarioImc> calcularImc(Long id) {
		
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        
        if (usuario.isEmpty()) {
            return Optional.empty();
        }
        
        double peso = usuario.get().getPeso();
        double altura = usuario.get().getAltura();
        double imc = peso / (altura * altura);
        
        UsuarioImc usuarioImc = new UsuarioImc();
        usuarioImc.setId(usuario.get().getId());
        usuarioImc.setNome(usuario.get().getNome());
        usuarioImc.setUsuario(usuario.get().getUsuario());
        usuarioImc.setSenha("");
        usuarioImc.setFoto(usuario.get().getFoto());
        usuarioImc.setAltura(usuario.get().getAltura());
        usuarioImc.setPeso(usuario.get().getPeso());
        usuarioImc.setImc(imc);
        
        return Optional.of(usuarioImc);
    }	

	
}
