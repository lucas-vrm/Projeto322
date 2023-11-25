package Model;

import java.time.LocalDate;

abstract class Usuario {
	private String nome;
	private String contato;
	private String email;
	private int id;
	private LocalDate dataDeRegistro;
	private boolean logado;
	
	/*Metodo construtor*/
	public Usuario(String nome, String contato, String email, int id) {
		this.nome = nome;
		this.contato = contato;
		this.email = email;
		this.id = id;
		this.logado = false; // Por padrão, iniciamos o usuário como não-logado
		LocalDate dataDeRegistro = LocalDate.now();
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

	public void fazerLogin(){
		this.logado = true;
		System.out.println("Seja bem vindo, " + nome + "!");
	}

	public void fazerLogout(){
		this.logado = false;
		System.out.println("Usuário " + nome + " deslogado com sucesso!");
	}

	public boolean estaLogado(){
		return logado;
	}

}
