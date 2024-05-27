package br.org.serratec.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.ecommerce.DTO.ClienteDTO;
import br.org.serratec.ecommerce.entity.Cliente;
import br.org.serratec.ecommerce.entity.Endereco;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Optional<Endereco> findByEnderecoCep(String cep);

	boolean existsByEmail(String email);

	boolean existsByCpf(String cpf);


}
