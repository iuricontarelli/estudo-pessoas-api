package com.pessoas.pessoas.model;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "CURSO")
public class Curso implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME", nullable = false, length = 25)
    private String nome;

    @Column(name = "DESCRICAO", nullable = false, length = 50)
    private String descricao;

    @Column(name = "DURACAO_HORAS", nullable = false)
    private String duracaoHoras;

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

    public String getDuracaoHoras() {
        return duracaoHoras;
    }

    public void setDuracaoHoras(String duracaoHoras) {
        this.duracaoHoras = duracaoHoras;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Curso other = (Curso) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}
