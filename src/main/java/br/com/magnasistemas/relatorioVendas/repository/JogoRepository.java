package br.com.magnasistemas.relatorioVendas.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.magnasistemas.relatorioVendas.entity.JogoEntity;

@Repository
public interface JogoRepository extends PagingAndSortingRepository<JogoEntity, Long> {

	public JogoEntity findByLote(String lote);

	public void deleteByLote(String lote);
}
