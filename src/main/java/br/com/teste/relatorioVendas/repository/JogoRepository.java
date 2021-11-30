package br.com.teste.relatorioVendas.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.relatorioVendas.entity.JogoEntity;

@Repository
public interface JogoRepository extends PagingAndSortingRepository<JogoEntity, Long> {

	public JogoEntity findByLote(String lote);

	public void deleteByLote(String lote);
}
