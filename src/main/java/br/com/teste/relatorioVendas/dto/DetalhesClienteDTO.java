package br.com.teste.relatorioVendas.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



public class DetalhesClienteDTO {

	@NotBlank
	private String cpf;
	@NotBlank
	private String nome;
	@NotNull 
	private long telefone;
	@NotEmpty
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

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

}
