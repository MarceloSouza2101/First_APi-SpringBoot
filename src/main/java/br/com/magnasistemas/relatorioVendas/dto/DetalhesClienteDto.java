package br.com.magnasistemas.relatorioVendas.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.magnasistemas.relatorioVendas.config.validacao.CpfCnpj;



public class DetalhesClienteDto {

	@NotBlank @CpfCnpj
	private String cpf;
	@NotBlank
	private String nome;
	@NotNull
	private int telefone;
	@NotNull
	private List<JogoDTO> jogos;
	
	
	public List<JogoDTO> getJogos() {
		return jogos;
	}
	public void setJogos(List<JogoDTO> jogos) {
		this.jogos = jogos;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
	
}
