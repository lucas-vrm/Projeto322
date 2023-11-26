package Model;

import java.time.LocalDate;

abstract class Usuario {
	private String nome;
	private String contato;
	private String email;
	private String senha;
	private int id;
	private LocalDate dataDeRegistro;
	
	/*Metodo construtor*/
	public Usuario(String nome, String contato, String email, String senha, int id) {
		this.nome = nome;
		this.contato = contato;
		this.email = email;
		this.senha = senha;
		this.id = id;
		this.dataDeRegistro = LocalDate.now();
	}
	
	/*Metodos getters e setters*/
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getDataDeRegistro() {
		return dataDeRegistro;
	}

	public void setDataDeRegistro(LocalDate dataDeRegistro) {
		this.dataDeRegistro = dataDeRegistro;
	}

}
