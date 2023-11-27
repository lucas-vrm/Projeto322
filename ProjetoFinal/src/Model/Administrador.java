package Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import Resources.RepositorioDestino;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Administrador extends Usuario {
    RepositorioDestino repDestino = new RepositorioDestino();

    RepositorioDestino repPacote = new RepositorioDestino();

    public Administrador(String nome, String contato, String email, String senha, int id) {
		super(nome, contato, email, senha, id);
	}
	
    public RepositorioDestino getRepDestino() {
        return this.repDestino;
    }

    public void setRepDestino(RepositorioDestino repDestino) {
        this.repDestino = repDestino;
    }
    
	public RepositorioDestino getRepPacote() {
        return this.repPacote;
    }

    public void setRepPacote(RepositorioDestino repPacote) {
        this.repPacote = repPacote;
    }

	public void fazAcao(int acao) {
		switch(acao) {
			case 0:
			
			case 1:
				//add novo pacote de viagem
			case 2:
				//apagar pacote de viagem
			case 3:
				//apagar usuario
			default:
				System.out.println("Algo errado");
		}
	}

    public Destino criaNovoDestino(Scanner scanner) {
        boolean entradaValida = false;
        String nome = null, descricao = null;
        ArrayList<String> pontosTuristicos = new ArrayList<>();

        while (!entradaValida) {
            try {
                System.out.println("==== Cadastro de Destino ====");

                System.out.println("Digite o nome do Destino:");
                nome = scanner.nextLine();

                System.out.println("De uma descricao do Destino:");
                descricao = scanner.nextLine();

                System.out.println("Adicione pontos turisticos (digite 0 para parar):");
                while (true) {
                    String entrada = scanner.nextLine();
                    if (entrada.equals("0")) {
                        break;  // Saia do loop se o usuário digitar "0"
                    }
                    pontosTuristicos.add(entrada);
                }
                scanner.nextLine();
                entradaValida = true;
                
            } catch (InputMismatchException e) {
                System.out.println("Erro: Valor inserido inválido. Certifique-se de inserir o tipo correto de dado.");
                scanner.nextLine();
            }
        }
        return new Destino(nome, descricao, pontosTuristicos);
    }

    public Pacote criaNovoPacote(Scanner scanner) {
        boolean entradaValida = false;
        String nome = null, nomeDestino = null, categoria = null;
        Destino destino = null;
        Date dataDePartida = null;
        int duracao = 0, assentosDisponiveis = 0;
        double preco = 0.0;
        ArrayList<String> atividadesDisponiveis = new ArrayList<>();

        while (!entradaValida) {
            try {
                System.out.println("==== Cadastro de Pacote ====");

                System.out.println("Digite o nome do Pacote:");
                nome = scanner.nextLine();

                System.out.println("Digite o nome do Destino:");
                nomeDestino = scanner.nextLine();
                destino = repDestino.getDestinoByName(nomeDestino);

                System.out.println("Digite a data de partida (formato YYYY-MM-DD):");
                SimpleDateFormat formatoData = new SimpleDateFormat("yyyy-MM-dd");
                dataDePartida = formatoData.parse(scanner.nextLine());

                System.out.println("Digite a duração da viagem em dias:");
                duracao = scanner.nextInt();

                System.out.println("Digite o preço do Pacote de Viagem:");
                preco = scanner.nextDouble();

                System.out.println("Digite a quantidade de assentos disponíveis:");
                assentosDisponiveis = scanner.nextInt();

                scanner.nextLine();
                System.out.println("Digite a categoria do pacote:");
                categoria = scanner.nextLine();

                System.out.println("Adicione atividades (digite 0 para parar):");
                while (true) {
                    String entrada = scanner.nextLine();
                    if (entrada.equals("0")) {
                        break;  // Saia do loop se o usuário digitar "0"
                    }
                    atividadesDisponiveis.add(entrada);
                }
                
                scanner.nextLine();
                entradaValida = true;
                
            } catch (InputMismatchException e) {
                System.out.println("Erro: Valor inserido inválido. Certifique-se de inserir o tipo correto de dado.");
                scanner.nextLine();
            } catch (ParseException e) {
                System.out.println("Erro: Data inserida inválida. Certifique-se de inserir o tipo correto de dado.");
                scanner.nextLine();
            }
        }
        return new Pacote(nome, destino, dataDePartida, duracao, preco, assentosDisponiveis, categoria, atividadesDisponiveis);
    }
	
    public void adicionarDestino(Scanner scanner) {
        try {
            repDestino.addItem(criaNovoDestino(scanner));
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao criar destino");
            e.printStackTrace();
        }
    }

    public void removerDestino(String nomeDestino) {
        try {
            repDestino.removeItemByAttributeValue("nome", nomeDestino);
        } catch (IOException e) {
            System.out.println("Erro ao remover destino");
            e.printStackTrace();
        }
    }

    public void adicionarPacote(Scanner scanner) {
        try {
            repPacote.addItem(criaNovoPacote(scanner));
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao criar pacote");
            e.printStackTrace();
        }
    }

    public void removerPacote(String nomePacote) {
        try {
            repDestino.removeItemByAttributeValue("nome", nomePacote);
        } catch (IOException e) {
            System.out.println("Erro ao remover pacote");
            e.printStackTrace();
        }
    }

}