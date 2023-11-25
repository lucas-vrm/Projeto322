package Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


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

	// Método para visualizarmos os pacotes de viagem por data
	public List<Pacote> visualizarPorData(){
		return pacotesComprados.stream()
			.sorted(Comparator.comparing(Pacote::getDataPartida))
			.collect(Collectors.toList());
	}

	// Método para visualizarmos os pacotes de viagem por destino
	public List<Pacote> visualizarPorDestino(){
		return pacotesComprados.stream()
			.filter(p -> p.getDestino().equalsIgnoreCase(destino))
			.collect(Collectors.toList());
	}
	

}
