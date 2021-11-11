package br.com.magnasistemas.relatorioVendas.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.magnasistemas.relatorioVendas.config.validacao.CpfCnpj;


public class DetalhesFabricanteDto {

	@NotBlank
	private String nome;
	@NotBlank @CpfCnpj
	private String cnpj;
	@NotNull
	private List<JogoDTO> jogos;
	
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public List<JogoDTO> getJogos() {
		return jogos;
	}
	public void setJogos(List<JogoDTO> jogos) {
		this.jogos = jogos;
	}
	
	
	
	
}
