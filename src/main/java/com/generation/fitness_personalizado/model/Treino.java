package com.generation.fitness_personalizado.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_treinos")

public class Treino {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo nome é obrigatorio")
	private String nome;
	
	@NotBlank(message = "O atributo descrição é obrigatorio")
	@Size(min = 5, message = " A descrição tem que ser maior que 5 caracteres")
	private String descricao;
	
	@NotNull(message = "O atributo duração é obrigatorio")
	@Min(5)
	private int duracao;
	
	//pensar em como restringir (Leve/Moderado/Intenso)
	@NotBlank(message = "O atributo intensidade é obrigatorio")
	@Size(min = 4, message = " A intensidade tem que ser maior que 4 caracteres")
	private String intensidade;
	
	@ManyToOne
	@JsonIgnoreProperties("treino")
	private Usuario usuario;
	
	@ManyToOne
	@JsonIgnoreProperties("treino")
	private Categoria categoria;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public String getIntensidade() {
		return intensidade;
	}

	public void setIntensidade(String intensidade) {
		this.intensidade = intensidade;
	}
	
	
	
}
