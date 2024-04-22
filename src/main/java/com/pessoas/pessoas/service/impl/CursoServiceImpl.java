package com.pessoas.pessoas.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.pessoas.pessoas.exception.NotFoundException;
import com.pessoas.pessoas.model.Curso;
import com.pessoas.pessoas.repository.CursoRepository;
import com.pessoas.pessoas.service.CursoService;

@Service
public class CursoServiceImpl implements CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    public List<Curso> listarCurso() {
        return cursoRepository.findAll();
    }

    @Override
    public Curso buscarCurso(Long id) {
        return cursoRepository.findById(id).orElseThrow(() -> new NotFoundException("Curso nao encontrado."));
    }

    @Override
    public Curso adicionarCurso(Curso curso) {
        Optional<Curso> cursoExistente = cursoRepository.findByNomeAndDescricao(curso.getNome(), curso.getDescricao());

        if (cursoExistente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ja existe um curso igual");
        }

        curso.setId(null);
        return cursoRepository.save(curso);
    }

    @Override
    public Curso atualizarCurso(Long id, Curso curso) {
        Curso cursoDB = buscarCurso(id);

        BeanUtils.copyProperties(curso, cursoDB, "id");

        return cursoRepository.save(cursoDB);
    }

    @Override
    public void excluirCurso(Long id) {
        Curso curso = buscarCurso(id);

        cursoRepository.delete(curso);
    }

}
