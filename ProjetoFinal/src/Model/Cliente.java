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
	
	public Cliente(String nome, String contato, String email, int id) {
		super(nome, contato, email, id);
		this.pacotesComprados = new ArrayList<Pacote>();
		this.creditos = 0.0;
	}
	
}
