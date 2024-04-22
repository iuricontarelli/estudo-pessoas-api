package com.pessoas.pessoas.model;

import java.io.Serializable;

import jakarta.persistence.*;
// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Table;

// Anotação que indica que esta classe é uma entidade JPA.
@Entity
@Table(name = "PESSOAS")
public class Pessoas implements Serializable{

    @Id // Anotação para indicar que este campo é a chave primária da entidade.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura a geração automática de valores para esta coluna (ID) usando uma sequência.
    @Column(name = "ID")
    private Long id;

    @Column(name = "MATRICULA", unique = true, nullable = false)
    private Long matricula;

    @Column(name = "NOME", nullable = false, length = 25)
    private String nome;

    @Column(name = "SOBRENOME", nullable = false, length = 50)
    private String sobrenome;

    @Column(name = "IDADE", nullable = false)
    private Long idade;

    @ManyToOne // Muitas PESSOAS para 1 CURSO
    @JoinColumn(name = "CURSO_ID", nullable = false)
    private Curso curso; // Composição -> 1:N

    // Métodos getters e setters permitem acessar e atualizar os valores das propriedades da classe.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Long getIdade() {
        return idade;
    }

    public void setIdade(Long idade) {
        this.idade = idade;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    // Método hashCode sobreescrito para fornecer consistência de uso em coleções baseadas em hash.
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    // Método equals sobreescrito para fornecer uma comparação adequada de objetos Pessoa.
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pessoas other = (Pessoas) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
