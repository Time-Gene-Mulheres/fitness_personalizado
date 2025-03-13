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

import com.generation.fitness_personalizado.model.Treino;
import com.generation.fitness_personalizado.repository.CategoriaRepository;
import com.generation.fitness_personalizado.repository.TreinoRepository;

import jakarta.validation.Valid;

@RestController

@RequestMapping("/treinos")

@CrossOrigin (allowedHeaders = "*", origins = "*")

public class TreinoController {
	
	@Autowired	
	private TreinoRepository treinoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	//Buscar todos treinos
	@GetMapping	
	public ResponseEntity<List<Treino>> getAll(){
		return ResponseEntity.ok(treinoRepository.findAll());
	}
	
	//Buscar treino por id
	@GetMapping("/{id}")
	public ResponseEntity<Treino> getById(@PathVariable Long id){
		return treinoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	//Buscar treinos por nome
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Treino>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(treinoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	//Cadastrar treino
    @PostMapping
    public ResponseEntity<Treino> post(@Valid @RequestBody Treino treino){
        if(categoriaRepository.existsById(treino.getCategoria().getId()))
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(treinoRepository.save(treino));
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!", null);
    }
	
	//Atualizar treino
	@PutMapping
	public ResponseEntity<Treino> put(@Valid @RequestBody Treino treino){
		if(treinoRepository.existsById(treino.getId())) {
			if(treinoRepository.existsById(treino.getCategoria().getId()))
				return ResponseEntity.status(HttpStatus.OK)
						.body(treinoRepository.save(treino));
			
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria não existe!", null);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	//Deletar treino	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Treino> treino = treinoRepository.findById(id);
		
		if(treino.isEmpty()) 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		treinoRepository.deleteById(id);	
	}
	
}
