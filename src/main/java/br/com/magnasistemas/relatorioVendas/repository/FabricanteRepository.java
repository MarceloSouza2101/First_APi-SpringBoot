package br.com.magnasistemas.relatorioVendas.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.magnasistemas.relatorioVendas.entity.FabricanteEntity;

@Repository
public interface FabricanteRepository extends PagingAndSortingRepository<FabricanteEntity, Long> {

	public FabricanteEntity findByCnpj(String cnpj);

	public void deleteByCnpj(String cnpj);

}
