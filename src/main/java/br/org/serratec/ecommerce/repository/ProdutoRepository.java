package br.org.serratec.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.serratec.ecommerce.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto , Long> {

}
