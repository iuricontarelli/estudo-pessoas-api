// Define o pacote onde a interface está localizada.
package com.pessoas.pessoas.service;

// Importa a classe List do Java, usada para representar listas de objetos.
import java.util.List;

// Importa a classe Pessoas do pacote model para ser usada nos métodos desta interface.
import com.pessoas.pessoas.model.Pessoas;

// Declaração da interface PessoasService, que define os serviços que podem ser executados em objetos Pessoas.
public interface PessoasService {

    // Método para listar todas as pessoas cadastradas. Retorna uma lista de objetos Pessoas.
    List<Pessoas> listarPessoas();

    // Método para buscar uma pessoa específica pelo seu ID. Retorna um objeto Pessoas caso encontrado.
    Pessoas buscarPessoas(Long id);

    // Método para adicionar uma nova pessoa ao banco de dados. Retorna o objeto Pessoas adicionado com o ID atribuído.
    Pessoas adicionarPessoas(Pessoas pessoas);

    // Método para atualizar dados de uma pessoa existente no banco de dados usando seu ID.
    // Retorna o objeto Pessoas atualizado.
    Pessoas atualizarPessoas(Long id, Pessoas pessoas);

    // Método para excluir uma pessoa do banco de dados pelo seu ID. Não retorna valor.
    void excluirPessoas(Long id);

}
