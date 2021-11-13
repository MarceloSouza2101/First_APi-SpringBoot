package br.com.magnasistemas.relatorioVendas.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.magnasistemas.relatorioVendas.entity.ClienteEntity;

@Repository
public interface ClienteRepository extends PagingAndSortingRepository<ClienteEntity, Long> {

	public ClienteEntity findByCpf(String cpf);

	public void deleteByCpf(String cpf);
}
