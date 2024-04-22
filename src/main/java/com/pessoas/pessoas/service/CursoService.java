package com.pessoas.pessoas.service;

import java.util.List;

import com.pessoas.pessoas.model.Curso;

public interface CursoService {

    List<Curso> listarCurso();

    Curso buscarCurso(Long id);

    Curso adicionarCurso(Curso curso);

    Curso atualizarCurso(Long id, Curso curso);

    void excluirCurso(Long id);

}
