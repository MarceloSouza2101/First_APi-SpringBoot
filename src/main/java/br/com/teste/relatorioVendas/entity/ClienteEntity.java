package br.com.teste.relatorioVendas.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;



@Entity
public class ClienteEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@NotEmpty(message = "CPF NÃO PODE ESTAR EM BRANCO")
	@Column(unique = true)
	private String cpf;
	private String nome;
	private long telefone;
	@ManyToMany
	@NotNull
	private List<JogoEntity> jogos;

	public ClienteEntity() {
	}

	public ClienteEntity(String cpf, String nome, long telefone, List<JogoEntity> jogos) {
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

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
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
