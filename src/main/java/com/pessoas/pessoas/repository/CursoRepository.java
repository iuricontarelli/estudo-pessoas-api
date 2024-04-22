package com.pessoas.pessoas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pessoas.pessoas.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findByNomeAndDescricao(String nome, String descricao);

}
