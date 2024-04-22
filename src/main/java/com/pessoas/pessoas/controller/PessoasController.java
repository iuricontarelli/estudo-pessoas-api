package com.pessoas.pessoas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.bind.annotation.RestController;

import com.pessoas.pessoas.model.Pessoas;
import com.pessoas.pessoas.service.PessoasService;

// Anotação para permitir requisições de diferentes origens (CORS).
@CrossOrigin
// Anotação que marca a classe como um controlador REST, que responde a requisições HTTP.
@RestController
// Define o caminho base para todas as requisições ao controlador.
@RequestMapping("/pessoas")
public class PessoasController {

    // Injeta automaticamente uma instância de PessoasService.
    @Autowired
    private PessoasService pessoasService;

    // Método HTTP GET para listar todas as pessoas. Mapeia requisições GET para /pessoas.
    @GetMapping
    public List<Pessoas> listar(){
        return pessoasService.listarPessoas();
    }

    // Método HTTP GET para buscar uma pessoa pelo ID. Mapeia requisições GET para /pessoas/{id}.
    @GetMapping("/{id}")
    public ResponseEntity<Pessoas> buscar(@PathVariable Long id) {
        Pessoas pessoas = pessoasService.buscarPessoas(id);
        return ResponseEntity.ok().body(pessoas);
    }

    // Método HTTP POST para adicionar uma nova pessoa. Mapeia requisições POST para /pessoas.
    // Valida o corpo da requisição conforme as anotações na classe Pessoas.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoas adicionar(@Validated @RequestBody Pessoas pessoas) {
        return pessoasService.adicionarPessoas(pessoas);
    }

    // Método HTTP PUT para atualizar uma pessoa existente pelo ID. Mapeia requisições PUT para /pessoas/{id}.
    // Valida o corpo da requisição.
    @PutMapping("/{id}")
    public ResponseEntity<Pessoas> atualizar(@PathVariable Long id, @Validated @RequestBody Pessoas pessoas) {
        Pessoas pessoasAtualizada = pessoasService.atualizarPessoas(id, pessoas);
        // Retorna a pessoa atualizada com o status OK.
        return ResponseEntity.ok(pessoasAtualizada);
    }

    // Método HTTP DELETE para excluir uma pessoa pelo ID. Mapeia requisições DELETE para /pessoas/{id}.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        pessoasService.excluirPessoas(id);
        // Retorna uma resposta sem conteúdo, indicando sucesso na exclusão.
        return ResponseEntity.noContent().build();
    }

}
