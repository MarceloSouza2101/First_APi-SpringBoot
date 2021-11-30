package br.com.teste.relatorioVendas.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
public class FabricanteEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	@NotNull
	@NotEmpty
	private String cnpj;
	private String nome;
	@OneToMany
	@NotNull
	private List<JogoEntity> jogos;

	public FabricanteEntity() {
	}

	public FabricanteEntity(String cnpj, String nome, List<JogoEntity> jogos) {
		super();
		this.cnpj = cnpj;
		this.nome = nome;
		this.jogos = jogos;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<JogoEntity> getJogos() {
		return jogos;
	}

	public void setJogos(List<JogoEntity> jogos) {
		this.jogos = jogos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cnpj, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FabricanteEntity other = (FabricanteEntity) obj;
		return Objects.equals(cnpj, other.cnpj) && Objects.equals(nome, other.nome);
	}
}
