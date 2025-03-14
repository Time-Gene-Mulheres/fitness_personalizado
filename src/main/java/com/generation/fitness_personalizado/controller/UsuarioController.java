package com.generation.fitness_personalizado.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.fitness_personalizado.model.Usuario;
import com.generation.fitness_personalizado.model.UsuarioImc;
import com.generation.fitness_personalizado.repository.UsuarioRepository;
import com.generation.fitness_personalizado.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping
    public ResponseEntity<List<Usuario>> getAll(){
        return ResponseEntity.ok(usuarioRepository.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id){
        return usuarioRepository.findById(id)
            .map(resposta -> ResponseEntity.ok(resposta))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<List <Usuario>> getByUsuario(@PathVariable 
    String usuario){
        return ResponseEntity.ok(usuarioRepository.findAllByUsuarioContainingIgnoreCase(usuario));
        		
        		 
    }

    @GetMapping("/imc/{id}")
    public ResponseEntity<Optional<UsuarioImc>> getImc(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.calcularImc(id));
    }	
    
    @PostMapping
    public ResponseEntity<Usuario> post(@Valid @RequestBody Usuario usuario){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioRepository.save(usuario));
    }
    
    @PutMapping
    public ResponseEntity<Usuario> put(@Valid @RequestBody Usuario usuario){
        return usuarioRepository.findById(usuario.getId())
            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
            .body(usuarioRepository.save(usuario)))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        
        if(usuario.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        
        usuarioRepository.deleteById(id);               
    }

    
}