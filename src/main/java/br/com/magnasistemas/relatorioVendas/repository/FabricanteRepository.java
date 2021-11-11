package br.com.magnasistemas.relatorioVendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.magnasistemas.relatorioVendas.entity.FabricanteEntity;

@Repository
public interface FabricanteRepository extends JpaRepository<FabricanteEntity, Long>{

	public FabricanteEntity findByCnpj(String cnpj);

	
}
