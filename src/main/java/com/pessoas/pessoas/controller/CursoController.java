package com.pessoas.pessoas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.pessoas.pessoas.model.Curso;
import com.pessoas.pessoas.service.CursoService;

@CrossOrigin
@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public List<Curso> listar() {
        return cursoService.listarCurso();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscar(@PathVariable Long id) {
        Curso curso = cursoService.buscarCurso(id);
        return ResponseEntity.ok().body(curso);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Curso adicionar(@Validated @RequestBody Curso curso) {
        return cursoService.adicionarCurso(curso);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(@PathVariable Long id, @Validated @RequestBody Curso curso) {
        Curso cursoAtualizado = cursoService.atualizarCurso(id, curso);
        return ResponseEntity.ok(cursoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        cursoService.excluirCurso(id);
        return ResponseEntity.noContent().build();
    }

}
