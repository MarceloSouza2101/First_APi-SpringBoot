package br.com.magnasistemas.relatorioVendas.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class ClienteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cpf;
	private String nome;
	private int telefone;
	@ManyToMany
	private List<JogoEntity> jogos;

	public ClienteEntity() {
	}

	public ClienteEntity(String cpf, String nome, int telefone, List<JogoEntity> jogos) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.jogos = jogos;
	}

	public Long getId() {
		return id;
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

	public List<JogoEntity> getJogos() {
		return jogos;
	}

	public void setJogos(List<JogoEntity> jogos) {
		this.jogos = jogos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, nome, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteEntity other = (ClienteEntity) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(nome, other.nome)
				&& Objects.equals(telefone, other.telefone);
	}

}
