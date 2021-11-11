package br.com.magnasistemas.relatorioVendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.magnasistemas.relatorioVendas.entity.JogoEntity;

@Repository
public interface JogoRepository extends JpaRepository<JogoEntity, Long>{
	
	public JogoEntity findByLote(String lote);
}
