// Define o pacote em que a interface está localizada.
package com.pessoas.pessoas.repository;

// Importa a classe Optional do Java, usada para tratar valores que podem ser nulos.
import java.util.Optional;

// Importa a interface JpaRepository do Spring Data JPA, para operações CRUD básicas e definir queries personalizadas.
import org.springframework.data.jpa.repository.JpaRepository;

// Importa a classe Pessoas do pacote model para ser usada na definição do repositório.
import com.pessoas.pessoas.model.Pessoas;

// Declaração da interface PessoasRepository que estende JpaRepository, facilitando a interação com o banco de dados.
public interface PessoasRepository extends JpaRepository<Pessoas, Long> {

    // Declara um método para buscar uma entidade Pessoas pelo nome e sobrenome.
    // Retorna um Optional de Pessoas, o que ajuda a lidar com o caso de não encontrar a entidade.
    Optional<Pessoas> findByNomeAndSobrenome(String nome, String sobrenome);

}
