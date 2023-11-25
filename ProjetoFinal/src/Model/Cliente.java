package Model;

import java.util.ArrayList;

public class Cliente extends Usuario {
	
	private ArrayList<Destino> reservas;
	private double creditos;
	
	public Cliente(String nome, String contato, String email, int id) {
		super(nome, contato, email, id);
		this.reservas = new ArrayList<>();
		this.creditos = 0.0;
	}
	
}
