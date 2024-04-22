package com.pessoas.pessoas.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pessoas.pessoas.exception.NotFoundException;
import com.pessoas.pessoas.model.Pessoas;
import com.pessoas.pessoas.repository.PessoasRepository;
import com.pessoas.pessoas.service.PessoasService;

// Anotação que marca a classe como um componente de serviço Spring.
@Service
public class PessoasServiceImpl implements PessoasService{

    // Injeção de dependência para o repositório de pessoas.
    @Autowired
    private PessoasRepository pessoasRepository;

    // Método que retorna uma lista de todas as pessoas.
    @Override
    public List<Pessoas> listarPessoas() {
        return pessoasRepository.findAll();
    }

    // Método que busca uma pessoa pelo ID.
    @Override
    public Pessoas buscarPessoas(Long id) {
        // Optional<Pessoas> optionalPessoas = pessoasRepository.findById(id);
        // if (optionalPessoas.isEmpty()) {
        //     throw new NotFoundException("Pessoa não encontrada.");
        // }
        // return optionalPessoas.get();

        // De forma reduzida:
        return pessoasRepository.findById(id).orElseThrow(() -> new NotFoundException("Pessoa não encontrada."));
    }

    // Método que adiciona uma nova pessoa, verificando se já existe uma pessoa com o mesmo nome e sobrenome.
    @Override
    public Pessoas adicionarPessoas(Pessoas pessoas) {
        Optional<Pessoas> pessoasExistentes = pessoasRepository.findByNomeAndSobrenome(pessoas.getNome(), pessoas.getSobrenome());
        if (pessoasExistentes.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma pessoa igual.");
        }
        // Assegura que o ID é nulo para criar um novo registro.
        pessoas.setId(null);
        pessoas.setMatricula(gerarMatricula());
        return pessoasRepository.save(pessoas);
    }

    private long gerarMatricula() {
        // Gera um UUID único e o converte para string.
        // return UUID.randomUUID().toString();

        // Gera um numero aleatorio
        // Math.random();

        long timePart = System.currentTimeMillis(); // Obtém o tempo atual em milissegundos.
        int randomPart = ThreadLocalRandom.current().nextInt(1000, 10000); // Gera um número aleatório entre 1000 e 9999.
        return timePart + randomPart; // Combina as partes para formar a matrícula.
    }

    // Método que atualiza os dados de uma pessoa existente.
    @Override
    public Pessoas atualizarPessoas(Long id, Pessoas pessoas) {
        // Caso tivesse diversas variaveis, o código ficaria gigante (não é a melhor forma de se fazer)
        // return pessoasRepository.findById(id).map(pessoasExistentes -> {
        //     pessoasExistentes.setNome(pessoas.getNome());
        //     pessoasExistentes.setSobrenome(pessoas.getSobrenome());
        //     pessoasExistentes.setIdade(pessoas.getIdade());
        //     return pessoasRepository.save(pessoasExistentes);
        // }).orElseThrow(() -> new NotFoundException("Oportunidade não encontrada para atualização."));
            
        // Utilizando o Bean Utils (o que diminui linhas de códigos)
        Pessoas pessoasDB = buscarPessoas(id); // Busca a pessoa para garantir que ela existe.
        BeanUtils.copyProperties(pessoas, pessoasDB, "id", "matricula"); // Copia propriedades, excluindo "id" e "matricula".
        return pessoasRepository.save(pessoasDB);
    }

    // Método que exclui uma pessoa pelo ID.
    @Override
    public void excluirPessoas(Long id) {
        // Pessoas pessoas = pessoasRepository.findById(id).orElseThrow(() -> new NotFoundException("Oportunidade não encontrada para exclusão."));
        // pessoasRepository.delete(pessoas);

        // Uma melhor abordagem (mais simples e "clara")
        Pessoas pessoas = buscarPessoas(id); // Busca a pessoa para garantir que ela existe.
        pessoasRepository.delete(pessoas);
    }

}
