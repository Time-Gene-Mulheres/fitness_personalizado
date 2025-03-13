package com.generation.fitness_personalizado.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_categorias")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O campo \"Descrição\" é de preenchimento obrigatório!")
	@Size(min = 3, max = 25, message = "O campo \"Descrição\" é de preenchimento obrigatório!")
	private String descricao;
}
