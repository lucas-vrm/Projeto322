package Model;

import java.util.ArrayList;

public class Cliente extends Usuario {
	
	private ArrayList<Destino> destinos;
	private double creditos;
	
	public Cliente(String nome, String contato, String email, String senha, int id) {
		super(nome, contato, email, senha, id);
		this.destinos = new ArrayList<Destino>();
		this.creditos = 0.0;
	}

	public ArrayList<Destino> getDestinos() {
		return destinos;
	}

	public void setDestinos(ArrayList<Destino> destinos) {
		this.destinos = destinos;
	}

	public double getCreditos() {
		return creditos;
	}

	public void setCreditos(double creditos) {
		this.creditos = creditos;
	}
	
}
