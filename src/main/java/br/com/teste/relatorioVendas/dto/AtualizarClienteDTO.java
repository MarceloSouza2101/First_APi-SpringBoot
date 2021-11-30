package br.com.teste.relatorioVendas.dto;

import javax.validation.constraints.NotNull;


public class AtualizarClienteDTO {

	@NotNull
	private long telefone;

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

}
