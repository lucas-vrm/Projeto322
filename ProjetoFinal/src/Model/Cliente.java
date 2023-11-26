package Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Set;
import java.util.HashSet;

public class Cliente extends Usuario {
	
	private ArrayList<Pacote> pacotesComprados;
	private double creditos;
	
	public Cliente(String nome, String contato, String email, String senha, int id) {
		super(nome, contato, email, senha, id);
		this.pacotesComprados = new ArrayList<Pacote>();
		this.creditos = 0.0;
	}

	public ArrayList<Pacote> getPacotesComprados() {
		return pacotesComprados;
	}

	public void setPacotesComprados(ArrayList<Pacote> pacotesComprados) {
		this.pacotesComprados = pacotesComprados;
	}

	public double getCreditos() {
		return creditos;
	}

	public void setCreditos(double creditos) {
		this.creditos = creditos;
	}

	// Método para adicionar um pacote de viagem comprado pelo cliente.
	public void comprarPacoteViagem(Pacote pacote, int quantidadeAssentos){
		if (creditos >= pacote.getPreco()){
			// Para checarmos se ainda temos assentos disponíveis
			boolean reservou = pacote.reservar(quantidadeAssentos);
			if (reservou){
				System.out.println("O pacote de viagem foi comprado com sucesso!");
				pacotesComprados.add(pacote);
				double creditoAtualizado = creditos - pacote.getPreco();
				this.creditos = creditoAtualizado;
			}
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
	public Set<Destino> visualizarDestinosPacotesComprados(){
		Set<Destino> destinos = new HashSet<>();

		for (Pacote pacote : pacotesComprados) {
			destinos.add(pacote.getDestino());
		}
		return destinos;
	}

	// Método para verificarmos a quantidade de créditos do cliente.
	public void verificarCreditos(){
		System.out.println("Você possui R$ " + creditos + " em créditos.");
	}

	// Método para adicionarmos créditos ao cliente.
	public void adicionarCreditos(double creditosAdicionados){
		double creditosAtualizados = this.creditos + creditosAdicionados;
		this.creditos = creditosAtualizados;
		System.out.println("Seus créditos adicionados com sucesso!");
	}

}
