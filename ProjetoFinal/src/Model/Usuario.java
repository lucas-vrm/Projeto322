package Model;

import java.time.LocalDate;
import java.util.ArrayList;

import Model.Usuario.TipoMembro;

public class Usuario {
	private String nome;
	private String contato;
	private String email;
	private int id;
	private ArrayList<Destino> destinos;
	private double creditos;
	private LocalDate dataDeRegistro;
	private TipoMembro tipoDeMembro;
	
	public enum TipoMembro {
        USUARIO,
        ADM
    }
	
	/*Metodo construtor*/
	public Usuario(String nome, String contato, String email, int id, TipoMembro tipoDeMembro) {
		this.nome = nome;
		this.contato = contato;
		this.email = email;
		this.id = id;
		this.destinos = new ArrayList<Destino>();
		this.creditos = 0.0;
		LocalDate dataDeRegistro = LocalDate.now();
		this.tipoDeMembro = tipoDeMembro;
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

	public TipoMembro getTipoDeMembro() {
		return tipoDeMembro;
	}

	public void setTipoDeMembro(TipoMembro tipoDeMembro) {
		this.tipoDeMembro = tipoDeMembro;
	}

}
