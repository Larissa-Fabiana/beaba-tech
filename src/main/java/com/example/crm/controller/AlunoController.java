package com.example.crm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.model.Aluno;
import com.example.crm.repository.AlunoRepository;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@GetMapping
	@Operation(summary = "Lista os alunos.",
		       description = "Retorna uma lista com todos os alunos.")
	public List<Aluno> listar() {
		return alunoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Lista um unico aluno pelo ID.",
		       description = "Retorna somente o aluno requisitado.")
	public Aluno visualizarAluno(@PathVariable("id") Long id) {
		return alunoRepository.findById(id).get();
	}
	
	@PostMapping("/adicionar")
	@Operation(summary = "Cria um novo aluno.")
	@ResponseStatus(HttpStatus.CREATED)
	public Aluno adicionar(@RequestBody Aluno aluno) {
		return alunoRepository.save(aluno);
	}
	
	@DeleteMapping("/remover/{id}")
	@Operation(summary = "Exclui um aluno.",
    description = "Deleta somente o aluno requisitado pelo ID.")
	public ResponseEntity<Aluno> deletar(@PathVariable("id") Long id) {
		
		Optional<Aluno> alunoOptional = alunoRepository.findById(id);
		
		if(alunoOptional.isPresent()) {
			alunoRepository.deleteById(id);
			return new ResponseEntity<>(alunoOptional.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@Operation(summary = "Atualiza um aluno",
		       description = "Atualiza um aluno existente. Caso tente alterar um aluno inesistente retorna status não encontrado.")
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Aluno> atualizarAluno(@PathVariable("id") Long id,
			@RequestBody Aluno novosDadosAluno) {
		
		Optional<Aluno> alunoOptional = alunoRepository.findById(id);
		
		 if(alunoOptional.isPresent()) {
			 Aluno antigoAluno = alunoOptional.get();
			 
			 antigoAluno.setNome(novosDadosAluno.getNome());
			 antigoAluno.setIdade(novosDadosAluno.getIdade());
			 antigoAluno.setNota_semestre_1(novosDadosAluno.getNota_semestre_1());
			 antigoAluno.setNota_semestre_2(novosDadosAluno.getNota_semestre_2());
//			 antigoAluno.setNome_professor(novosDadosAluno.getNome_professor());
//			 antigoAluno.setNumero_sala(novosDadosAluno.getNumero_sala());
			 
			 Aluno alunoAtualizado = alunoRepository.save(antigoAluno);
			 
			 return new ResponseEntity<>(alunoAtualizado, HttpStatus.OK);
		 }
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
