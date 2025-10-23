package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // Identificador único do tutor
	private String nome;
	private String cpf;
	@Column(name = "t_email", nullable = false, length = 512)
	private String email;
	private String telefone;
	private String endereco;

	// ✅ Construtor vazio necessário para o Hibernate
	public Aluno() {
	}

	// Construtor com todos os dados
	public Aluno(String nome, String cpf, String telefone, String t_email) {
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.email = t_email;
	}

	// Construtor da classe com todos os atributos
	public Aluno(String nome, String cpf, String email, String telefone, String endereco) {

		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Tutor [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", telefone=" + telefone
				+ ", endereco=" + endereco + "]";
	}

}