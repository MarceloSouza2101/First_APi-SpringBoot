package br.com.teste.relatorioVendas.dto;

import javax.validation.constraints.NotBlank;

public class AtualizarFabricanteDTO {

	@NotBlank
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
