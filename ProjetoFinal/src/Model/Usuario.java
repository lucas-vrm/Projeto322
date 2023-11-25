package Model;

import java.time.LocalDate;
import java.util.ArrayList;

abstract class Usuario {
	private String nome;
	private String contato;
	private String email;
	private int id;
	private LocalDate dataDeRegistro;
	
	/*Metodo construtor*/
	public Usuario(String nome, String contato, String email, int id) {
		this.nome = nome;
		this.contato = contato;
		this.email = email;
		this.id = id;
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

}
