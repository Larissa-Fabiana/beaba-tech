package com.example.crm.model;

import java.util.Objects;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "aluno")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Long id;
	
	@Column(nullable = false)
	@NotNull
	private String nome;
	
	@Column(nullable = false)
	@NotNull
	private int idade;
	
	@Column(nullable = false)
	@NotNull
	private int nota_semestre_1;
	
	@Column(nullable = false)
	@NotNull
	private int nota_semestre_2;
	
//	@Column(nullable = false)
//	@NotNull
//	private String nome_professor;
//
//	@Column(nullable = false)
//	@NotNull
//	private int numero_sala;

	public Long getId() {
		return id;
	}

//	public void setId(Long id) {
//		this.id = id;
//	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getNota_semestre_1() {
		return nota_semestre_1;
	}

	public void setNota_semestre_1(int nota_semestre_1) {
		this.nota_semestre_1 = nota_semestre_1;
	}

	public int getNota_semestre_2() {
		return nota_semestre_2;
	}

	public void setNota_semestre_2(int nota_semestre_2) {
		this.nota_semestre_2 = nota_semestre_2;
	}

//	public String getNome_professor() {
//		return nome_professor;
//	}
//
//	public void setNome_professor(String nome_professor) {
//		this.nome_professor = nome_professor;
//	}
//
//	public int getNumero_sala() {
//		return numero_sala;
//	}
//
//	public void setNumero_sala(int numero_sala) {
//		this.numero_sala = numero_sala;
//	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
