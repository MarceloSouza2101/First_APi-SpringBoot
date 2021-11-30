package br.com.teste.relatorioVendas.dto;

import javax.validation.constraints.NotBlank;

public class AlterarListas {

	@NotBlank
	private String lote;

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

}
