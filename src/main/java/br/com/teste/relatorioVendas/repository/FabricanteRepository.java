package br.com.teste.relatorioVendas.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.relatorioVendas.entity.FabricanteEntity;

@Repository
public interface FabricanteRepository extends PagingAndSortingRepository<FabricanteEntity, Long> {

	public FabricanteEntity findByCnpj(String cnpj);

	public void deleteByCnpj(String cnpj);

}
