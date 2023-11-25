package Model;

import java.util.ArrayList;

public class Cliente extends Usuario {
	
	private ArrayList<Destino> destinos;
	private double creditos;
	
	public Cliente(String nome, String contato, String email, int id) {
		super(nome, contato, email, id);
		this.destinos = new ArrayList<Destino>();
		this.creditos = 0.0;
	}
	
}
