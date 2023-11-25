package Model;

import java.util.ArrayList;

public class Cliente extends Usuario {
	
	private ArrayList<Pacote> pacotesComprados;
	private double creditos;
	
	public Cliente(String nome, String contato, String email, int id) {
		super(nome, contato, email, id);
		this.pacotesComprados = new ArrayList<Pacote>();
		this.creditos = 0.0;
	}

	// Método para adicionar um pacote de viagem comprado pelo cliente.
	public void comprarPacoteViagem(Pacote pacote){
		if (creditos >= pacote.getPreco){
			System.out.println("O pacote de viagem foi comprado com sucesso!");
			pacotesComprados.add(pacote);
			double creditoAtualizado = creditos - pacote.getPreco;
			this.creditos = creditoAtualizado;
		}
		else {
			System.out.println("Você não possui créditos suficientes para comprar este pacote.");
		}
	}

}
